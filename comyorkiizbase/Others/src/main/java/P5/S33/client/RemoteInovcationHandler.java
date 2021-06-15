package P5.S33.client;

import P5.S33.server.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/12/16-18:17
 */
public class RemoteInovcationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInovcationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request=new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);
        request.setTypes(method.getParameterTypes());
        RpcNetTransport rpcNetTransport=new RpcNetTransport(host,port);
        Object result=rpcNetTransport.send(request);
        return result;
    }
}
