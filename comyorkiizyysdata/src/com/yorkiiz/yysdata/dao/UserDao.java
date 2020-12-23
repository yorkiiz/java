package com.yorkiiz.yysdata.dao;

import com.yorkiiz.yysdata.domain.YysData;

import java.util.List;


public interface UserDao {

    public List<YysData> findAll();

    void adddata(YysData yysData);

    void deletedata(YysData yysData);
}
