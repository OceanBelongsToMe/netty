package ocean.example.messagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;
import org.msgpack.template.MapTemplate;
import org.msgpack.template.Template;
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

        //decodeString(list,msgPack,array);

        //decodeAnyObject(list,msgPack,array);

        decodeMapHaveObject(list,msgPack,array);

    }

    private void decodeString(List<Object> list, MessagePack msgPack, byte[] array)
        throws Exception
    {
        //解码String类型
        list.add(msgPack.read(array, Templates.TString));
    }

    private void decodeAnyObject(List<Object> list, MessagePack msgPack, byte[] array)
        throws Exception
    {
        //解码任意类
        list.add(msgPack.read(array, MsgPackDomain.class));
    }

    private void decodeMapHaveObject(List<Object> list, MessagePack msgPack, byte[] array)
        throws Exception
    {
        //解码包含任意类型的map
        //任意类型，不需要注册
        Template temp = msgPack.lookup(MsgPackDomain.class);
        list.add(msgPack.read(array, new MapTemplate(Templates.TInteger, temp)));

//        Template msgTemplate = null;
//        try
//        {
//            TemplateRegistry registry = new TemplateRegistry(null);
//            registry.register(MsgPackDomain.class);
//            msgTemplate = registry.lookup(MsgPackDomain.class);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        list.add(msgPack.read(array, new MapTemplate(Templates.TInteger,msgTemplate)));

    }

}
