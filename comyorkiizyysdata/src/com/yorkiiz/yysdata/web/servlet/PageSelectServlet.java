package com.yorkiiz.yysdata.web.servlet;

import com.yorkiiz.yysdata.domain.PageBean;
import com.yorkiiz.yysdata.domain.YysData;
import com.yorkiiz.yysdata.service.UserService;
import com.yorkiiz.yysdata.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@WebServlet("/PageSelectServlet")
public class PageSelectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String currentpage = req.getParameter("currentpage");//当前页码
        String rows = req.getParameter("rows");//每页显示条数

        //调用service
        UserService service = new UserServiceImpl();
        PageBean<YysData> pageBean = service.findbyPage(currentpage,rows);





        //存入request域
        req.setAttribute("pageBean",pageBean);

        //转发对象
        req.getRequestDispatcher("/list.jsp").forward(req, resp);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doPost(req, resp);
    }
}
