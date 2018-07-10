package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/7]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Utils {
    private Utils() {

    }

    public static void close(ServerSocket server) {
        if (server != null) {
            System.out.printf("The TimeServer close");
            try {
                server.close();
                server = null;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void close(BufferedReader in, PrintWriter out, Socket socket) {
        try {
            if (in != null)
                in.close();
            if (out != null)
                out.close();
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
