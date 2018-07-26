package messagePack.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import messagePack.MsgpackDecoder;
import messagePack.MsgpackEncoder;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/19]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class EchoClient
{
    public void connect(String host, int port)
    {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        try
        {
            b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>()
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
                        socketChannel.pipeline().addLast("frameEncoder",new LengthFieldPrepender(4));

                        socketChannel.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
                        socketChannel.pipeline().addLast(new EchoClientHandler());
                    }
                });

            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();

        }
        finally
        {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args)
    {
        String host = "127.0.0.1";
        int port = 8080;
        if (args != null && args.length > 0)
        {
            port = Integer.parseInt(args[0]);
        }
        new EchoClient().connect(host, port);
    }
}
