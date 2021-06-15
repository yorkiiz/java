package P5.S33.server_provider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/12/16-18:08
 */
public class ProcessorHandler{
    SocketChannel socket;
    Object service;

    public ProcessorHandler(SocketChannel socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    public void doProcessor(){
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        try {
            socket.read(byteBuffer);
            int postion=byteBuffer.position();
            String message=new String(byteBuffer.array(),0,postion);
            String[] requestData=message.split("@");
            String className=requestData[0];
            //Netty->
            /**
             * 对象的序列化
             *
             */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Object inovke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz=Class.forName(request.getClassName());
        Method method=clazz.getMethod(request.getMethodName(),request.getTypes());
        Object result=method.invoke(service,request.getParameters());
        return result;
    }
}
