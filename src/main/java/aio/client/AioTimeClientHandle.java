package aio.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AioTimeClientHandle implements Runnable, CompletionHandler<Void, AioTimeClientHandle> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AioTimeClientHandle.class);
    private String host;
    private int port;
    private AsynchronousSocketChannel client;

    private CountDownLatch latch;

    public AioTimeClientHandle(String host, int port) {
        this.port = port;
        if (host == null) {
            host = "127.0.0.1";
        }
        this.host = host;
        try {
            client = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        client.connect(new InetSocketAddress(host, port), this, this);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void completed(Void result, AioTimeClientHandle attachment) {
        //向服务器发送请求命令
        byte[] writeBytes = "QTO".getBytes();
        ByteBuffer request = ByteBuffer.allocate(writeBytes.length);
        request.put(writeBytes);
        request.flip();
        client.write(request, request, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                //如果没有发送完，继续发送
                if (request.hasRemaining()) {
                    client.write(attachment, attachment, this);
                } else {
                    //执行异步读取服务器返回的结果
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    client.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            attachment.flip();
                            byte[] readBytes = new byte[attachment.remaining()];
                            attachment.get(readBytes);
                            try {
                                String response = new String(readBytes, "UTF-8");
                                System.out.println("the server'answer is " + response);
                                latch.countDown();
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            try {
                                client.close();
                                latch.countDown();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    client.close();
                    latch.countDown();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, AioTimeClientHandle attachment) {
        exc.printStackTrace();
        try {
            client.close();
            latch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
