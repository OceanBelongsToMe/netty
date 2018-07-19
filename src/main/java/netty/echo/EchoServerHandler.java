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
public class EchoServerHandler extends ChannelInboundHandlerAdapter
{
    private int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception
    {
        String request = (String)msg;
        System.out.println("request [" + request + "]" + ++counter);
        request += "$_";
        ByteBuf echo = Unpooled.copiedBuffer(request.getBytes());
        ctx.writeAndFlush(echo);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
        throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }
}
