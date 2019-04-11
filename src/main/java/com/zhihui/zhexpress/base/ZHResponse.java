package com.zhihui.zhexpress.base;

import com.alibaba.fastjson.JSON;
import com.zhihui.zhexpress.config.ZHConfig;

import java.util.LinkedHashMap;
import java.util.Map;

public class ZHResponse {

    public static Map<String, Object> success(Object obj) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put(ZHConfig.RESP_STATUS, ZHConfig.RESP_SUCCESS);
        resultMap.put(ZHConfig.RESP_RESULT, obj);
        ZHLog.info2("-->>返回值： " + JSON.toJSONString(resultMap));
        return resultMap;
    }

    public static Map<String, Object> failed(Object obj) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put(ZHConfig.RESP_STATUS, ZHConfig.RESP_FAIL);
        resultMap.put(ZHConfig.RESP_RESULT, obj);
        ZHLog.info2("-->>返回值： " + JSON.toJSONString(resultMap));
        return resultMap;
    }

    public static Map<String, Object> warning(Object obj) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put(ZHConfig.RESP_STATUS, ZHConfig.RESP_WARNING);
        resultMap.put(ZHConfig.RESP_RESULT, obj);
        ZHLog.info2("-->>返回值： " + JSON.toJSONString(resultMap));
        return resultMap;
    }

    public static Map<String, Object> forbidden(Object obj) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put(ZHConfig.RESP_STATUS, ZHConfig.RESP_FORBIDDEN);
        resultMap.put(ZHConfig.RESP_RESULT, obj);
        ZHLog.info2("-->>返回值： " + JSON.toJSONString(resultMap));
        return resultMap;
    }

}
