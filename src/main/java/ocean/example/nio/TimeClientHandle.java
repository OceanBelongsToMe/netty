package ocean.example.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.SocketChannelUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/7]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TimeClientHandle extends GeneralHandle implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeClientHandle.class);
    private int port;
    private String host;
    private SocketChannel socketChannel;

    /**
     * 实例化
     *
     * @param host,服务器ip地址
     * @param port,服务器端口
     * @throws
     * @author wangyang
     * @date 2018/7/7 下午9:15
     */
    public TimeClientHandle(String host, int port) {
        super();
        try {
            this.host = host == null ? "127.0.0.1" : host;
            this.port = port;
            selector = Selector.open();
            System.out.println(selector);
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            System.out.printf("client init success!!");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }


    @Override
    public void run() {

        //1 连接服务器
        try {
            doConnection();
            //2 唤醒Selector，遍历SelectionKey
            //3 清空SelectionKey元素，传入SelectionKey,执行handleInput方法
            //4 close selectionKey
            //5 close selector
            generalRun();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public void handleInput(SelectionKey key) throws IOException {
        //1 key isVaild
        if (key.isValid()) {
            //2 key isConnectable
            if (key.isConnectable()) {
                //3 sc finishConnect,register OP_READ,
                if (socketChannel.finishConnect()) {
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    SocketChannelUtils.doWrite(socketChannel, "QTO");
                } else {
                    System.exit(1);
                }
            }
            //1.2 如果是read就绪事件：isReadable()
            if (key.isReadable()) {
                //1.2.1 get the Socket channel,  read the data to the buffer
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(byteBuffer);
                /* flip()：Buffer有两种模式，写模式和读模式。在写模式下调用flip()之后，Buffer从写模式变成读模式。
                 * 那么limit就设置成了position当前的值(即当前写了多少数据)，postion会被置为0，以表示读操作从缓存的头开始读，mark置为-1。
                 * 也就是说调用flip()之后，读/写指针position指到缓冲区头部，并且设置了最多只能读出之前写入的数据长度(而不是整个缓存的容量大小)。
                 */

                //读取到字节
                if (readBytes > 0) {
                    //1.2.2 data no empty, then flip()
                    byteBuffer.flip();
                    //1.2.3 read the byte, new String by utf-8
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    //1.2.4 do you own business
                    System.out.println("Now is:" + body);
                    this.setStop(true);
                }
                //链路关闭
                else if (readBytes < 0) {

                }
                //没有读取到字节
                else {
                    ;
                }
            }

        }
    }

    public void doConnection() throws IOException {
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            socketChannel.register(selector, SelectionKey.OP_READ);
//            Scanner scan = new Scanner(System.in);
//            String read = scan.nextLine();
//            System.out.println("输入数据：" + read);
            String read = "QTO";
            SocketChannelUtils.doWrite(socketChannel, read);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }


}
