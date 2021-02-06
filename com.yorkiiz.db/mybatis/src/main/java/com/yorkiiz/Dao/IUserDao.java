package com.yorkiiz.Dao;

import com.yorkiiz.domain.User;


import java.util.List;

public interface IUserDao {
    List<User> findAll();
}
