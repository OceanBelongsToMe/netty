package ocean.example.messagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * 解码器
 * <一句话描述>MsgpackEncoder继承MessageToByteEncoder
 *
 * @author wangyang
 * @version [需求编号, 2018/7/24]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MsgpackEncoder extends MessageToByteEncoder<Object>
{

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf)
        throws Exception
    {
        MessagePack messagePack = new MessagePack();
        //Serialize
        byte[] bytes = null;
        bytes = messagePack.write(o);
        byteBuf.writeBytes(bytes);
    }
}
