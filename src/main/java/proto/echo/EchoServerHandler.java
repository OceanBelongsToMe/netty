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
public class EchoServerHandler extends ChannelInboundHandlerAdapter
{
    private int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception
    {
        DecodeProtoBuf(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
        throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }

    private void DecodeProtoBuf(ChannelHandlerContext ctx, Object msg)
    {
        SignInPrize signInPrize = (SignInPrize)msg;

        String b = "";
        for (int i = 0; i < signInPrize.getA(); i++)
        {
            b += "🌰";
        }
        SignInPrize.Builder builder = signInPrize.toBuilder();
        builder.setB(b);

        ctx.writeAndFlush(builder.build());
    }

}
