/*
 * 文 件 名: StrUtil.java 版 权: Huawei Technologies Co., Ltd. Copyright YYYY-YYYY, All rights reserved 描
 * 述: 字符串工具类 修 改 人: 吴飞00106856 修改时间: 2009-3-12 跟踪单号: <跟踪单号> 修改单号: <修改单号> 修改内容: <修改内容>
 */
package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串工具类
 *
 * @author 吴飞00106856
 * @version CMR20 2013-8-27
 * @since V100R001C01LCM020
 */
public class StrUtil {

    /**
     * 日志对象声明
     */
    private static final Logger logger = LoggerFactory.getLogger(StrUtil.class);

    /**
     * 构造函数
     */
    private StrUtil() {

    }

    /**
     * 错误信息长度限制
     */
    public static String lengthLimitStr = "512";

    /**
     * 允许null对象的trim方法
     *
     * @param str 给定的字符串
     * @return trim后的字符串
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 判断字符串是否为null对象或是空白字符
     *
     * @param str 给定的字符串
     * @return true：字符串为空
     * @author mWX177641
     */
    public static boolean isEmpty(String str) {
        return ((str == null) || (str.trim().length() == 0));
    }

    /**
     * 判断字符串是否不为null对象或是空白字符
     *
     * @param str 给定的字符串
     * @return true：字符串不为空
     * @author mWX177641
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断两个字符串是否相等(忽略大小写)
     *
     * @param str        字符串1
     * @param compareStr 字符串2
     * @return true：相等， false：不相等
     * @author zWX190898
     */
    public static boolean isEqsIgnoreCase(String str, String compareStr) {
        if (str == null || compareStr == null) {
            return false;
        }
        return str.equalsIgnoreCase(compareStr);
    }

    /**
     * 判断两个字符串是否相等
     *
     * @param str   字符串1
     * @param other 字符串2
     * @return true：相等
     * @author mWX177641
     */
    public static boolean isEq(String str, String other) {
        if (str == null) {
            return other == null;
        }
        return str.equals(other);
    }

    /**
     * 判断两个字符串是否不相等
     *
     * @param str   字符串1
     * @param other 字符串2
     * @return true：不相等
     * @author mWX177641
     */
    public static boolean isNotEq(String str, String other) {
        return !isEq(str, other);
    }

    /**
     * 判断字符串和整数是否在字符串上相等
     *
     * @param str   字符串
     * @param other 整数
     * @return true：相等
     * @author mWX177641
     */
    public static boolean isEq(String str, int other) {
        return String.valueOf(other).equals(str);
    }

    /**
     * 判断字符串和整数是否在字符串上不相等
     *
     * @param str   字符串
     * @param other 整数
     * @return true：不相等
     * @author mWX177641
     */
    public static boolean isNotEq(String str, int other) {
        return !isEq(str, other);
    }

    /**
     * 判断字符串和整数是否在字符串上相等
     *
     * @param i   整数
     * @param str 字符串
     * @return true：相等
     * @author mWX177641
     */
    public static boolean isEq(int i, String str) {
        return String.valueOf(i).equals(str);
    }

    /**
     * 判断字符串和整数是否在字符串上不相等
     *
     * @param i   整数
     * @param str 字符串
     * @return true：不相等
     * @author mWX177641
     */
    public static boolean isNotEq(int i, String str) {
        return !isEq(i, str);
    }

    /**
     * 判断该字符串是否与后面某个整型参数在字符串上相等
     *
     * @param base    字符串
     * @param matched 整型数组
     * @return true：相等
     * @author mWX177641
     */
    public static boolean matchs(String base, int... matched) {
        int b;
        try {
            b = Integer.parseInt(base);
        } catch (Exception e) {
            return false;
        }

        for (int i = 0; i < matched.length; i++) {
            if (b == matched[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断 该整数是否与后面的某个整数是否相等
     *
     * @param base    整数
     * @param matched 整型数组
     * @return true：相等
     * @author mWX177641
     */
    public static boolean matchs(int base, int... matched) {
        for (int i = 0; i < matched.length; i++) {
            if (base == matched[i]) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断字符串是否与其后面某个参数相等
     *
     * @param base    字符串
     * @param matched 字符串数组
     * @return true：相等
     * @author mWX177641
     */
    public static boolean matchs(String base, String... matched) {
        for (int i = 0; i < matched.length; i++) {
            if (matched[i].equals(base)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将boolean型转换为字符串
     *
     * @param b boolean值
     * @return 1：true，0：false
     * @author mWX177641
     */
    public static String valueOf(boolean b) {
        return b ? "1" : "0";
    }

    /**
     * 对象转换为String
     *
     * @param o 对象
     * @return 如果对象为null，则会返回null对象，而不是返回字符串"null"
     * @author mWX177641
     */
    public static String valueOf(Object o) {
        return (o == null) ? null : o.toString();
    }

    /**
     * 将int型转换为字符串
     *
     * @param i int值
     * @return 字符串
     * @author mWX177641
     */
    public static String valueOf(int i) {
        return String.valueOf(i);
    }

    /**
     * 对象转字符串，若对象为空，返回默认值
     *
     * @param o   对象
     * @param def 默认值
     * @return
     * @author mWX177641
     */
    public static String valueOf(Object o, String def) {
        return (o == null) ? def : o.toString();
    }

    /**
     * 将long型转换为字符串
     *
     * @param l long值
     * @return 字符串
     * @author mWX177641
     */
    public static String valueOf(long l) {
        return String.valueOf(l);
    }

    /**
     * 将double型转换为字符串
     *
     * @param d double值
     * @return 字符串
     * @author mWX177641
     */
    public static String valueOf(double d) {
        return String.valueOf(d);
    }


    /**
     * 判断一个字符串是否全部是整数
     *
     * @param str 字符串
     * @return true：是整数
     */
    public static boolean isNumber(String str) {
        if (str != null) {
            return str.matches("-?\\d+");
        } else {
            return false;
        }
    }

    /**
     * 判断一个字符串是否全部是正整数
     *
     * @param str 字符串
     * @return true：是整数
     */
    public static boolean isPNumber(String str) {
        if (str != null) {
            return str.matches(".*(\\d){11,}.*");
        } else {
            return false;
        }
    }

    /**
     * 将String类型转换为Int类型,当转换失败时返回默认值
     *
     * @param str 待转换的字符串
     * @param def 默认值
     * @return
     * @author c00126000
     */
    public static int toInt(String str, int def) {
        int result = def;
        try {
            result = Integer.parseInt(str);
        } catch (Exception e) {
            return result;
        }
        return result;
    }

    /**
     * 将String类型转换为Double类型,当转换失败时返回默认值
     *
     * @param str 待转换的字符串
     * @param def 默认值
     * @return
     * @author c00126000
     */
    public static double toDouble(String str, Double def) {
        double result = def;
        try {
            result = Double.parseDouble(str);
        } catch (Exception e) {
            return result;
        }
        return result;
    }

    /** modified by yukuangzhou yKF72055 at 2012-6-27 for CMR13_CPortal_REQ-555 begin */
    /**
     * 字符串转换为整形
     *
     * @param str 字符串
     * @return 整型值
     * @author mWX177641
     */
    public static Integer toInteger(String str) {
        try {
            if (str == null) {
                return null;
            }

            DecimalFormat dcmFmt = new DecimalFormat("#0");
            String strDouble = dcmFmt.format(Double.parseDouble(str));
            return Integer.parseInt(strDouble);
        } catch (Exception e) {
            return null;
        }
    }

    /** modified by yukuangzhou yKF72055 at 2012-6-27 for CMR13_CPortal_REQ-555 end */

    /**
     * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
     *
     * @param origin 原始字符串
     * @param len    截取长度(一个汉字长度按2算的)
     * @return 返回的字符串
     * @author patriotlml
     */
    public static String subChinseseStr(String origin, int len) {
        if (origin == null || origin.equals("") || len < 1)
            return "";
        byte[] strByte = new byte[len];
        if (len > length(origin)) {
            return origin;
        }

        // modify by c00126000 at 2011-12-13 for REQ-826 begin
        try {
            System.arraycopy(origin.getBytes("GBK"), 0, strByte, 0, len);
            int count = 0;
            for (int i = 0; i < len; i++) {
                int value = (int) strByte[i];
                if (value < 0) {
                    count++;
                }
            }
            if (count % 2 != 0) {
                len = (len == 1) ? ++len : --len;
            }
            return new String(strByte, 0, len, "GBK");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
        // modify by c00126000 at 2011-12-13 for REQ-826 end
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     *
     * @param s 需要得到长度的字符串
     * @return 得到的字符串长度
     */
    public static int length(String s) {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }

    // add by c00126000 at 2011-12-27 for IRD-21884 begin

    /**
     * 计算字符串的字符长度（中文、英文均按一个字符计算） 如："中文abc" 长度为 5
     *
     * @param s 字符串
     * @return 返回的字符串长度
     * @author mWX177641
     */
    public static int lengthc(String s) {
        if (s == null) {
            return 0;
        }
        return s.length();
    }

    // add by c00126000 at 2011-12-27 for IRD-21884 end

    /**
     * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
     *
     * @param c 指定的字符
     * @return true:Ascill字符
     * @author mWX177641
     */
    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    // modify by wufei00106856 at 2011-12-13 for REQ-930 begin

    /**
     * 分隔字符串
     *
     * @param str   被分隔的字符串
     * @param regex 分隔符
     * @return 分隔得到的数组
     * @author mWX177641
     */
    public static String[] split(String str, String regex) {
        if (str == null) {
            return new String[]{""};
        }
        if (regex == null) {
            return new String[]{str};
        }

        return str.split(regex);
    }

    // modify by wufei00106856 at 2011-12-13 for REQ-930 end

    // modify by hKF48608 at Feb 6, 2012 for WORK-6522 begin

    /**
     * 判断输入的字符串是否是可以转换为boolean类型的true/false
     *
     * @param str 给定的字符串
     * @return true：可以转化成boolean
     * @author hKF48608 hexiuli
     */
    public static boolean isBooleanType(String str) {
        String tmpStr = trim(str);
        if (null == tmpStr) {
            return false;
        } else if (tmpStr.equalsIgnoreCase("true") || tmpStr.equalsIgnoreCase("false")) {
            return true;
        } else {
            return false;
        }
    }

    // modify by hKF48608 at Feb 6, 2012 for WORK-6522 end

    // add by c00126000 at 2012-2-13 for IRD-22403 begin

    /**
     * 判断字符串是否以标点符号结尾（句号、逗号、分号）
     *
     * @param str 给定的字符串
     * @return true：以标点符号结尾
     * @author mWX177641
     */
    public static boolean endWithPunctuation(String str) {
        if (isEmpty(str)) {
            return false;
        }

        return str.endsWith("。") || str.endsWith(".") || str.endsWith("，") || str.endsWith(",")
                || str.endsWith("；") || str.endsWith(";") || str.endsWith("!") || str.endsWith("！");
    }

    // add by c00126000 at 2012-2-13 for IRD-22403 end

    /**
     * 判断字符串是否包含字符串
     *
     * @param str  字符串1
     * @param mark 字符串2
     * @return true： 字符串1包含字符串2
     * @author mWX177641
     */
    public static boolean containMark(String str, String mark) {
        if (isEmpty(str)) {
            return false;
        }

        return str.contains(mark);
    }


    /**
     * 子字符串是否包含在字符串列表中
     *
     * @param str     字符串
     * @param strList 字符串列表
     * @return true：包含
     */
    public static boolean containSubStr(String str, String[] strList) {
        boolean containSub = false;
        if (strList.length == 0 || isEmpty(str)) {
            containSub = false;
        }
        for (int i = 0; i < strList.length; i++) {

            if (!strList[i].isEmpty()) {
                if (str.startsWith(strList[i])) {
                    containSub = true;
                }
            }

        }

        return containSub;
    }

    /**
     * 组装错误信息
     *
     * @param errorInfoList 错误信息集合
     * @return 返回的错误信息
     * @author KF71109
     */
    public static String getErrorInfo(List<String> errorInfoList) {
        int listLength = errorInfoList.size();
        int lengthLimitInt = Integer.parseInt(lengthLimitStr);
        if (listLength == 2) {
            return StrUtil.subChinseseStr(errorInfoList.get(0) + "=" + errorInfoList.get(1),
                    lengthLimitInt);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i + 1 < listLength; i = i + 2) {
                sb = sb.append(errorInfoList.get(i)).append("=").append(errorInfoList.get(i + 1))
                        .append(",");
            }
            return StrUtil.subChinseseStr(sb.toString(), lengthLimitInt);
        }
    }

    /**
     * 替换“+”和空格
     *
     * @param str 元字符串
     * @return 替换后的字符串
     * @author zKF47558
     */
    public static String replaceStr(String str) {
        if (null == str) {
            return "";
        }
        Pattern p = Pattern.compile("\\+|\\s*");

        Matcher m = p.matcher(str);

        String after = m.replaceAll("");

        return after;
    }

    /**
     * 近似匹配，找到比数组中小的，最接近输入值的数
     *
     * @param source
     * @param matched
     * @return
     * @author g00216904
     */
    public static int match(int source, int... matched) {
        int x = 0;
        int target = 0;
        for (int i = 0; i < matched.length; i++) {
            if (source >= matched[i]) {
                x = matched[i] - source;

                if (Math.abs(x) < Math.abs(target - source)) {
                    target = matched[i];
                }
            }
        }
        return target;
    }

    /**
     * 用指定的分隔符号拆分字符串
     *
     * @param srcStr 待拆分的字符串
     * @param sep    分隔符号
     * @return 返回拆分后的字符串列表
     * @author r00138849
     */
    public static List<String> splitString(String srcStr, String sep) {
        List<String> list = new ArrayList<String>();
        if (!isEmpty(srcStr)) {
            String[] temp = srcStr.split(sep);
            for (int i = 0; i < temp.length; i++) {
                list.add(temp[i]);
            }
        }
        return list;
    }

    /**
     * 将列表用指定的符号组装成String
     *
     * @param source    待组装的列表
     * @param seperator 分隔符
     * @return 组装后的字符串
     * @author kf56385
     */
    public static String parseListToStrBySeperator(List<String> source, String seperator) {
        String result = "";
        // 参数检查
        if (isEmpty(source)) {
            return result;
        }
        // 执行组装
        for (int i = 0; i < source.size(); i++) {
            String str = source.get(i);

            if (i == source.size() - 1) {
                result += str;
            } else {
                result += str + seperator;
            }
        }
        return result;
    }

    /**
     * 验证集合是否为null或为空集
     *
     * @param c,需要判断的集合
     * @return true：null或为空集
     * @author mWX177641
     */
    public static boolean isEmpty(Collection<?> c) {
        return (c == null) || c.isEmpty();
    }

    /**
     * 验证集合是否为null或为空集
     *
     * @param leftStr,需要判断的集合
     * @return true：null或为空集
     * @author mWX177641
     */
    public static String joint(String leftStr, String... rightStr) {
        StringBuffer sb = new StringBuffer();

        sb.append(leftStr);

        for (String str : rightStr) {
            sb.append(str);
        }

        return sb.toString();
    }

    /**
     * 对null对象进行赋值
     *
     * @param o 对象
     * @return String
     * @author wWX190899
     */
    public static String nvl(Object o) {
        return (null == o) ? "" : o.toString().trim();
    }

    /**
     * 校验支付方式是否为数字和英文逗号组合
     *
     * @param supportPayType
     * @return true：数字或者数字加逗号加数字
     * @author wzh
     */
    public static boolean reg(String supportPayType) {
        String regex = "(\\d+(,\\d+)?)";
        return supportPayType.matches(regex);
    }

    /**
     * 判断指定的对象是否为空
     *
     * @param obj Object
     * @return boolean
     */
    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        }
        return false;
    }

    //是否为数字的判断
    public static boolean isNumeric(String s) {
        if (s != null && !"".equals(s.trim()))
            return s.matches("^[0-9]*$");
        else
            return false;
    }

    public static List<Integer> toIntList(String excludeTypes) {
        if (StrUtil.isEmpty(excludeTypes)) {
            return null;
        }
        String[] arrays = excludeTypes.split(",");
        List<Integer> list = new ArrayList<Integer>(arrays.length);
        for (String types : arrays) {
            try {
                list.add(Integer.parseInt(types));
            } catch (Exception e) {
                logger.error(" StrUtil [ toList ] is not int:" + types, e);
            }

        }
        return list;
    }

    /**
     * 以某个分割符分割的字符串转换成Map
     */
    public static Map<String, String> parseStrToMap(String source, String seperator) {
        // 参数检查
        if (source == null || seperator == null) {
            return null;
        }

        // 执行分拆
        String[] temp = source.split("\\" + seperator);
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < temp.length; i++) {
            if (StrUtil.isEmpty(temp[i])) {
                continue;
            }
            map.put(temp[i], temp[i]);
        }
        return map;
    }


    public static List<String> toList(String ids) {
        if (StrUtil.isEmpty(ids)) {
            return null;
        }
        String[] arrays = ids.split(",");
        List<String> list = new ArrayList<String>(arrays.length);
        for (String id : arrays) {
            list.add(id);
        }
        return list;
    }

    /**
     * 将转码后的unicode字符串，转成汉字
     *
     * @param unicodeStr unicode字符串
     * @return 汉字
     * @author shanlingling
     */
    public static String unicodeToString(String unicodeStr) {
        if (StrUtil.isEmpty(unicodeStr)) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }

    public static String asDefault(String s, String def) {
        if ((s == null) || "".equals(s.trim())) {
            return def;
        } else {
            return s;
        }
    }


    public static int StrtoInt(String s, int def) {
        int value = def;
        try {
            if (StrUtil.isNotEmpty(s)) {
                value = Integer.parseInt(s);
            } else {
                value = def;
            }
        } catch (Exception e) {
            value = def;
        }
        return value;
    }


}
