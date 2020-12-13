package servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@WebServlet("/cookieDemo5")
public class CookieDemo5 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie c = new Cookie("msg", "hello");
        //setMaxAge 正数：缓存在硬盘   负数：浏览器关闭立即删除    0：缓存在浏览器
        c.setMaxAge(30);//缓存30秒
        //c.setPath("/");//设置cookie共享路径为跟路径
        c.setDomain(".baidu.com");
        resp.addCookie(c);
        resp.getWriter().write("<h1>cookieDemo5</h1>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
