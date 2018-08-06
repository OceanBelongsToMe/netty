package ocean.example.aio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/9]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AsyncAioTimeServerHandle implements Runnable {
    //端口
    private int port;

    //异步Socket通道
    private AsynchronousServerSocketChannel assc;

    //
    CountDownLatch countDownLatch;

    /**
     * 打开AsynchronousServerSocketChannel,绑定端口
     *
     * @param port,端口
     * @throws
     * @author wangyang
     * @date 2018/7/10 上午7:14
     */
    public AsyncAioTimeServerHandle(int port) {
        this.port = port;
        try {
            assc = AsynchronousServerSocketChannel.open();
            assc.bind(new InetSocketAddress(port));
            System.out.println("The aioTimeServer port is " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //1 初始化countDownLatch对象
        countDownLatch = new CountDownLatch(1);
        //2 接受客户端链接
        doAccept();
        //3 让线程在此阻塞，防止服务端执行完成退出
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void doAccept() {
        assc.accept(this, new AcceptCompletionHandle());
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public AsynchronousServerSocketChannel getAssc() {
        return assc;
    }

    public void setAssc(AsynchronousServerSocketChannel assc) {
        this.assc = assc;
    }
}
