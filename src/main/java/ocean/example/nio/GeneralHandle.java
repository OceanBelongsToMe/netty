package ocean.example.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/8]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class GeneralHandle {
    protected volatile boolean stop;
    protected Selector selector;

    abstract void handleInput(SelectionKey key) throws IOException;

    public void stop() {
        this.stop = true;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public void generalRun() {
        try {
            //1 遍历，如果服务器未停
            while (!this.isStop()) {
                //2 每个1s，唤醒selector，返回一些I/O操作已经准备好的管道
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();
                //3 遍历就绪管道的SelectionKey集合
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    //3.1 取出当前SelectionKey，并从集合移除
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    //3.2 调用handleInput方法，传入SelectionKey，处理I/O事件
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        //异常：key not null,key must cancel，如果SelectionKey.channel不为null，channel must close
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }

            }
            //4 关闭selector
            if (selector != null) {
                selector.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
