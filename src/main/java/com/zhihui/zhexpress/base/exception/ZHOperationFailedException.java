package com.zhihui.zhexpress.base.exception;

import com.alibaba.fastjson.JSONObject;

import javax.validation.constraints.NotNull;

/**
 * @author cc
 * @date 19-1-2 上午11:55
 */
public class ZHOperationFailedException extends RuntimeException{

    public ZHOperationFailedException(){super();}
    public ZHOperationFailedException(@NotNull Integer code, @NotNull String msg){
        super(concatMsg(code,msg));
    }
    public ZHOperationFailedException(@NotNull String msg){
        super(concatMsg(null,msg));
    }
    public static String concatMsg(Integer code,@NotNull String msg){
        JSONObject jsonObject = new JSONObject();
        if (code!=null) {
            jsonObject.put("code",code);
        }

        jsonObject.put("msg",msg);
        return jsonObject.toJSONString();
    }
}
