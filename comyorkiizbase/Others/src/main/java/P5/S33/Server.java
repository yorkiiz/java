package P5.S33;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            System.out.println("有客户端"+socket.getPort()+"已经连接");
            //从网络上拿到一个输入数据
            InputStream inputStream = socket.getInputStream();
            int len = 0;
            byte[] buff = new byte[1024];
            while((len=inputStream.read(buff))!=-1){
                System.out.println(new String(buff,0,len,"UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
