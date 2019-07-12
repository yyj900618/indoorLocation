package com.cqut.indoor.utills;

import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class WebUtils {

    public static final String AJAX_ACCEPT_CONTENT_TYPE = "text/html;type=ajax";
    public static final String AJAX_SOURCE_PARAM = "ajaxSource";
    public static final String AJAX_X_Requested_With = "X-Requested-With";

    public static boolean isAjax(HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        String RequestWith =  request.getHeader(AJAX_X_Requested_With);
        String ajaxParam = request.getParameter(AJAX_SOURCE_PARAM);
        return RequestWith!= null || AJAX_ACCEPT_CONTENT_TYPE.equals(acceptHeader) || StringUtils.hasText(ajaxParam)
                || (!StringUtils.isEmpty(acceptHeader) && acceptHeader.toLowerCase().contains("json"));
    }


    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static final String getIpAddr(final HttpServletRequest request)
            throws Exception {
        if (request == null) {
            throw (new Exception("getIpAddr method HttpServletRequest Object is null"));
        }
        String ipString = request.getHeader("x-forwarded-for");
        if (org.apache.commons.lang3.StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("Proxy-Client-IP");
        }
        if (org.apache.commons.lang3.StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("WL-Proxy-Client-IP");
        }
        if (org.apache.commons.lang3.StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getRemoteAddr();
        }
        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ipString.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ipString = str;
                break;
            }
        }
        return ipString;
    }

    public static Map<String, String>  getParamsByRequest(HttpServletRequest request){
        if (request==null)
            return null;
        String body=getBodyString(request);
        if (!body.isEmpty()) {
            Map<String, String> params = JsonUtils.toObject(body, Map.class);
            return params;
        }
        else
            return new HashMap<>();
    }

}
