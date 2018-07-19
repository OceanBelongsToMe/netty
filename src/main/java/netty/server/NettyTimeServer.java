package netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class NettyTimeServer
{
    public void bind(int port)
        throws InterruptedException
    {
        //配置服务端Nio线程组
        EventLoopGroup boosGrop = new NioEventLoopGroup();
        EventLoopGroup workGrop = new NioEventLoopGroup();
        try
        {
            //创建ServerBootstrap,用于启动nio服务端的辅助类，目的用于降低服务端的开发复杂度
            ServerBootstrap b = new ServerBootstrap();
            b.group(boosGrop, workGrop)
                .channel(NioServerSocketChannel.class)//设置channel为NioServerSocketChannel,类似于NIO中的ServerSocketChannel
                .option(ChannelOption.SO_BACKLOG, 1024)//设置channel的tcp参数：backlog
                .childHandler(new ChildChannelHandler());//绑定I/O事件的处理类
            //绑定端口，同步等待成功
            ChannelFuture future = b.bind(port).sync();

            //等待服务端监听端口关闭
            future.channel().closeFuture().sync();
        }
        finally
        {
            boosGrop.shutdownGracefully();
            workGrop.shutdownGracefully();
        }

    }

    /**
     * <一句话描述>用于处理I/O事件：记录日志，对消息编解码
     *
     * @author wangyang
     * @version [需求编号, 2018/7/10]
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>
    {

        @Override
        protected void initChannel(SocketChannel socketChannel)
            throws Exception
        {
            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
            socketChannel.pipeline().addLast(new StringDecoder());
            socketChannel.pipeline().addLast(new NettyTimeServerHandle());
        }
    }

    public static void main(String[] args)
        throws InterruptedException
    {
        int port = 8080;
        if (args != null && args.length > 0)
        {
            port = Integer.valueOf(args[0]);
        }
        new NettyTimeServer().bind(port);
    }
}
