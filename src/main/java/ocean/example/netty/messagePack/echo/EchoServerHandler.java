package ocean.example.netty.messagePack.echo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import ocean.example.netty.messagePack.MsgPackDomain;

import java.util.Map;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/19]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter
{
    private int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception
    {
        //DecodeMyObject(ctx, msg);
        DecodeMap(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
        throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }

    private void DecodeMyObject(ChannelHandlerContext ctx, Object msg)
    {
        MsgPackDomain ms = (MsgPackDomain)msg;
        System.out.println("request [" + ms.getApple() + ms.getTomato() + "]" + ++counter);
        System.out.println(msg.getClass());
        ctx.writeAndFlush(msg);
    }

    private void DecodeMap(ChannelHandlerContext ctx, Object msg)
    {
        Map ms = (Map)msg;
        MsgPackDomain mapMsg= (MsgPackDomain)ms.get(1);
        System.out.println("request [" +  mapMsg.getApple() + mapMsg.getTomato() + "]" + ++counter);
        System.out.println(mapMsg.getFish());
        System.out.println(msg.getClass());
        ctx.writeAndFlush(msg);
    }

}
