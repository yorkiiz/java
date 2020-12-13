package servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

/**
 在服务器中的Servlet判断是否有一个名为lastTime的cookie
 1. 有：不是第一次访问
 1. 响应数据：欢迎回来，您上次访问时间为:2018年6月10日11:50:20
 2. 写回Cookie：lastTime=2018年6月10日11:50:01
 2. 没有：是第一次访问
 1. 响应数据：您好，欢迎您首次访问
 2. 写回Cookie：lastTime=2018年6月10日11:50:01




 */

@WebServlet("/cookietest")
public class CookieTest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = req.getCookies();
        Boolean cookieflg = false;
        if (cookies!=null&&cookies.length>0){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if(name.equals("lastdate")){
                    String value = cookie.getValue();
                    System.out.println("解码前："+value);
                    value = URLDecoder.decode(value,"utf-8");
                    System.out.println("解码后："+value);
                    resp.getWriter().write("<h1>"+"last visit time is"+value+"</h1>");

                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
                    String str_date = sdf.format(date);
                    System.out.println("编码前："+str_date);
                    str_date=URLEncoder.encode(str_date,"UTF-8");
                    System.out.println("编码后："+str_date);
                    cookie.setValue(str_date);
                    cookie.setMaxAge(60*60*24);
                    resp.addCookie(cookie);
                    cookieflg = true;
                    break;

                }
            }
        }

        if(!cookieflg){
            Cookie cookie = new Cookie("lastdate", "");
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
            String str_date = sdf.format(date);
            System.out.println("编码前："+str_date);
            str_date=URLEncoder.encode(str_date,"UTF-8");
            System.out.println("编码后："+str_date);
            cookie.setValue(str_date);
            cookie.setMaxAge(60*60*24);
            resp.addCookie(cookie);
            System.out.println("解码前："+str_date);
            str_date = URLDecoder.decode(str_date,"utf-8");
            System.out.println("解码后："+str_date);
            resp.getWriter().write("<h1>"+"welcome to this page"+str_date+"</h1>");

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
