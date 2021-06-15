package P5.S33.client;


import P5.S33.server_api.IHelloService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        RpcProxyClient client=new RpcProxyClient();

        IHelloService iHelloService=client.clientProxy(IHelloService.class,"localhost",8888);
        String content=iHelloService.sayHello("Mic"); //类似调用本地方法
        System.out.println(content);
    }
}
