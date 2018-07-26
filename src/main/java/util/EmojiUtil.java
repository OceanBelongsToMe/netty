package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/19]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class EmojiUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EmojiUtil.class);

    /**
     * @param str 待转换字符串
     * @return 转换后字符串
     * @throws UnsupportedEncodingException exception
     * @Description 将字符串中的emoji表情转换成可以在utf-8字符集数据库中保存的格式（表情占4个字节，需要utf8mb4字符集）
     */
    public static String emojiConvert2UTF(Object str)
        throws UnsupportedEncodingException
    {
        String patternString = "([\\x{10000}-\\x{10ffff}\ud800-\udfff])";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher((String)str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find())
        {
            try
            {
                matcher.appendReplacement(
                    sb,
                    "[["
                        + URLEncoder.encode(matcher.group(1),
                        "UTF-8") + "]]");
            }
            catch (UnsupportedEncodingException e)
            {
                LOGGER.error("emojiConvert error", e);
                throw e;
            }
        }
        matcher.appendTail(sb);
        LOGGER.debug("emojiConvert " + str + " to " + sb.toString()
            + ", len：" + sb.length());
        return sb.toString();
    }

    /**
     * @param str 转换后的字符串
     * @return 转换前的字符串
     * @throws UnsupportedEncodingException exception
     * @Description 还原utf8数据库中保存的含转换后emoji表情的字符串
     */
    public static String emojiRecovery(String str)
    {
        String patternString = "\\[\\[(.*?)\\]\\]";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);

        StringBuffer sb = new StringBuffer();
        while (matcher.find())
        {
            try
            {
                matcher.appendReplacement(sb,
                    URLDecoder.decode(matcher.group(1), "UTF-8"));
            }
            catch (UnsupportedEncodingException e)
            {
                LOGGER.error("emojiRecovery error", e);
            }
        }
        matcher.appendTail(sb);
        LOGGER.debug("emojiRecovery " + str + " to " + sb.toString());
        return sb.toString();
    }
}
