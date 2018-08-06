package util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/8]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SocketChannelUtils {

    private SocketChannelUtils() {

    }

    public static void doWrite(SelectionKey key) throws IOException {
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
                String body = new String(bytes, "UTF-8").trim();
                //1.2.4 do you own business
                System.out.println("the client order is " + body);
                String currentTime = "QTO".equals(body) ? new Date(System.currentTimeMillis()).toString() : "错误的命令";
                //1.2.5 write the result to client
                doWrite(sc, currentTime);
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

    /**
     * 将结果返回给客户端
     *
     * @param sc,客户端通道
     * @param data,处理结果
     * @return void
     * @throws
     * @author wangyang
     * @date 2018/7/7 下午9:03
     */
    public static void doWrite(SocketChannel sc, String data) throws IOException {
        if (data != null && data.trim().length() > 0) {
            byte[] bytes = data.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            sc.write(byteBuffer);
            if (!byteBuffer.hasRemaining()) {
                System.out.printf("Date Send Success！！！" + data);
            }
        }
    }
}
