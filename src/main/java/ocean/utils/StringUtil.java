package ocean.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class StringUtil {

    /**
     * 日志对象声明
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);

    /**
     * 私有构造方法
     */
    private StringUtil() {

    }


    /**
     * 判断字符串是否为null对象或是空白字符
     *
     * @param str
     * @return boolean
     * @throws
     * @author wangyang
     * @date 2018/7/11 下午6:16
     */
    public static boolean isEmpty(String str) {
        return ((str == null) || (str.trim().length() == 0));
    }

    /**
     * 判断字符串不是否为null对象和空白字符
     *
     * @param str
     * @return boolean
     * @throws
     * @author wangyang
     * @date 2018/7/11 下午6:17
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断两个字符串是否相等(忽略大小写)
     *
     * @param str
     * @param compareStr
     * @return boolean
     * @throws
     * @author wangyang
     * @date 2018/7/11 下午6:20
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
     * @param str
     * @param other
     * @return boolean
     * @throws
     * @author wangyang
     * @date 2018/7/11 下午6:20
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
     * @param str
     * @param other
     * @return boolean
     * @throws
     * @author wangyang
     * @date 2018/7/11 下午6:22
     */
    public static boolean isNotEq(String str, String other) {
        return !isEq(str, other);
    }

    /**
     * 判断字符串和整数是否在字符串上不相等
     *
     * @param str
     * @param other
     * @return boolean
     * @throws
     * @author wangyang
     * @date 2018/7/11 下午6:24
     */
    public static boolean isEq(String str, int other) {
        return String.valueOf(other).equals(str);
    }

    public static boolean isEq(int other, String str) {
        return isEq(str, other);
    }

    /**
     * 对象转字符串，若对象为空，返回默认值
     *
     * @param o
     * @return java.lang.String
     * @throws
     * @author wangyang
     * @date 2018/7/11 下午6:27
     */
    public static String valueOf(Object o) {
        return (o == null) ? null : o.toString();
    }

    /**
     * 判断一个字符串是否全部是整数
     *
     * @param str
     * @return boolean
     * @throws
     * @author wangyang
     * @date 2018/7/11 下午6:28
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
     * @param str
     * @return boolean
     * @throws
     * @author wangyang
     * @date 2018/7/11 下午6:29
     */
    public static boolean isPNumber(String str) {
        if (str != null) {
            return str.matches(".*(\\d){11,}.*");
        } else {
            return false;
        }
    }

    /**
     *  把String类型的val转换成def的类型返回
     *
     * @param str
     * @param def
     * @return java.lang.Number
     * @author wangyang
     * @date 2018/7/11 下午7:22
     * @throws
     */
    public static Number toNumber(String str, Number def) {
        Number result = toNumber(str, def.getClass());
        result = result != null ? result : def;
        return result;
    }

    /**
     * 把val转换成type类型返回 比如说getVal("123",Integer.class) 返回一个123
     *
     * @param val
     * @param type
     * @return T
     * @throws
     * @author wangyang
     * @date 2018/7/11 下午7:21
     */
    public static <T> T toNumber(String val, Class<T> type) {
        T value = null;
        String className = type.getSimpleName();
        if (type == Integer.class) {
            className = "Int";
        }
        String convertMethodName = "parse" + className;
        try {
            Method m = type.getMethod(convertMethodName, String.class);
            value = (T) m.invoke(null, val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void main(String[] args) {
        String str = "2";
        System.out.println(toNumber(str, 3));
    }
}
