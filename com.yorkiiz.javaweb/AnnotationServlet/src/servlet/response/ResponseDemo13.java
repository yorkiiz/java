package servlet.response;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@WebServlet("/demo13")
public class ResponseDemo13 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //tomcat 编码格式为ISO-8859-1，将该编码转换成GBK
        resp.setCharacterEncoding("GBK");
        //告知浏览器用什么编码
        //resp.setHeader("content-type","text/html;charset=utf-8");
        //resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.write("<h1>你好hello response</h1>");

        ServletOutputStream sos = resp.getOutputStream();
        sos.write("你好呀".getBytes());


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
