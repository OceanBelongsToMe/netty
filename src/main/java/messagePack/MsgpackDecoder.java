package messagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.util.List;

/**
 * 解码器
 * <一句话描述>MsgpackDecoder继承MessageToByteEncoder
 *
 * @author wangyang
 * @version [需求编号, 2018/7/24]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf>
{

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list)
        throws Exception
    {
        final byte[] array;
        final int length = byteBuf.readableBytes();
        array = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(), array, 0, length);
        MessagePack msgPack = new MessagePack();
        //设置解码类型
        list.add(msgPack.read(array, Templates.TString));
//        list.add(msgPack.read(array,MsgPackDomain.class));
    }
}
