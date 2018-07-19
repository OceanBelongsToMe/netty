package netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;
import util.DateUtil;
import util.EmojiUtil;

import java.nio.ByteBuffer;
import java.util.Date;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class NettyTimeServerHandle extends ChannelInboundHandlerAdapter
{

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception
    {
        ByteBuf byteBuffer = (ByteBuf)msg;

        byte[] bytes = new byte[byteBuffer.readableBytes()];
        byteBuffer.readBytes(bytes);
        String request =
            new String(bytes, "UTF-8");
        request = request.substring(0, bytes.length - System.getProperty("line.separator").length());
        System.out.println(request);
        request = EmojiUtil.emojiRecovery(request);
        System.out.println("request:" + request + ";counter:" + ++counter);

        String result =
            "QTO".equals(request) ? DateUtil.GetNowDate(DateUtil.HOR_SEC_FORMAT) + "🍅🍅" : "bad 🍎🍎🍎🍎🍎🍎";

        result = result + System.getProperty("line.separator");
        ByteBuf response = Unpooled.copiedBuffer(result.getBytes());
        ctx.writeAndFlush(response);

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
        System.out.println(cause.getMessage());
        ctx.close();
    }

    public int getCounter()
    {
        return counter;
    }

    public void setCounter(int counter)
    {
        this.counter = counter;
    }

}
