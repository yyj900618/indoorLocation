package com.cqut.indoor.utills;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;

public class JsonUtils {

    private static ObjectMapper mapper=new ObjectMapper();
    private static SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.setDateFormat(format);
    }

    public static String toJson(Object object) {

        StringWriter writer=null;
        try {
            writer=new StringWriter();
            mapper.writeValue(writer,object);
            writer.flush();
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("JSON 格式化出错",e);
        }finally{
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static <T> T toObject(String jsonstr, Class<T> clazz){
        try {
            return mapper.readValue(jsonstr, clazz);
        } catch (JsonParseException e) {
            throw new RuntimeException("JSON 格式化出错",e);
        } catch (JsonMappingException e) {
            throw new RuntimeException("JSON 格式化出错",e);
        } catch (IOException e) {
            throw new RuntimeException("网络连接异常",e);
        }

    }
    
    public static String toJson(ObjectMapper objectMapper, Object object) {
        StringWriter writer=null;
        try {
            writer=new StringWriter();
            if(objectMapper==null){
                mapper.writeValue(writer, object);
            }else{
                objectMapper.writeValue(writer, object);
            }
            writer.flush();
            return writer.toString();
        }catch (Exception e) {
            throw new RuntimeException("JSON 格式化出错",e);
        }finally{
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean writeJsonValue(ObjectMapper objectMapper, OutputStream os, Object object) {
        boolean bool=true;
        try {
            if(objectMapper==null){
                mapper.writeValue(os, object);
            }else{
                objectMapper.writeValue(os, object);
            }
            os.flush();
        } catch (Exception e) {
            throw new RuntimeException("JSON 格式化出错",e);
        }
        return bool;
    }

    public static boolean writeJsonValue(OutputStream os, Object object) {
        boolean bool=true;
        try {
            mapper.writeValue(os, object);
            os.flush();
        } catch (Exception e) {
            throw new RuntimeException("JSON 格式化出错",e);
        }
        return bool;
    }
    public static boolean writeJsonValue(HttpServletResponse response, Object object) {
        return writeJsonValue(null,response, object);
    }
    public static boolean writeJsonValue(ObjectMapper objectMapper, HttpServletResponse response, Object object) {
        response.setContentType("text/html;charset=UTF-8");
        boolean bool=true;
        if(objectMapper==null){
            objectMapper=mapper;
        }
        try {
            objectMapper.writeValue(response.getOutputStream(), object);
        } catch (Exception e) {
            throw new RuntimeException("JSON 格式化出错",e);
        }
        return bool;
    }

    public static <T> T readValueFromURL(String url, Class<T> clasz){

        try {
            return mapper.readValue(new URL(url), clasz);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T readValueFromStream(InputStream in, TypeReference<T> valueTypeRef){

        try {
            return (T)mapper.readValue(in, valueTypeRef);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


}
