package ocean.example.nio;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/6/27]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            port = Integer.valueOf(args[0]);
        } else {
            //throw NumberFormatException use default port
        }
        new Thread(new TimeClientHandle("127.0.0.1", port), "NIO-TimeClient-001").start();

    }
}
