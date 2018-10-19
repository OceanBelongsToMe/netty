package ocean.netty.server.fileserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/8/31]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MyFileServer
{
    public void bind(int port)
    {
        //创建bossGrop，用于服务端接受客户端连接
        EventLoopGroup bossGrop = new NioEventLoopGroup();
        //创建workGrop，用于进行SocketChannel的网络读写
        EventLoopGroup workGrop = new NioEventLoopGroup();

        //创建serverBootstrap，用于启动NIO服务端的辅助类，目的降低服务端开发难度
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //绑定reactor线程组
        serverBootstrap.group(bossGrop, workGrop)
            //设置channel类型为NioServerSocketChannel
            .channel(NioServerSocketChannel.class)
            //设置backlog大小
            .option(ChannelOption.SO_BACKLOG, 1024)
            //设置日志handle
            .handler(new LoggingHandler())
            //设置初始化handle处理器
            .childHandler(new GeneralChildeHander());

    }

    private class GeneralChildeHander extends ChannelInitializer<SocketChannel>
    {

        @Override
        protected void initChannel(SocketChannel ch)
            throws Exception
        {
            //获取与channl绑定的管道channelPipeline
            ChannelPipeline channelPipeline = ch.pipeline();
            //设置httpRequest的解码器
            channelPipeline.addLast(new HttpRequestDecoder());
            //将多种httpRequest消息对象合并为FullRequest
            channelPipeline.addLast(new HttpObjectAggregator(1024));
            //防止大文件，造成内存溢出
            channelPipeline.addLast(new ChunkedWriteHandler());
            channelPipeline.addLast(new MyFileServerHandler());
        }
    }
}
