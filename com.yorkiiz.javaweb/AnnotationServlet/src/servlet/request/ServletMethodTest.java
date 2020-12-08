package servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@WebServlet("/demo5")
public class ServletMethodTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        String a = req.getMethod();
        String b = req.getContextPath();
        String c = req.getQueryString();
        String d = req.getRequestURI();
        StringBuffer e = req.getRequestURL();
        String f = req.getProtocol();
        String g = req.getRemoteAddr();

        System.out.println("req.getMethod():"+a);
        System.out.println("req.getContextPath():"+b);
        System.out.println("req.getQueryString():"+c);
        System.out.println("req.getRequestURI():"+d);
        System.out.println("req.getRequestURL():"+e);
        System.out.println("req.getProtocol():"+f);
        System.out.println("req.getRemoteAddr():"+g);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
