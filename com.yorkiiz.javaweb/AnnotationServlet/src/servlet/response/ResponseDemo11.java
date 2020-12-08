package servlet.response;

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

@WebServlet("/demo11")
public class ResponseDemo11 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("response demo11");
        resp.setStatus(302);
        resp.setHeader("location","/AnnotationServlet_war_exploded/demo12");

        resp.sendRedirect("/AnnotationServlet_war_exploded/demo12");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
