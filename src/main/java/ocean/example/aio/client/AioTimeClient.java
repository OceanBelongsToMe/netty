package ocean.example.aio.client;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AioTimeClient {
    public static void main(String[] args) {
        int port = 8080;
        String host = "127.0.0.1";
        if (args != null && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }
        new Thread(new AioTimeClientHandle(host, port), "AioTimeClient-001").start();
    }
}
