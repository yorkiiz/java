package P5.S33.server_provider;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/12/16-18:06
 */
public class RpcProxyServer implements Runnable{

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private volatile boolean stop;
    private Object service;

    public RpcProxyServer(Object service,int port) {
        this.service = service;
        try {
            selector=Selector.open();
            serverSocketChannel=ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(8080));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while(!stop){
            try {
                selector.select(); //阻塞
                Set<SelectionKey> selectionKeys=selector.selectedKeys();
                Iterator<SelectionKey> iterator=selectionKeys.iterator();
                while(iterator.hasNext()){
                    SelectionKey key=iterator.next();
                    iterator.remove();
                    try {
                        handleRequest(key);
                    }catch (Exception e){
                        key.cancel();
                        key.channel().close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void handleRequest(SelectionKey key) throws IOException {
        if(key.isValid()){
            if(key.isAcceptable()){
                ServerSocketChannel serverSocketChannel=(ServerSocketChannel)key.channel();
                SocketChannel socketChannel=serverSocketChannel.accept(); //
                socketChannel.configureBlocking(false);
                socketChannel.register(selector,SelectionKey.OP_READ);
            }else if(key.isReadable()){
                SocketChannel socketChannel=(SocketChannel)key.channel();
                new ProcessorHandler(socketChannel,service).doProcessor();
            }
        }
    }
}
