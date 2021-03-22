package com.yorkiiz.service;

import java.sql.SQLException;

public interface IAccountService {

    void transferaccount(String tartgetname,String sourcename,double money) throws SQLException;
}
