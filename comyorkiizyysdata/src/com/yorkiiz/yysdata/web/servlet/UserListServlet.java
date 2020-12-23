package com.yorkiiz.yysdata.web.servlet;

import com.yorkiiz.yysdata.domain.YysData;
import com.yorkiiz.yysdata.service.UserService;
import com.yorkiiz.yysdata.service.impl.UserServiceImpl;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<YysData> users = null;
        System.out.println("===============================================");
        //调用service
        UserServiceImpl userService = new UserServiceImpl();
        users = userService.findAll();

        System.out.println(users);

        //存入request域
        req.setAttribute("users",users);

        //转发对象
        req.getRequestDispatcher("/list.jsp").forward(req, resp);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doPost(req, resp);
    }
}
