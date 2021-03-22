package com.yorkiiz.dao;

import com.yorkiiz.domain.Account;

import java.sql.SQLException;
import java.util.List;

public interface IAccountDao {

    void addaccount(String name);

    void deleteaccount(String name);

    Account findaccount(String name) throws SQLException;

    void updateaccount(Account account) throws SQLException;
}
