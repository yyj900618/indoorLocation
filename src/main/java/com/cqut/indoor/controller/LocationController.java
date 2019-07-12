package com.cqut.indoor.controller;

import com.cqut.indoor.common.ResponseModel;

import com.cqut.indoor.model.RPIDdistance;
import com.cqut.indoor.model.SSIDIntensity;
import com.cqut.indoor.model.TDmeasure;
import com.cqut.indoor.model.TDrp;
import com.cqut.indoor.service.LocationService;
import com.cqut.indoor.utills.JsonUtils;
import com.cqut.indoor.utills.RedisUtil;
import com.cqut.indoor.utills.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("location")
@Api(description = "定位")
public class LocationController {


    @Value("${location.steplong}")
    Integer steplong;

    @Resource(name = "redisUtil")
    RedisUtil redisUtil;

    @Resource(name = "jedis")
    Jedis jedis;

    @Autowired
    LocationService locationService;

    @ApiOperation(value = "根据wifi测量信息进行室内定位,进行初始定位", notes = "定位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ssidIntensity_json", dataType = "string", paramType = "query", value = "SSID及信号强度，json格式，列：[{\"ssid\": \"AP1\", \"intensity\": \"123\"}]")})
    @RequestMapping(value = "indoor_wifi", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel indoor_wifi(@RequestParam(value = "ssidIntensity_json") String ssidIntensity_json) {
        HashMap<String, Object> location_wifi = getLocationByWifi(ssidIntensity_json);
        HashMap<String, Object> result = new HashMap<>();
        result.put("x", location_wifi.get("x"));
        result.put("y", location_wifi.get("y"));
        return new ResponseModel((Long) location_wifi.get("code"), String.valueOf(location_wifi.get("msg")), result);
    }


    @ApiOperation(value = "根据传感器信息计算下一次坐标_pdr", notes = "定位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", dataType = "string", paramType = "query", value = "上一次得到的x坐标"),
            @ApiImplicitParam(name = "y", dataType = "string", paramType = "query", value = "上一次得到的y坐标"),
            @ApiImplicitParam(name = "angle", dataType = "string", paramType = "query", value = "角度"),
            @ApiImplicitParam(name = "base", dataType = "int", paramType = "query", value = "步数"),
            @ApiImplicitParam(name = "pointCount", dataType = "int", paramType = "query", value = "第几次打点，用于5步校准法"),
            @ApiImplicitParam(name = "ssidIntensity_json", dataType = "string", paramType = "query", value = "SSID及信号强度，当,pointCount为5的倍数时，必传。json格式，列：[{\"ssid\": \"AP1\", \"intensity\": \"123\"}]")
    })
    @RequestMapping(value = "indoor_pdr", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel indoor_pdr(
            @RequestParam(value = "ssidIntensity_json", required = false) String ssidIntensity_json,
            @RequestParam(value = "x") String x,
            @RequestParam(value = "y") String y,
            @RequestParam(value = "angle") String angle,
            @RequestParam(value = "base") Integer base,
            @RequestParam(value = "pointCount") Integer pointCount) {
        HashMap<String, Object> result = new HashMap<>();
        if (pointCount % 5 == 0) {//五步校准
            if (StringUtils.isNullOrNone(ssidIntensity_json))
                return new ResponseModel(500L, "失败：请传入wifi测量信息", null);
            HashMap<String, Object> location_wifi = getLocationByWifi(ssidIntensity_json);

            result.put("x", location_wifi.get("x"));
            result.put("y", location_wifi.get("y"));
            return new ResponseModel((Long) location_wifi.get("code"), String.valueOf(location_wifi.get("msg")), result);
        } else {
            double deltaX = base * steplong * Math.sin(Math.toRadians(Double.parseDouble(angle)));//计算x轴偏移量
            double deltaY = base * steplong * Math.cos(Math.toRadians(Double.parseDouble(angle)));//计算y轴偏移量
            BigDecimal x_next = new BigDecimal(x).add(new BigDecimal(deltaX)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal y_next = new BigDecimal(y).add(new BigDecimal(deltaY)).setScale(2, BigDecimal.ROUND_HALF_UP);
            result.put("x", x_next);
            result.put("y", y_next);
            return new ResponseModel(0L, "成功", result);
        }
    }


    //根据wifi强度定位算法,根据RP位置定位。
    private HashMap<String, Object> getLocationByWifi(String ssidIntensity_json) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 500L);
        List<LinkedHashMap<String, Object>> ssidIntensity_List_LinkedHashMap = JsonUtils.toObject(ssidIntensity_json, List.class);
        //测量信息
        List<SSIDIntensity> ssidIntensityList = ssidIntensity_List_LinkedHashMap.stream().map(it -> new SSIDIntensity(it.get("ssid").toString(), it.get("intensity").toString())).collect(Collectors.toList());
        List<TDrp> rpList = locationService.getRpList();//拿出全部的RP点(指纹)
        if (rpList.isEmpty()) {
            result.put("msg", "失败:指纹库为空，不能进行定位");
            return result;
        }
        List<RPIDdistance> rpiDdistanceList = new ArrayList<>();

        for (TDrp rp : rpList) {
            boolean flag = false;
            BigDecimal distance = new BigDecimal(0);
            for (SSIDIntensity si : ssidIntensityList) {

                BigDecimal rssi = new BigDecimal(si.getIntensity());
                TDmeasure RRSI_obj = locationService.getMeasure(rp.getRpid(), si.getSSID());
                BigDecimal RSSI = RRSI_obj.getRrsi();
                if (RSSI == null) {
                    flag = true;//如果该指纹点没有手机测量AP的信号强度信息，则摒弃该指纹点
                    break;
                }

                BigDecimal distance_compent = RSSI.subtract(rssi).pow(2);
                distance = distance.add(distance_compent);
            }
            if (flag)
                continue;
            rpiDdistanceList.add(new RPIDdistance(rp.getRpid(), distance));
        }
        Collections.sort(rpiDdistanceList);
        if (rpiDdistanceList.isEmpty()) {
            result.put("msg", "失败:为找到匹配的指纹，不能进行定位");
            return result;
        }
        else if (rpiDdistanceList.size() >= 2) {
            BigDecimal x = new BigDecimal(0);
            BigDecimal y = new BigDecimal(0);
            for (RPIDdistance item : rpiDdistanceList.subList(0, 2)) {
                TDrp rp = locationService.getrp(item.getRPID());
                x = x.add(rp.getX());
                y = y.add(rp.getY());
            }
            x = x.divide(new BigDecimal(2), 2, BigDecimal.ROUND_HALF_UP);
            y = y.divide(new BigDecimal(2), 2, BigDecimal.ROUND_HALF_UP);
            result.put("code", 0L);
            result.put("msg", "成功");
            result.put("x", x);
            result.put("y", y);
        } else {
            TDrp rp = locationService.getrp(rpiDdistanceList.get(0).getRPID());
            result.put("code", 0L);
            result.put("msg", "成功");
            result.put("x", rp.getX().setScale(2, BigDecimal.ROUND_HALF_UP));
            result.put("y", rp.getX().setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        return result;
    }
}
