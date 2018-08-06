package ocean.example.bio;

import util.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
        BufferedReader in = null;
        PrintWriter out = null;
        Socket socket = null;
        try {
            socket = new Socket("localhost", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("QTO");
            System.out.println("Query Time Order");
            String resp = in.readLine();
            System.out.println("现在是：" + resp);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Utils.close(in, out, socket);
        }
    }
}
