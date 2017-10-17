package com.dfire.soa.consumer.fa.util;

import com.twodfire.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author wangyan
 * @date 2014-11-02 16:41
 */
public final class DateUtils extends org.apache.commons.lang.time.DateUtils {

    /**
     * 默认时间格式
     */
    private static final String FORMAT = "yyyy-MM-dd hh:mm:ss";

    /**
     * 格式化日期
     *
     * @param date    日期
     * @param pattern 格式化日期格式
     * @return 日期字符串
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            throw new IllegalArgumentException("parameter date is required");
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = FORMAT;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 日期字符串转成日期
     *
     * @param date    日期字符串
     * @param pattern 格式化日期格式
     * @return 日期
     */
    public static Date parse(String date, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            throw new IllegalArgumentException("parameter pattern is required ");
        }
        if (date == null) {
            throw new IllegalArgumentException("parameter date is required");
        }
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            throw new BizException(e.getMessage());
        }
    }

    /**
     * 获取当前时间
     *
     * @return 当前天
     */
    public static Date now() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 当前天
     *
     * @return
     */
    public static Date nowDate() {
        return parse(format(now(), "yyyy-MM-dd"), "yyyy-MM-dd");
    }

    public static Date getDate(Date dateTime) {
        return parse(format(dateTime, "yyyy-MM-dd"), "yyyy-MM-dd");
    }

    /**
     * 日期字符串转成日期
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static Date parse(String date) {
        return parse(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化日期
     *
     * @param calendar calendar
     * @param pattern  格式化日期格式
     * @return 日期字符串
     */
    public static String format(Calendar calendar, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            throw new IllegalArgumentException("parameter pattern is required");
        }
        if (calendar == null) {
            throw new IllegalArgumentException("parameter calendar is required");
        }
        return format(calendar.getTime(), pattern);
    }

    /**
     * 格式化当前日期
     *
     * @param pattern 格式化日期格式
     * @return 日期字符串
     */
    public static String formatCurrentDate(String pattern) {
        if (StringUtils.isBlank(pattern)) {
            throw new IllegalArgumentException("parameter pattern is required");
        }
        return format(new Date(), pattern);
    }

    /**
     * 格式化默认日期格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     * @return 日期字符串
     */
    public static String formatDefaultPattern(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("parameter calendar is required");
        }
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化当前日期  yyyy-MM-dd HH:mm:ss
     *
     * @return 默认格式化日期字符串
     */
    public static String formatCurrentDefaultPattern() {
        return format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 时间戳转为date 对象
     *
     * @param timestamp 时间戳
     * @return Date
     */
    public static Date timestampToDate(String timestamp) {
        if (StringUtils.isNumeric(timestamp)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(timestamp));
            return calendar.getTime();
        } else {
            throw new IllegalStateException("timestamp 为数字字符串");
        }
    }

    /**
     * 获取最近时间
     *
     * @param count
     * @return Date
     */
    public static Date getLatestDay(int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -count);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 时间转化为时间戳
     *
     * @param date
     * @return 时间戳
     */
    public static Long dateToTimestamp(Date date) {
        Assert.notNull(date);
        return date.getTime();
    }

    /**
     * 获取当天时间定制
     *
     * @param hourOfDay 小时
     * @param minute    分钟
     * @param second    秒
     * @return 当天时间
     */
    public static Date getCustomDate(int hourOfDay, int minute, int second) {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
        currentDate.set(Calendar.MINUTE, minute);
        currentDate.set(Calendar.SECOND, second);
        currentDate.set(Calendar.MILLISECOND, 0);
        return currentDate.getTime();
    }

    /**
     * 获取本周起始时间
     *
     * @return 本周起始时间
     */
    public static Date getCurrentWeek() {
        Calendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取本月起始时间
     *
     * @return 本月起始时间
     */
    public static Date getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 最近几个月
     *
     * @param number 几个月
     * @return 时间
     */
    public static Date getLastMonth(int number) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MONTH, number * (-1));
        return calendar.getTime();
    }

    /**
     * 获取两个时间的相差多少分钟
     *
     * @param begin begin
     * @param end   end
     * @return 相差多少分钟
     */
    public static long between(Date begin, Date end) {
        long beginL = begin.getTime();
        long endL = end.getTime();
        long result = (endL - beginL) / (1000 * 60);
        return result;
    }

    public static int getBetweenDay(Date begin, Date end) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            begin = sdf.parse(sdf.format(begin));
            end = sdf.parse(sdf.format(end));
            Calendar cal = Calendar.getInstance();
            cal.setTime(begin);
            long time1 = cal.getTimeInMillis();
            cal.setTime(end);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
            throw new BizException("日期格式错误");
        }
    }


    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    public static Date getMonthAfter(Date d, int month) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.MONTH, now.get(Calendar.MONTH) + month);
        return now.getTime();
    }

    public static Date getHourAfter(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    public static Date getMinuteAfter(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    public static Date getSecondAfter(Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    public static int getWeekOfDate(Date data) {
        int[] weekdays = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        int weekDayIndex = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (weekDayIndex < 0) {
            weekDayIndex = 0;
        }
        return weekdays[weekDayIndex];
    }

    public static int getCurHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 根据date获取这一天是星期几
     *
     * @param date 如果为null 返回0
     * @return
     */
    public static int getDayType(Date date) {
        int[] weekdays = {7, 1, 2, 3, 4, 5, 6};
        if (date != null) {
            Calendar calendar = DateUtils.toCalendar(date);
            int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0) {
                w = 0;
            }
            //星期几
            return weekdays[w];
        } else {
            return 0;
        }
    }

    /**
     * 根据date获取今天是星期几 汉字
     *
     * @param date
     * @return
     */
    public static String parseDayType(Date date) {
        String[] weekdays = {"日", "一", "二", "三", "四", "五", "六"};
        if (date != null) {
            Calendar calendar = DateUtils.toCalendar(date);
            int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0) {
                w = 0;
            }
            return weekdays[w];
        } else {
            return "";
        }
    }

    /**
     * 判断一个日期是否在两个日期之间
     *
     * @param date
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean between(Date date, Date startTime, Date endTime) {
        return (startTime.equals(date) || startTime.before(date)) && (endTime.equals(date) || endTime.after(date));
    }
}