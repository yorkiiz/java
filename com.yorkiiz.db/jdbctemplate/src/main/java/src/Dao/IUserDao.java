package src.Dao;

import src.Domain.User;

import java.util.List;

public interface IUserDao {
    List<User> findall();
}
