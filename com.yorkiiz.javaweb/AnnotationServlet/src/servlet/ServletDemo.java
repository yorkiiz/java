package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

//@WebServlet(urlPatterns = "/demo2")
    @WebServlet("/demo3")
public class ServletDemo implements Servlet {


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("servlet init");

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        System.out.println("Hellow Servlet 3.0");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("severlet destroy");

    }
}
