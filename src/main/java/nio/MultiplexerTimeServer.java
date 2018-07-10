package nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.SocketChannelUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/7]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MultiplexerTimeServer extends GeneralHandle implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiplexerTimeServer.class);

    private ServerSocketChannel serverSocketChannel;

    /**
     * 初始化多路复用器Selector，绑定监听端口
     *
     * @param port,端口
     * @throws
     * @author wangyang
     * @date 2018/7/7 下午9:01
     */
    public MultiplexerTimeServer(int port) {
        super();
        try {
            //1 打开selector
            this.selector = Selector.open();
            //2 打开serverSocketChannel
            serverSocketChannel = ServerSocketChannel.open();
            //3 设置serverSocketChannel的链接为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            //4 绑定serverSocketChannel的socket端口，它的blacklog设置为1024
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            //5 将serverSocketChannel注册到selector上，并监听OP_ACCEPT
            serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);

            //打印
            System.out.printf("MultiplexerTimeServer port is " + port);
        } catch (IOException e) {
            //如果资源初始化失败，如端口被占用，则退出
            LOGGER.error("初始化失败", e);
            System.exit(1);
        }
    }

    @Override
    public void run() {
        super.generalRun();
    }


    /**
     * NIO SelectionKey中定义的4种事件
     * SelectionKey.OP_ACCEPT —— 接收连接继续事件，表示服务器监听到了客户连接，服务器可以接收这个连接了
     * SelectionKey.OP_CONNECT —— 连接就绪事件，表示客户与服务器的连接已经建立成功
     * SelectionKey.OP_READ —— 读就绪事件，表示通道中已经有了可读的数据，可以执行读操作了（通道目前有数据，可以进行读操作了）
     * SelectionKey.OP_WRITE —— 写就绪事件，表示已经可以向通道写数据了（通道目前可以用于写操作）
     * 这里 注意，下面两种，SelectionKey.OP_READ ，SelectionKey.OP_WRITE ，
     * 1.当向通道中注册SelectionKey.OP_READ事件后，如果客户端有向缓存中write数据，下次轮询时，则会 isReadable()=true；
     * 2.当向通道中注册SelectionKey.OP_WRITE事件后，这时你会发现当前轮询线程中isWritable()一直为ture，如果不设置为其他事件
     * <p>
     * <p>
     * 根据IO事件处理请求
     *
     * @param key,SelectionKeyIO事件
     * @return void
     * @throws
     * @author wangyang
     * @date 2018/7/7 下午4:21
     */
    public void handleInput(SelectionKey key) throws IOException {
        //1 如果key有效
        if (key.isValid()) {
            //1.1 如果接受继续事件:isAcceptAble()
            if (key.isAcceptable()) {
                //1.1.1 get the Server channel, accept this connection，and set socketChannel configureBlocking false
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                //1.1.2 register this connection on the selector,binding OP_READ
                sc.register(this.selector, SelectionKey.OP_READ);
            }

            SocketChannelUtils.doWrite(key);
        }
    }


}
