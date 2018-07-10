package nio;

import java.io.IOException;

/**
 * <一句话描述>同步阻塞I/O创建的
 *
 * @author wangyang
 * @version [需求编号, 2018/6/27]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TimerServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            port = Integer.valueOf(args[0]);
        } else {
            //throw NumberFormatException use default port
        }

        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);

        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();

    }


}
