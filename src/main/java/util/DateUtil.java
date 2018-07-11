package util;

import exception.MyCodeException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class DateUtil {


    /**
     * 每月的开始日期，默认为自然月开始，即每月的1号
     */
    private static int beginDay = 1;

    /**
     * 0
     */
    public static final int YEAR = 0;

    /**
     * 1
     */
    public static final int MONTH = 1;

    /**
     * 2
     */
    public static final int WEEK = 2;

    /**
     * 3
     */
    public static final int DAY = 3;

    /**
     * 4
     */
    public static final int HOUR = 4;

    /**
     * 5
     */
    public static final int MINUTE = 5;

    /**
     * 6
     */
    public static final int SECOND = 6;

    /**
     * 7
     */
    public static final int MILLISECOND = 7;

    /**
     * 8
     */
    public static final int MINUTEOFDAY = 8;

    /**
     * 缺省的日期字符串格式
     */
    public static final String DEFAULT_FORMAT = "yyyyMMddHHmmss";

    /**
     * 带连字符"-"精确到时间的字符串格式
     */
    public static final String HOR_SEC_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 年月日时分秒时间格式
     */
    public static final String YYYYMMDDHHMMSS_FORMAT = "yyyyMMddHHmmss";

    /**
     * 带连字符"-"精确到天的字符串格式
     */
    public static final String HOR_DAY_FORMAT = "yyyy-MM-dd";

    /**
     * 带连字符"-"年月日时分秒时间格式
     */
    public static final String YYYY_MM_DDHHMMSS_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 不带连字符精确到天的字符串格式
     */
    public static final String NOHOR_DAY_FORMAT = "yyyyMMdd";

    /**
     * 手机报对账时间格式串
     */
    public static final String RECON_DAY_FORMAT = "yyyyMMdd-HH:mm:ss";

    /**
     * yyyyMMddHH
     */
    public static final String YEAR_MONTH_DAY_HOUR_FORMAT = "yyyyMMddHH";

    /**
     * 年月时间
     */
    public static final String YEAR_MONTH_FORMAT = "yyyy-MM";

    /**
     * 年月时间
     */
    public static final String YEAR_MONTH_YYYYMM_FORMAT = "yyyyMM";

    /**
     * 只有年份的格式
     */
    public static final String YEAR_FORMAT = "yyyy";

    /**
     * 只有月份的格式
     */
    public static final String MONTH_FORMAT = "MM";

    /**
     * 只有年份的长度
     */
    public static final int YEAR_LENTH = 4;

    /**
     * 只有年份和月份的长度
     */
    public static final int YEAR_MONTH_LENTH = 6;

    /**
     * 只有年份、月份和日期的长度
     */
    public static final int YEAR_MONTH_DATE_LENTH = 8;

    /**
     * 只有年份、月份、日期和小时的长度
     */
    public static final int YEAR_MONTH_DATE_HOUR_LENTH = 10;

    /**
     * 只有年份、月份、日期、小时和分钟的长度
     */
    public static final int YEAR_MONTH_DATE_HOUR_MINUTE_LENTH = 12;

    /**
     * 有年份、月份、日期、小时、分钟和秒的长度
     */
    public static final int YEAR_MONTH_DATE_HOUR_MINUTE_SECOND_LENTH = 14;

    /**
     * 一天24小时
     */
    public static final int DAY_HOUR = 24;

    /**
     * UTC时间格式
     */
    public static final String UTC_DATETIME_FORMAT = "yyyyMMddHHmmssSSS";

    /**
     * 时分的正则表达式
     */
    public static final String HOUR_MIN_REX = "^([0-1]?[0-9]|2[0-3]):([0-5][0-9])$";

    /**
     * 构造函数
     */
    private DateUtil() {

    }

    /**
     * 根据日期字符串自动匹配生成日期
     *
     * @param dateString String
     * @return 返回日期格式
     * @author s00215221
     */
    public static Date getDate(String dateString) {
        Date date = null;
        if (dateString.contains(":")) {
            date = formTimestamp(dateString, HOR_SEC_FORMAT);
        } else if (dateString.contains("-")) {
            date = formTimestamp(dateString, HOR_DAY_FORMAT);
        } else if (dateString.length() == 14) {
            date = formTimestamp(dateString, DEFAULT_FORMAT);
        } else if (dateString.length() == 8) {
            date = formTimestamp(dateString, NOHOR_DAY_FORMAT);
        }
        return date;
    }

    /**
     * 格式化为 Timestamp
     *
     * @param dateStr  String
     * @param formater String
     * @return Timestamp
     */
    public static Timestamp formTimestamp(String dateStr, String formater) {
        return new Timestamp(formdate(dateStr, formater).getTime());
    }

    /**
     * 格式化为 Timestamp
     *
     * @param date Date
     * @return Timestamp
     */
    public static Timestamp formTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    /**
     * 把Date格式化为Timestamp，带默认值
     *
     * @param date
     * @param DEFAULT
     * @return
     */
    public static Timestamp formTimestamp(Date date, Timestamp DEFAULT) {
        return (null == date) ? DEFAULT : formTimestamp(date);
    }

    /**
     * 得到当前时间的字符格式
     *
     * @return String
     */
    public static String getCurrentTime() {
        return format(new Date());
    }

    /**
     * 得到指定格式的Date型当前时间
     *
     * @param formater String
     * @return Date
     */
    public static Date getCurrentDate(String formater) {
        return formdate(getCurrentTime(), formater);
    }

    /**
     * 得到当前时间
     *
     * @return Timestamp
     */
    public static Timestamp getCurrentDatetime() {
        return getDateTime(System.currentTimeMillis());
    }

    /**
     * 获取指定毫秒数对应的时间
     *
     * @param timeStamp 指定的毫秒数
     * @return 指定毫秒数对应的而时间
     * @author l00239363
     */
    public static Timestamp getDateTime(long timeStamp) {
        return new Timestamp(timeStamp);
    }

    /**
     * 格式化成系统常用日期格式：yyyyMMddHHmmss
     *
     * @param date Date
     * @return String
     */
    public static String format(Date date) {
        return format(date, "yyyyMMddHHmmss");
    }

    /**
     * 格式化日期
     *
     * @param formatStr String
     * @param date      Date
     * @return String
     */
    public static String format(Date date, String formatStr) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat sf = new SimpleDateFormat(formatStr);
        return sf.format(date);
    }

    /**
     * 将指定的日期字符串转换为Timestamp类型
     *
     * @param dateStr String
     * @return Timestamp
     * @author l65566
     */
    public static Timestamp parse(String dateStr) {
        return parse(dateStr, "yyyyMMddHHmmss");
    }

    /**
     * 根据指定的格式将指定的日期字符串转换为Timestamp类型
     *
     * @param dateStr   String
     * @param formatStr String
     * @return Timestamp
     * @author l65566
     */
    public static Timestamp parse(String dateStr, String formatStr) {
        if (null == dateStr) {
            return null;
        }

        Date date = null;
        SimpleDateFormat sf = new SimpleDateFormat(formatStr);
        try {
            date = sf.parse(dateStr);
        } catch (ParseException e) {
        }

        if (null != date) {
            return new Timestamp(date.getTime());
        } else {
            return null;
        }
    }

    /**
     * 把字符串格式化日期。
     *
     * @param dateStr       Date
     * @param dateFormatStr String
     * @return Date
     */
    public static Date formdate(String dateStr, String dateFormatStr) {
        String dateFormatStrNotNull = (null == dateFormatStr) ? "yyyy-MM-dd HH:mm:ss" : dateFormatStr;
        DateFormat formatter = new SimpleDateFormat(dateFormatStrNotNull);
        Date date = null;
        try {
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
        }

        return date;
    }

    /**
     * CompareDateFormate
     *
     * @author lWX160046
     * @version C10 2014年10月23日
     * @since SDP V300R003C10
     */
    public static enum CompareDateFormate {
        /**
         * 时间格式
         */
        year, month, day, hour, minute, second,

        /**
         * 时间格式
         */
        yyyyMMddhhmmss, yyyyMMddhhmm, yyyyMMddhh, yyyyMMdd, yyyyMM,

        /**
         * 时间格式
         */
        MMddhhmmss, MMddhhmm, MMddhh, MMdd, ddhhmmss, ddhhmm, ddhh, hhmmss, hhmm, mmss
    }

    private static final HashMap<CompareDateFormate, int[]> map = new HashMap<CompareDateFormate, int[]>();

    static {
        map.put(CompareDateFormate.year, new int[]{Calendar.YEAR});
        map.put(CompareDateFormate.month, new int[]{Calendar.MONTH});
        map.put(CompareDateFormate.day, new int[]{Calendar.DATE});
        map.put(CompareDateFormate.hour, new int[]{Calendar.HOUR_OF_DAY});
        map.put(CompareDateFormate.minute, new int[]{Calendar.MINUTE});
        map.put(CompareDateFormate.second, new int[]{Calendar.SECOND});

        map.put(CompareDateFormate.yyyyMMddhhmmss, new int[]{Calendar.YEAR, Calendar.MONTH, Calendar.DATE,
                Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND});
        map.put(CompareDateFormate.yyyyMMddhhmm, new int[]{Calendar.YEAR, Calendar.MONTH, Calendar.DATE,
                Calendar.HOUR_OF_DAY, Calendar.MINUTE});
        map.put(CompareDateFormate.yyyyMMddhh, new int[]{Calendar.YEAR, Calendar.MONTH, Calendar.DATE,
                Calendar.HOUR_OF_DAY});
        map.put(CompareDateFormate.yyyyMMdd, new int[]{Calendar.YEAR, Calendar.MONTH, Calendar.DATE});
        map.put(CompareDateFormate.yyyyMM, new int[]{Calendar.YEAR, Calendar.MONTH});

        map.put(CompareDateFormate.MMddhhmmss, new int[]{Calendar.MONTH, Calendar.DATE, Calendar.HOUR_OF_DAY,
                Calendar.MINUTE, Calendar.SECOND});
        map.put(CompareDateFormate.MMddhhmm, new int[]{Calendar.MONTH, Calendar.DATE, Calendar.HOUR_OF_DAY,
                Calendar.MINUTE});
        map.put(CompareDateFormate.MMddhh, new int[]{Calendar.MONTH, Calendar.DATE, Calendar.HOUR_OF_DAY});
        map.put(CompareDateFormate.MMdd, new int[]{Calendar.MONTH, Calendar.DATE});

        map.put(CompareDateFormate.ddhhmmss, new int[]{Calendar.DATE, Calendar.HOUR_OF_DAY, Calendar.MINUTE,
                Calendar.SECOND});
        map.put(CompareDateFormate.ddhhmm, new int[]{Calendar.DATE, Calendar.HOUR_OF_DAY, Calendar.MINUTE});
        map.put(CompareDateFormate.ddhh, new int[]{Calendar.DATE, Calendar.HOUR_OF_DAY});

        map.put(CompareDateFormate.hhmmss, new int[]{Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND});
        map.put(CompareDateFormate.hhmm, new int[]{Calendar.HOUR_OF_DAY, Calendar.MINUTE});
        map.put(CompareDateFormate.mmss, new int[]{Calendar.MINUTE, Calendar.SECOND});
    }

    /**
     * 判断两个日期是否为同年同月
     *
     * @param date1 Date
     * @param date2 Date
     * @return boolean
     */
    public static boolean isSameYYYYMM(Date date1, Date date2) {
        return compare(date1, date2, CompareDateFormate.yyyyMM) == 0;
    }

    /**
     * 判断两个日期是否为同年同月同日
     *
     * @param date1 第一个日期
     * @param date2 第二个日期
     * @return 如果年月日相同返回true，否则返回false
     * @author r00138849
     */
    public static boolean isSameYYYYMMDD(Date date1, Date date2) {
        return compare(date1, date2, CompareDateFormate.yyyyMMdd) == 0;
    }

    /**
     * 获取下个月开始时刻（该时刻根据配置得来）
     *
     * @return Timestamp
     */
    public static Timestamp getBeginOfNextMonth() {
        return getBeginOfNextMonth(new Date());
    }

    /**
     * 根据CompareFields的格式（如只比较年月）比较两个日期先后，
     * <p>
     * 在比较字段内，若返回1，表示date1在date2之后，返回-1，表示date1在date2之前，0表示两者相等
     *
     * @param date1 Date
     * @param date2 Date
     * @param cdf   CompareDateFormate
     * @return int
     */
    public static int compare(Date date1, Date date2, CompareDateFormate cdf) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);

        int[] form = map.get(cdf);
        for (int field : form) {
            int t1 = c1.get(field);
            int t2 = c2.get(field);
            if (t1 > t2) {
                return 1;
            } else if (t1 < t2) {
                return -1;
            }
        }

        return 0;
    }

    /**
     * 获取下个月开始时刻（该时刻根据配置得来）
     *
     * @param date Date
     * @return Timestamp
     */
    public static Timestamp getBeginOfNextMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, beginDay);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // 月份加1
        c.add(Calendar.MONTH, 1);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取指定时间后N个月开始时间
     *
     * @param date Date
     * @param N    int
     * @return Timestamp
     * @author tWX149479
     */
    public static Timestamp getBeginOfNextNMotnh(Date date, int N) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, beginDay);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // 月份加1
        c.add(Calendar.MONTH, N);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取月末时刻
     *
     * @param date Date
     * @return Timestamp
     */
    public static Timestamp getEndOfCurMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, beginDay);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // 月份加1
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.SECOND, -1);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取月末开始时刻
     *
     * @param date Date
     * @return Timestamp
     */
    public static Timestamp getBeginEndOfCurMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, beginDay);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // 月份加1
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.HOUR_OF_DAY, -24);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取下个月开始时刻（该时刻根据配置得来）
     *
     * @param date Date
     * @return Timestamp
     */
    public static Timestamp getBeginOfPrevMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, beginDay);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // 月份加1
        c.add(Calendar.MONTH, -1);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取下个月开始时刻
     *
     * @param date Date
     * @return Timestamp
     */
    public static Timestamp getBeginOfNextMonthAbsolute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // 月份加1
        c.add(Calendar.MONTH, 1);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取当月开始时间
     *
     * @param date Date
     * @return Timestamp
     */
    public static Timestamp getBeginOfCurrentMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取指定天数的开始时刻
     *
     * @param date Date
     * @param days int
     * @return Timestamp
     * @author wkf50567
     */
    public static Timestamp getBeginOfDay(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        // 日期增加
        c.add(Calendar.DATE, days);

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取指定时间往后顺延指定月和小时的时间
     *
     * @param date  Date
     * @param month int
     * @param hour  int
     * @return Timestamp
     * @author tWX149479
     */
    public static Timestamp getAddTime(Date date, int month, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 月增加
        c.add(Calendar.MONTH, month);
        // 小时增加
        c.add(Calendar.HOUR_OF_DAY, hour);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取增加的月的时间
     *
     * @param date Date
     * @param hour int
     * @return Timestamp
     */
    public static Timestamp getAddMonthDate(Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 月增加
        c.add(Calendar.MONTH, hour);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取增加的小时的时间
     *
     * @param date Date
     * @param hour int
     * @return Timestamp
     */
    public static Timestamp getAddHourDate(Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 小时增加
        c.add(Calendar.HOUR_OF_DAY, hour);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取增加的分钟的时间
     *
     * @param date Date
     * @param minu int
     * @return Timestamp
     */
    public static Timestamp getAddMinuDate(Date date, int minu) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 分钟增加
        c.add(Calendar.MINUTE, minu);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取增加的秒的时间
     *
     * @param date Date
     * @param miss int
     * @return Timestamp
     */
    public static Timestamp getAddMissDate(Date date, int miss) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 秒增加
        c.add(Calendar.SECOND, miss);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取当天开始时间
     *
     * @param date Date
     * @return Timestamp
     */
    public static Timestamp getBeginDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取当天结束时间
     *
     * @param date Date
     * @return Timestamp
     */
    public static Timestamp getEndDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 某个时间是否在两个时间段之间
     *
     * @param begintime Timestamp
     * @param endtime   Timestamp
     * @param nowTimes  Timestamp
     * @return boolean
     */
    public static boolean isBetweenTime(Timestamp begintime, Timestamp endtime, Timestamp nowTimes) {
        if (nowTimes.compareTo(begintime) != -1 && nowTimes.compareTo(endtime) != 1) {
            return true;
        }

        return false;
    }

    // modify by kf56385 at 2011-11-11 for IRD-21034 begin

    /**
     * 某个时间是否在两个时间段之间，包含起始时间，不包含结束时间
     *
     * @param begintime Timestamp
     * @param endtime   Timestamp
     * @param nowTimes  Timestamp
     * @return boolean
     */
    public static boolean isBetweenTimeEx(Timestamp begintime, Timestamp endtime, Timestamp nowTimes) {
        if (!nowTimes.before(begintime) && nowTimes.before(endtime)) {
            return true;
        }

        return false;
    }

    // modify by kf56385 at 2011-11-11 for IRD-21034 end

    /**
     * 某个时间是否在两个时间段之间
     *
     * @param beginDate   Date
     * @param endDate     Date
     * @param compareDate Date
     * @return boolean
     */
    public static boolean isBetweenDate(Date beginDate, Date endDate, Date compareDate) {
        if (compareDate.compareTo(beginDate) != -1 && compareDate.compareTo(endDate) != 1) {
            return true;

        }
        return false;
    }

    /**
     * 把字符串格式化制定的日期
     *
     * @param dateStr    Date
     * @param formater   String
     * @param resultCode int
     * @return Date
     * @throws MyCodeException 参数异常
     */
    public static Date formateToDate(String dateStr, String formater, int resultCode)
            throws MyCodeException {
        String formaterNotNull = (null == formater) ? "yyyy-MM-dd HH:mm:ss" : formater;
        DateFormat formatter = new SimpleDateFormat(formaterNotNull);
        Date date = null;
        try {
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            throw new MyCodeException(resultCode);
        }
        return date;
    }

    /**
     * 获取时间并指定小时
     *
     * @param date Date
     * @param hour int
     * @return Timestamp
     */
    public static Timestamp getDateForHour(Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取前一天时间并指定小时
     *
     * @param date Date
     * @param hour int
     * @param num  int
     * @return Timestamp
     */
    public static Timestamp getPreDateForHour(Date date, int hour, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, num);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return new Timestamp(c.getTimeInMillis());
    }

    public static Date strToDate(String strDate) {
        return strToDate(strDate, DEFAULT_FORMAT);
    }

    public static Date strToDate(String strDate, String strFormat) {
        // 如果入参为null则直接返回null
        if (StrUtil.isEmpty(strDate) || StrUtil.isEmpty(strFormat)) {
            return null;
        }

        SimpleDateFormat df = null;
        Date date = null;
        try {
            // 使用指定的格式创建日期时间格式
            df = new SimpleDateFormat(strFormat);
        } catch (IllegalArgumentException e) {
            df = new SimpleDateFormat(DateUtil.DEFAULT_FORMAT);
        }

        try {
            // 解析指定的字符串
            date = df.parse(strDate);
        } catch (ParseException e) {
            // 如果解析失败则使用当前日期
            date = new Date();
        }

        return date;
    }

    /**
     * 获取当前时间变化后的时间
     *
     * @param type        int
     * @param changeValue int
     * @return Timestamp
     */
    public static Timestamp getBeforeCurrentTime(int type, int changeValue) {
        Calendar calendar = Calendar.getInstance();
        switch (type) {
            case YEAR:
                calendar.add(Calendar.YEAR, changeValue);
                break;
            case MONTH:
                calendar.add(Calendar.MONTH, changeValue);
                break;
            case WEEK:
                calendar.add(Calendar.DAY_OF_WEEK, changeValue);
                break;
            case DAY:
                calendar.add(Calendar.DAY_OF_MONTH, changeValue);
                break;
            case HOUR:
                calendar.add(Calendar.HOUR, changeValue);
                break;
            case MINUTE:
                calendar.add(Calendar.MINUTE, changeValue);
                break;
            case SECOND:
                calendar.add(Calendar.SECOND, changeValue);
                break;
            case MILLISECOND:
                calendar.add(Calendar.MILLISECOND, changeValue);
            default:
                break;
        }

        return new Timestamp(calendar.getTimeInMillis());
    }


    /**
     * 获取当前时间（默认时间格式：yyyy-MM-dd HH:mm:ss）
     *
     * @param formater 时间格式
     * @return 当前时间
     * @author lWX180672
     */
    public static String GetNowDate(String formater) {
        formater = (null == formater) ? "yyyy-MM-dd HH:mm:ss" : formater;
        String temp_str = "";
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        temp_str = sdf.format(dt);
        return temp_str;
    }


    /**
     * 本方法用于比较 当前时间d1在失效时间d2的前N(days)天内，是的话返回true,本方法精确到秒
     *
     * @param d1   当前时间
     * @param d2   失效时间
     * @param days 前N天内
     * @return
     * @author wangzejun
     */
    public static boolean isInBeforeDaysSecond(Date d1, Date d2, int days) {
        if (null == d1 || null == d2) {
            return false;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d1);
        c.add(Calendar.DATE, days);
        Date newDate = c.getTime();

        if (d2.after(d1) && newDate.after(d2) || d1.equals(d2) || newDate.equals(d2)) {
            return true;
        }
        return false;
    }


    /**
     * 根据时间格式,获取当前时间戳
     *
     * @param format
     * @return
     * @author Yhh
     */
    public static String getCurrentTime(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, new Locale("EN"));
        return sdf.format(date);
    }
}
