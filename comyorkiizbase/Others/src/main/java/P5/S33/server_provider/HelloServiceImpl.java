package P5.S33.server_provider;

import P5.S33.server_api.IHelloService;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/12/16-18:04
 */
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String content) {
        return "Hello Content:"+content;
    }
}
