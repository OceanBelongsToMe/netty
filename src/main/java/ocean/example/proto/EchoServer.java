package ocean.example.proto;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import com.ocean.study.service.MytestProto.SignInPrize;


/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/19]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class EchoServer
{
    public void bind(int port)
    {

        //配置服务端nio线程组
        EventLoopGroup bossGrop = new NioEventLoopGroup();
        EventLoopGroup workerGrop = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        try
        {
            b.group(bossGrop, workerGrop)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>()
                {
                    @Override
                    protected void initChannel(SocketChannel socketChannel)
                        throws Exception
                    {
                        socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                        socketChannel.pipeline().addLast(new ProtobufDecoder(SignInPrize.getDefaultInstance()));
                        socketChannel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                        socketChannel.pipeline().addLast(new ProtobufEncoder());
                        socketChannel.pipeline().addLast(new EchoServerHandler());
                    }
                });

            //绑定端口，等待成功
            ChannelFuture f = b.bind(port).sync();
            //等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();

        }
        finally
        {
            bossGrop.shutdownGracefully();
            workerGrop.shutdownGracefully();
        }
    }

    public static void main(String[] args)
    {
        int port = 8080;
        if (args != null && args.length > 1)
        {
            port = Integer.parseInt(args[0]);
        }
        new EchoServer().bind(port);
    }
}
