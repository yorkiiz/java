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

@WebServlet("/updateServlet")
public class UserupdateServlet extends HttpServlet {
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
        YysData yysData1 = service.find(yysData);


        //4.将数据传到request
        req.setAttribute("yysData1",yysData1);
        System.out.println("yysData1:"+yysData1);


        //5.跳转到虚拟路径
        req.getRequestDispatcher("/update.jsp").forward(req, resp);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doPost(req, resp);
    }
}
