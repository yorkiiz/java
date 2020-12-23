package com.yorkiiz.yysdata.service;

import com.yorkiiz.yysdata.domain.YysData;

import java.util.List;

public interface UserService {

    /*
    查询所有用户信息
     */
    List<YysData> findAll();

    void adddata(YysData yysData);

    void delete(YysData yysData);
}
