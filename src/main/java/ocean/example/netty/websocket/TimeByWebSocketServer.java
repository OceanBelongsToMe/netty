package ocean.example.netty.websocket;

import com.sun.corba.se.internal.CosNaming.BootstrapServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocket00FrameDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/10/13]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TimeByWebSocketServer
{
    //私有化无参构造器
    private TimeByWebSocketServer()
    {

    }

    //静态内部类的静态成员
    public static TimeByWebSocketServer getInstance()
    {
        return TimeByWebSocketServerInstance.INSTANCE;
    }

    private static class TimeByWebSocketServerInstance
    {
        static final TimeByWebSocketServer INSTANCE = new TimeByWebSocketServer();
    }

    private void start(int port)
    {
        //绑定端口
        try
        {
            EventLoopGroup bossGrop = new NioEventLoopGroup();
            EventLoopGroup workGrop = new NioEventLoopGroup();
            ServerBootstrap b = new ServerBootstrap();

            b.group(bossGrop, workGrop).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>()
                {
                    @Override
                    protected void initChannel(SocketChannel ch)
                        throws Exception
                    {
                        ChannelPipeline pipeline = ch.pipeline();
                        //将请求转换为http请求
                        pipeline.addLast("http-codec", new HttpServerCodec());
                        //将http请求的各个部分合并为一个http请求
                        pipeline.addLast("http-aggregator", new HttpObjectAggregator(1024 * 64));
                        //支持发送html5文件
                        pipeline.addLast("http-chunked", new ChunkedWriteHandler());
                        //WebSockethander
                        pipeline.addLast(new WebSocketServerHandler());
                    }
                });

            Channel ch = b.bind(port).sync().channel();
            ch.closeFuture().sync();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args)
    {
        int port = 8080;
        if (args != null && args.length > 0)
        {
            port = Integer.parseInt(args[0]);
        }
        TimeByWebSocketServer.getInstance().start(port);
    }
}
