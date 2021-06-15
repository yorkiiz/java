package P5.S33;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class Client {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost",8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //我想发送一个数据到服务端
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("yongjie".getBytes("UTF-8"));
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
