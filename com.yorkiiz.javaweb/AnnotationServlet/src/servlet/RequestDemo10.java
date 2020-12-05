package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @auther:
 * @date:
 * @describtion:
 **/
@WebServlet("/demo10")
public class RequestDemo10 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String param = req.getParameter("username");
        String[] params = req.getParameterValues("hobby");
        System.out.println(param);
        for (String s : params) {
            System.out.println(s);
        }

        Enumeration<String> paramall = req.getParameterNames();
        while(paramall.hasMoreElements()){
            String nam = paramall.nextElement();
            System.out.println(nam+":");
            String[] values = req.getParameterValues(nam);
            System.out.println("values:");
            for (String value : values) {
                System.out.println(value);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        this.doPost(req, resp);
    }
}
