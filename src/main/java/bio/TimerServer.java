package bio;

import util.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <一句话描述>同步阻塞I/O创建的
 *
 * @author wangyang
 * @version [需求编号, 2018/6/27]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TimerServer {
    public static void main(String[] args) throws NumberFormatException {
        int port = 8080;
        if (args != null && args.length > 0) {
            port = Integer.valueOf(args[0]);
        } else {
            //throw NumberFormatException use default port
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            Socket socket = null;

            while (true) {
                socket = server.accept();
                new Thread(new TimerServerHander(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Utils.close(server);
        }
    }
}
