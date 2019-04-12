package com.zhihui.zhexpress.utils;

import com.github.pagehelper.PageHelper;

import java.util.Optional;

/**
 * Created by JerryYin on 10/11/17.
 */
public class StringUtil {

    public static String orderBy(String orderBy) {
        return Optional.ofNullable(orderBy)
                .map(x -> x.endsWith("_desc") ? x.replace("_desc", " desc") : x.replace("_asc", " asc"))
                .map(StringUtil::camelToUnderline).orElse(null);
    }

    public static void startPage(Integer pageNum,Integer pageSize,String orderBy){
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        if (orderBy!=null) {
            PageHelper.orderBy(orderBy(orderBy));
        }
    }

    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append('_');
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();


    }


    /**
     * 将下划线风格替换为驼峰风格
     *
     * @param inputString 带转换字符串
     * @return 驼峰字符串
     */
    public static String underlineToCamelhump(String inputString) {
        StringBuilder sb = new StringBuilder();

        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if (c == '_') {
                if (sb.length() > 0) {
                    nextUpperCase = true;
                }
            } else {
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
        }
        return sb.toString();
    }
}
