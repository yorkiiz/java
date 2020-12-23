package com.yorkiiz.yysdata.service.impl;

import com.yorkiiz.yysdata.dao.Impl.UserDaoImpl;
import com.yorkiiz.yysdata.dao.UserDao;
import com.yorkiiz.yysdata.domain.YysData;
import com.yorkiiz.yysdata.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class UserServiceImpl implements UserService {



    private UserDao userDao = new UserDaoImpl();

    public List<YysData> findAll() {
        //调用Dao完成
        return userDao.findAll();
    }

    public void adddata(YysData yysData){
        userDao.adddata(yysData);
    }

    @Override
    public void delete(YysData yysData) {
        userDao.deletedata(yysData);
    }
}
