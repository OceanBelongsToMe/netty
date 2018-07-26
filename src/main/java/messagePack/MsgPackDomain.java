package messagePack;

import org.msgpack.annotation.MessagePackBeans;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/26]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@MessagePackBeans
public class MsgPackDomain
{
    private String apple;

    private String tomato;

    public MsgPackDomain(int x)
    {
        apple = "";
        for (int i = 0; i < x; i++)
        {
            apple += "🍅";
            tomato += "🍎";
        }

    }

    public String getApple()
    {
        return apple;
    }

    public void setApple(String apple)
    {
        this.apple = apple;
    }

    public String getTomato()
    {
        return tomato;
    }

    public void setTomato(String tomato)
    {
        this.tomato = tomato;
    }

    @Override
    public String toString()
    {
        return "MsgPackDomain{" +
            "apple='" + apple + '\'' +
            ", tomato='" + tomato + '\'' +
            '}';
    }
}
