package com.zhihui.zhexpress.base.exception;

/**
 *
 * @date 18-9-3 下午2:58
 * 自定义用户信息异常类
 *
 */
public class ZHUserInfoException extends RuntimeException {

    public ZHUserInfoException() {
        super();
    }

    public ZHUserInfoException(String msg) {
        super(msg);
    }

}
