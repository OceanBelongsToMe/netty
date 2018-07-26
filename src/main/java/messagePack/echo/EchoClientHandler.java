package messagePack.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import messagePack.MsgPackDomain;
import messagePack.MsgpackDecoder;
import util.EmojiUtil;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/19]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter
{
    private int counter;

//    static final String ECHO_REQ = "好🍎🍎🍎🍎🍎🍎🍎🍎🍎🍎🍎🍎🍎🍎🍎🍎🍎";

    @Override
    public void channelActive(ChannelHandlerContext ctx)
        throws Exception
    {
        for (int i = 1; i <= 10; i++)
        {
//            ctx.writeAndFlush(ECHO_REQ);
            ctx.writeAndFlush(new MsgPackDomain(10));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception
    {
        MsgPackDomain msgPackDomain = (MsgPackDomain)msg;
        System.out.println(
            "次数是：" + ++counter + "；苹果：" + msgPackDomain.getApple() + "; 番茄：" + msgPackDomain.getTomato());
//        if (counter < 5)
//        { //控制运行次数，因为不加这个控制直接调用下面代码的话，客户端和服务端会形成闭环循环，一直运行
//            ctx.write(msg);
//        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
        throws Exception
    {
        //每一次发送消息体，就要刷新一次
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
        throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }

}
