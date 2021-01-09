package com.yorkiiz.yysdata.dao;

import com.yorkiiz.yysdata.domain.PageBean;
import com.yorkiiz.yysdata.domain.YysData;

import java.text.ParseException;
import java.util.List;


public interface UserDao {

    public List<YysData> findAll();

    void adddata(YysData yysData);

    void deletedata(YysData yysData);

    YysData find(YysData yysData);

    void update(YysData yysData);

    void deletesel(String[] tids) throws ParseException;

    List<YysData> select(String t1, String t2, String number);

    List<YysData> findbyPage(int startrows, int endrows);

    Integer findAllcount();


}
