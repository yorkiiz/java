package src.Service;

import src.Dao.IUserDao;
import src.Dao.UserDao;
import src.Domain.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class ListUser {

    IUserDao userdao = new UserDao();


    public ListUser() throws SQLException {
    }

    public void printuser(){
        List<User> lusers = userdao.findall();
        System.out.println(lusers);
    }

}
