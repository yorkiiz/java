package servlet;

import com.sun.net.httpserver.HttpServer;

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

@WebServlet("/demo6")
public class ServletRefer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String refer = req.getHeader("Refer");
        System.out.println(refer);
        if(refer!=null) {
            if (refer.contains("AnnotationServlet")) {
                System.out.println("访问成功");
            } else {
                System.out.println("访问失败");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
