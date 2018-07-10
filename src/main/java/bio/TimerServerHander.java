package bio;

import util.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/6/27]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TimerServerHander implements Runnable {

    private Socket socket;

    TimerServerHander(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                String body = in.readLine();
                if (body == null)
                    break;
                System.out.println("the client order is " + body);
                out.println("QTO".equals(body) ? new Date(System.currentTimeMillis()).toString() : "错误的命令");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Utils.close(in, out, socket);
        }
    }
}