package netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

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

    static final String ECHO_REQ = "🍎🍎🍎🍎🍎🍎🍎🍎🍎🍎🍎🍎🍎🍎🍎🍎🍎$_";

    @Override
    public void channelActive(ChannelHandlerContext ctx)
        throws Exception
    {
        ByteBuf message;
        for (int i = 0; i < 10; i++)
        {
            message = Unpooled.buffer(ECHO_REQ.getBytes().length);
            message.writeBytes(ECHO_REQ.getBytes());
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception
    {
        System.out.println("次数是：" + ++counter + "；苹果：" + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
        throws Exception
    {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
        throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }

}
