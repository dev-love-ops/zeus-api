package com.devloveops.zeus.common;


import java.util.HashMap;

public class JsonResult  extends HashMap<String, Object>{

    public static JsonResult success(String message){
        JsonResult jsonResult = new JsonResult();
        jsonResult.put("code", 0);
        jsonResult.put("message", message);
        return jsonResult;
    }

    public static JsonResult success(String message, Object data){
        JsonResult jsonResult = new JsonResult();
        jsonResult.put("code", 0);
        jsonResult.put("message", message);
        jsonResult.put("data", data);
        return jsonResult;
    }

    public static JsonResult error(int code, String message){
        JsonResult jsonResult = new JsonResult();
        jsonResult.put("code", code);
        jsonResult.put("message", message);
        return jsonResult;
    }

}
