package com.zhihui.zhexpress.base;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by JerryYin on 7/13/17.
 */
public class ZHLog {

    private static Logger logger = LoggerFactory.getLogger("");

    private static boolean flag = true;

    public static void info(Object message) {
//        Throwable throwable = new Throwable();
//        StackTraceElement[] ste = throwable.getStackTrace();
//        if (flag)
//            logger.info("--->>>log类名: " + ste[1].getClassName() + "  方法名: " + ste[1].getMethodName() + "  行数: " + ste[1].getLineNumber());
        if (message instanceof String){
            logger.info((String) message);
        }else {
            logger.info(JSONObject.toJSONString(message));
        }
    }

    public static void info2(String message) {
        if (flag)
            logger.info(message);
    }

    public static void info(Object message, Throwable t) {
        if (flag)
            logger.info(JSONObject.toJSONString(message), t);
    }

    public static void warn(Object message) {
        if (flag)
            logger.warn(JSONObject.toJSONString(message));
    }

    public static void warn(Object message, Throwable t) {
        if (flag)
            logger.warn(JSONObject.toJSONString(message), t);
    }

    public static void error(Object message) {
//        Throwable throwable = new Throwable();
//        StackTraceElement[] ste = throwable.getStackTrace();
//        if (flag)
//            logger.error("--->>>log类名: " + ste[1].getClassName() + "  方法名: " + ste[1].getMethodName() + "  行数: " + ste[1].getLineNumber());
        logger.error(JSONObject.toJSONString(message));
    }

    public static void error(Object message, Throwable t) {
//        Throwable throwable = new Throwable();
//        StackTraceElement[] ste = throwable.getStackTrace();
//        if (flag)
//            logger.error("--->>>log类名: " + ste[1].getClassName() + "  方法名: " + ste[1].getMethodName() + "  行数: " + ste[1].getLineNumber());
        logger.error(JSONObject.toJSONString(message), t);
    }

    public static void debug(Object message) {
        if (flag)
//            logger.debug(object);
            logger.info(JSONObject.toJSONString(message));
    }

    public static void debug(Object message, Throwable throwable) {
        if (flag)
            logger.debug(JSONObject.toJSONString(message), throwable);
    }

}


