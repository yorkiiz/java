package src.Dao;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import src.Domain.User;
import utils.JDBCUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class UserDao implements IUserDao {


    JdbcTemplate jdbcTemplate = new JdbcTemplate( JDBCUtils.getDataSource());



    public UserDao() throws SQLException {
    }


    public List<User> findall() {
       String sql = "select * from user";
       List<User> lusers;
       lusers = jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class));
       return lusers;
    }
}
