package com.zhihui.zhexpress.base.exception;

/**
 * @author cc
 * @date 18-7-13 下午6:11
 * 自定义参数异常错误
 */
public class ZHErrorParamsException extends RuntimeException {

    private static final long serialVersionUID = 2162710183389028709L;

    public ZHErrorParamsException() {
        super();
    }

    public ZHErrorParamsException(String msg) {
        super(msg);
    }
}

