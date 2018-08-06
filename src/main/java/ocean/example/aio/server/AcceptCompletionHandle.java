package ocean.example.aio.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AcceptCompletionHandle implements CompletionHandler<AsynchronousSocketChannel, AsyncAioTimeServerHandle> {

    @Override
    public void completed(AsynchronousSocketChannel result, AsyncAioTimeServerHandle attachment) {
        //1 get AsyncAioTimeServerHandle's field assc,and do it's accept method
        attachment.getAssc().accept(attachment, this);
        //2 分配byteBuffer内存，通过AsynchronousSocketChannel进行异步读操作
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        result.read(buffer, buffer, new ReadCompletionHandle(result));

    }

    @Override
    public void failed(Throwable exc, AsyncAioTimeServerHandle attachment) {
        attachment.countDownLatch.countDown();
    }
}
