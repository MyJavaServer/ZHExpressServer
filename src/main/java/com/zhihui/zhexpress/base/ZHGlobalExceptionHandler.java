package com.zhihui.zhexpress.base;

import com.alibaba.fastjson.JSONObject;
import com.zhihui.zhexpress.base.exception.ZHErrorParamsException;
import com.zhihui.zhexpress.base.exception.ZHOperationFailedException;
import com.zhihui.zhexpress.base.exception.ZHUserInfoException;
import com.zhihui.zhexpress.service.ZUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by JerryYin on 7/12/17.
 */

@ControllerAdvice
public class ZHGlobalExceptionHandler {


    private final String errorHead = "全局异常: ";

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Map handleSQLException(Exception e) {
        ZHLog.error(errorHead, e);
        return ZHResponse.failed("操作失败！");
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Map handleNULLPointException(Exception e) {
        ZHLog.error(errorHead, e);
        return ZHResponse.failed("任务处理异常，请重试或联系客服！");
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Map handleNumberFormatException(Exception e) {
        ZHLog.error(errorHead, e);
        return ZHResponse.failed("请求参数有误!");
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Map handleIllegalParamException(Exception e) {
        ZHLog.error(errorHead, e);
        return ZHResponse.failed("请求参数格式有误!");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Map handleNotReadableException(Exception e) {
        ZHLog.error(errorHead, e);
        return ZHResponse.failed("请求参数有误!");
    }

    @ExceptionHandler(org.springframework.jdbc.UncategorizedSQLException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Map handleSqlException(Exception e) {
        ZHLog.error(errorHead, e);
        return ZHResponse.failed("参数格式异常!");
    }

    @ExceptionHandler(org.mybatis.spring.MyBatisSystemException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Map handleMyBatisSystemException(Exception e) {
        ZHLog.error(errorHead, e);
        return ZHResponse.failed("Unsolicited result!!");
    }

    @ExceptionHandler(org.springframework.dao.DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Map handleDuplicateKeyException(Exception e) {
        ZHLog.error(errorHead, e);
        return ZHResponse.failed("参数有误!");
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Map handleIndexOutOfBoundsException(Exception e) {
        ZHLog.error(errorHead, e);
        return ZHResponse.failed("无相关数据！");
    }

    @ExceptionHandler(ClassCastException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Map handleClassCastException(Exception e) {
        ZHLog.error(errorHead, e);
        return ZHResponse.failed("数据转换异常，请检查数据格式");
    }

    @ExceptionHandler(org.springframework.validation.BindException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Map validationException(Exception e) {
        ZHLog.info(errorHead, e);
        return ZHResponse.failed("参数绑定异常，请重试！");
    }

    @ExceptionHandler(ZHErrorParamsException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Map myException(Exception e) {
        ZHLog.info(errorHead, e);
        if (e.getMessage() == null) {
            return ZHResponse.failed("参数有误，请重试！");
        } else {
            return ZHResponse.failed(e.getMessage());
        }
    }

    @ExceptionHandler(com.alibaba.fastjson.JSONException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Map JSONException(Exception e) {
        e.printStackTrace();
        ZHLog.info(e + "所传必须参数为空");
        return ZHResponse.failed("数据绑定异常！");
    }

    @ExceptionHandler(ZHOperationFailedException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Map operationFailedException(Exception e) {
        ZHLog.info("自定义operationFailedException！");
        if (e.getMessage() == null) {
            return ZHResponse.failed("操作失败，请重试或联系客服人员！");
        } else {
            JSONObject jsonObject = JSONObject.parseObject(e.getMessage());
            if (jsonObject.getInteger("code") == null) {
                return ZHResponse.failed(jsonObject.getString("msg"));
            } else {
                return ZHResponse.failed(jsonObject);
            }
        }
    }

    @ExceptionHandler(ZHUserInfoException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map authorityException(Exception e) {
        if (e.getMessage() == null) {
            return ZHResponse.failed("无操作权限！");
        } else {
            return ZHResponse.forbidden(e.getMessage());
        }
    }

}
