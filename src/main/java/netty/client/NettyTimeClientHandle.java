package netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import util.EmojiUtil;

import java.io.UnsupportedEncodingException;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class NettyTimeClientHandle extends ChannelInboundHandlerAdapter
{
    private ByteBuf message;

    private byte[] req;

    private int counter;

    public NettyTimeClientHandle()
        throws UnsupportedEncodingException
    {
        String str = "QTO🍎";
        req = (str+"🍎" + System.getProperty("line.separator")).getBytes();
        //message = Unpooled.buffer(req.length);
        //message.writeBytes(req);
        System.out.println(new String(req, "UTF-8"));

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx)
        throws Exception
    {

        for (int i = 0; i < 100; i++)
        {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
            message = null;
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception
    {
        String response = (String)msg;
        System.out.println("ha ha ha ha,Now is " + response + "counter:" + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
        throws Exception
    {
        System.out.println("释放资源");
        //释放资源
        ctx.close();
    }
}
