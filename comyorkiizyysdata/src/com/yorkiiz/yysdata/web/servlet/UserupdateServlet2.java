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
import java.util.Map;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@WebServlet("/updateServlet2")
public class UserupdateServlet2 extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.设置编码格式
        req.setCharacterEncoding("utf-8");
        //2.获取参数
        Map<String,String[]> map =req.getParameterMap();
        //3.封装对象
        YysData yysData = new YysData();
        BeanUtils.populate(yysData,map);

        //4.查询数据
        UserService service = new UserServiceImpl();
        service.update(yysData);


        //5.跳转到虚拟路径
        resp.sendRedirect(req.getContextPath()+"/UserListServlet");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doPost(req, resp);
    }
}
