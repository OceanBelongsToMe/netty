package aio.server;

import aio.server.AsyncAioTimeServerHandle;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/9]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AioTimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }
        AsyncAioTimeServerHandle aioServer = new AsyncAioTimeServerHandle(port);

        new Thread(aioServer, "AioServer-001").start();
    }
}
