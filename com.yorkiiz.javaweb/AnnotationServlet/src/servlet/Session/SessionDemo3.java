package servlet.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@WebServlet("/sessiondemo3")
public class SessionDemo3 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1获取session1
        HttpSession session = req.getSession();
        System.out.println(session);

        //创建JESSIONID的cookie，设置最大存活时间
        Cookie c = new Cookie("JESSIONID",session.getId());
        c.setMaxAge(60*60);
        resp.addCookie(c);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
