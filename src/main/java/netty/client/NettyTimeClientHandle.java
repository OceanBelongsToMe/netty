package netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class NettyTimeClientHandle extends ChannelInboundHandlerAdapter {
    private final ByteBuf message;

    public NettyTimeClientHandle() {

        byte[] bytes = "QTO".getBytes();
        message = Unpooled.buffer(bytes.length);
        message.writeBytes(bytes);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        System.out.println("ha ha ha ha,Now is " + new String(bytes, "UTF-8"));
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("释放资源");
        //释放资源
        ctx.close();
    }
}
