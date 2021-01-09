package com.yorkiiz.yysdata.web.servlet;

import com.yorkiiz.yysdata.domain.YysData;
import com.yorkiiz.yysdata.service.UserService;
import com.yorkiiz.yysdata.service.impl.UserServiceImpl;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@WebServlet("/selectServlet")
public class SelectServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.设置编码格式
        req.setCharacterEncoding("utf-8");
        //2.获取参数
        String t1 = req.getParameter("time1");
        String t2 = req.getParameter("time2");
        String number = req.getParameter("number");
        //3.封装对象


        //4.调用service保存
        UserService service = new UserServiceImpl();
        List<YysData> users = (List<YysData>) service.select(t1,t2,number);

        //存入request域
        req.setAttribute("users",users);

        //5.跳转到虚拟路径
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doPost(req,resp);
    }
}
