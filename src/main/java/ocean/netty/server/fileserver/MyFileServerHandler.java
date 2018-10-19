package ocean.netty.server.fileserver;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/8/31]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MyFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest>
{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg)
        throws Exception
    {
        //1. 过滤favicon.ico请求
        //2. 判断http的请求解码状态，decode failed，return BAD_REQUEST 404
        //3. 判断是否GET请求，否，则return METHOD_NOT_ALLOWED 405
        //4. 获取RequestUri，并转换为path
        //5. 根据path获取File，判断File是否为目录，如果为目录，则构造该目录下的文件列表，返回给前端
        //6. 如果File不是文件类型，return Forbidden 403
        //7. 随机文件读写类，以读的方式打开文件
        //8. 获取文件长度，构建成功的http应答消息
    }
}
