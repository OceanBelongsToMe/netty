package ocean.example.messagePack;

import org.msgpack.annotation.Index;
import org.msgpack.annotation.Message;

import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/26]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Message
public class MsgPackDomain implements Serializable
{

    private static final long serialVersionUID = -3073612906010212754L;

    private String apple;

    private transient String tomato;

    private List<String> fish = new ArrayList<>();

    public MsgPackDomain()
    {
    }

    public MsgPackDomain(int x)
    {
        apple = "";
        tomato = "";
        for (int i = 0; i < x; i++)
        {
            apple += "🍅🌶🍏🌽🥚🍟🥗";
            tomato += "🍎🥔";
            fish.add("🐠🐠🐟");
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

    public void setFish(List<String> fish)
    {
        this.fish = fish;
    }

    public List<String> getFish()
    {
        return fish;
    }

    @Override
    public String toString()
    {
        return "MsgPackDomain{" +
            "apple='" + apple + '\'' +
            ", tomato='" + tomato + '\'' +
            ", fish=" + fish +
            '}';
    }
}
