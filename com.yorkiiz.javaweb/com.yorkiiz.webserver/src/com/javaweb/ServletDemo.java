package com.javaweb;

import javax.servlet.*;
import java.io.IOException;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

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

        System.out.println("Hellow Servlet");

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
