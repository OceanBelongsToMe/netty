package proto.echo;

import com.ocean.study.service.MytestProto.SignInPrize;
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

    @Override
    public void channelActive(ChannelHandlerContext ctx)
        throws Exception
    {

        for (int i = 1; i <= 10; i++)
        {
            SignInPrize.Builder builder = SignInPrize.newBuilder().setA(i);
            ctx.writeAndFlush(builder.build());
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception
    {
        SignInPrize signInPrize = (SignInPrize)msg;
        System.out.println(signInPrize.getA() + ":" + signInPrize.getB());
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
