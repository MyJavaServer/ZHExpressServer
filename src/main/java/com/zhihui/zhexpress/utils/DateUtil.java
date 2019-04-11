package com.zhihui.zhexpress.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author cc
 * @date 18-7-18 下午4:11
 * -------------目录（可以搜索）-----------
 * 获取指定格式时间字符串
 * 字符串日期转date
 * 日期加减
 * timestamp转日期
 * 获取日期星期数
 * 获取日期月份
 * 获取日期天数
 * 比较日期的早晚
 * 获取指定日期的当天开始时间
 * 获取指定日期的当天结束时间
 * <p>
 * ---------------------------
 */
public class DateUtil {

    public static final String YYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYMMDD = "yyyy-MM-dd";
    public static final String YYMM = "yyyy-MM";
    public static final String HHMMSS = "HH:mm:ss";
    public static final String HHMM = "HH:mm";

    /**
     * 获取指定格式时间字符串
     *
     * @param format 格式
     * @return String格式日期
     */
    public static String getFormatDateStr(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 字符串日期转date
     *
     * @param dateStr 字符串日期   只支持 上面的 3种格式
     * @return date格式日期
     */
    public static Date strToDate(String dateStr) {
        if (dateStr == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(YYMMDDHHMMSS).parse(dateStr);
        } catch (ParseException e) {
            try {
                return new SimpleDateFormat(YYMMDD).parse(dateStr);
            } catch (ParseException e1) {
                try {
                    return new SimpleDateFormat(YYMM).parse(dateStr);
                } catch (ParseException e2) {
                    try {
                        return DateUtil.strToDate(getFormatDateStr(new Date(), YYMMDD) + " " + getFormatDateStr(new SimpleDateFormat(HHMMSS).parse(dateStr), HHMMSS));
                    } catch (ParseException e3) {
                        try {
                            return DateUtil.strToDate(getFormatDateStr(new Date(), YYMMDD) + " " + getFormatDateStr(new SimpleDateFormat(HHMM).parse(dateStr), HHMMSS));
                        } catch (ParseException e4) {
                            return null;
                        }
                    }
                }
            }
        }

    }

    /**
     * 日期加减
     *
     * @param date         日期
     * @param calendarType Calendar.YEAR Calendar.MONTH
     * @param timeCount    多少时间
     * @return date格式日期
     */
    public static Date addDate(Date date, Integer calendarType, int timeCount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarType, timeCount);
        return c.getTime();
    }

    /**
     * timestamp转日期
     *
     * @param timestampString
     * @return
     */
    public static String timeStampToDate(String timestampString) {
        if (timestampString.length() == 10) {
            timestampString += "000";
        }
        Long timestamp = Long.parseLong(timestampString);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
        return date;
    }

    /**
     * 获取日期星期数
     *
     * @param date 日期
     * @return
     */
    public static int getWeekNum(Date date) {
        return getDateNum(date, Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取日期月份
     *
     * @param date 日期
     * @return
     */
    public static int getMonthNum(Date date) {
        return getDateNum(date, Calendar.MONTH);
    }

    /**
     * 获取日期天数
     *
     * @param date 日期
     * @return
     */
    public static int getDayNum(Date date) {
        return getDateNum(date, Calendar.DATE);
    }


    /**
     * 比较日期的早晚
     * d1 比 d2 晚 则返回1
     * d1  d2 相等 返回0
     * d1 比 d2 早 则返回-1
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Integer laterThan(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return null;
        }
        return Long.compare(d1.getTime(), d2.getTime());
    }

    /**
     * 获取指定日期的当天开始时间
     *
     * @param date 日期
     * @return date格式日期
     */
    public static Date getStartTimeOfDay(Date date) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取指定日期的当天结束时间
     *
     * @param date 日期
     * @return date格式日期
     */
    public static Date getEndTimeOfDay(Date date) {
        Calendar todayEnd = Calendar.getInstance();

        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    public static Date getStartDateOfMonth(Date date) {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-01 00:00:00");
        String timeStr = sdf2.format(date); //上月第一天
        return strToDate(timeStr);
    }

    public static Date getEndDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), day, 23, 59, 59);
        return calendar.getTime();
    }

    private static int getDateNum(Date date, int type) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置周一为一周的第一天
        cal.setTime(date);
        return cal.get(type);
    }


    public static boolean isWeekend(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }

    public static Date geLastWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    public static Date getNextWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
    }

    public static int getDayNumOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DAY_OF_WEEK);
        System.out.println(day);
        if (day == 1) {
            day = 8;
        }
        return day - 1;
    }

    public static boolean isFriday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
    }

    /**
     * 获取日期的前一天
     * @param date
     * @return
     */
    public static Date getLastDayOfDay(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH,-1);
        return c.getTime();
    }

}
