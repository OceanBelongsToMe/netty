package ocean.example.messagePack.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import ocean.example.messagePack.MsgpackDecoder;
import ocean.example.messagePack.MsgpackEncoder;

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
                        socketChannel.pipeline()
                            .addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 4, 0, 4));
                        socketChannel.pipeline().addLast("msgpack decoder", new MsgpackDecoder());

                        //ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                        //socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
                        //socketChannel.pipeline().addLast(new StringDecoder());
                        //在MessagePack编码器前增加2个字节的消息长度
                        socketChannel.pipeline().addLast("frameEncoder",new LengthFieldPrepender(4));
                        socketChannel.pipeline().addLast("msgpack encoder", new MsgpackEncoder());

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
