package com;

import com.yorkiiz.yysdata.dao.Impl.UserDaoImpl;
import com.yorkiiz.yysdata.dao.UserDao;
import com.yorkiiz.yysdata.domain.YysData;
import com.yorkiiz.yysdata.service.UserService;
import com.yorkiiz.yysdata.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class Testdb {

    public static void main(String[] args) {
        UserDao u = new UserDaoImpl();
        List<YysData> all = u.findAll();
        System.out.println(all);
    }

    @Test
    public void testservice(){
        UserService userService = new UserServiceImpl();
        List<YysData> users = userService.findAll();
        System.out.println(users);
    }


}
