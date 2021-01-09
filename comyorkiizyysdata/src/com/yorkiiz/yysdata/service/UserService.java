package com.yorkiiz.yysdata.service;

import com.yorkiiz.yysdata.domain.PageBean;
import com.yorkiiz.yysdata.domain.YysData;

import java.text.ParseException;
import java.util.List;

public interface UserService {

    /*
    查询所有用户信息
     */
    List<YysData> findAll();

    void adddata(YysData yysData);

    void delete(YysData yysData);

    YysData find(YysData yysData);

    void update(YysData yysData);

    void deletesel(String[] tids) throws ParseException;

    List<YysData> select(String t1, String t2, String number);

    PageBean<YysData> findbyPage(String currentpage, String rows);
}
