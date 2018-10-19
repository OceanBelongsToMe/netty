package ocean.example.netty.httpxml;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/9/7]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class AbstractHttpXmlEncoder<T> extends MessageToMessageEncoder<T>
{
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
        throws Exception
    {
        super.exceptionCaught(ctx, cause);
    }
}
