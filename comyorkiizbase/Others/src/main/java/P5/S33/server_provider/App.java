package P5.S33.server_provider;

import P5.S33.server_api.IHelloService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        IHelloService service=  new HelloServiceImpl();
        RpcProxyServer proxyServer=new RpcProxyServer(service,8080);
        new Thread(proxyServer).start();
        System.out.println( "Hello World!" );
    }
}
