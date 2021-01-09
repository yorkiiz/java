package com.yorkiiz.yysdata.service.impl;

import com.yorkiiz.yysdata.dao.Impl.UserDaoImpl;
import com.yorkiiz.yysdata.dao.UserDao;
import com.yorkiiz.yysdata.domain.PageBean;
import com.yorkiiz.yysdata.domain.YysData;
import com.yorkiiz.yysdata.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
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

    @Override
    public YysData find(YysData yysData) {
        return userDao.find(yysData);
    }

    @Override
    public void update(YysData yysData) {
        userDao.update(yysData);
    }

    @Override
    public void deletesel(String[] tids) throws ParseException {
        userDao.deletesel(tids);
    }

    @Override
    public List<YysData> select(String t1, String t2, String number) {
        return userDao.select(t1,t2,number);
    }

    @Override
    public PageBean<YysData> findbyPage(String currentpage, String rows) {
        PageBean<YysData> pageBean = new PageBean<YysData>();
        Integer totalcount = userDao.findAllcount();
        pageBean.setTotalCount(totalcount);

        int irows = Integer.valueOf(rows);
        int totalPage = (int) Math.ceil(totalcount/(irows));
        pageBean.setTotalPage(totalPage);

        int icurrentpage = Integer.valueOf(currentpage);
        pageBean.setCurrentPage(icurrentpage);

        pageBean.setRows(Integer.valueOf(rows));

        int startrows = (icurrentpage - 1)*irows;
        int endrows = startrows + irows;
        List<YysData> yysData = (List<YysData>) userDao.findbyPage(startrows,endrows);
        pageBean.setList(yysData);

        return pageBean;
    }
}
