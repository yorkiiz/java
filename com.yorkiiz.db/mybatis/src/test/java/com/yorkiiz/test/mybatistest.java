package com.yorkiiz.test;

import com.yorkiiz.Dao.IStudentDao;
import com.yorkiiz.Dao.IUserDao;
import com.yorkiiz.domain.Student;
import com.yorkiiz.domain.User;
import javafx.util.Builder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class mybatistest {

    public static void main(String[] args) throws IOException {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建sqlsessionfactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.使用工厂生产sqlsession对象
        SqlSessionFactory factory = builder.build(in);
        //4.使用sqlsession创建dao接口的代理对象
        SqlSession session = factory.openSession();
        //5.使用代理对象执行方法
        IUserDao iUserDao = session.getMapper(IUserDao.class);
        List<User> users = iUserDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

        IStudentDao iStudentDao = session.getMapper(IStudentDao.class);
        List<Student> students = iStudentDao.findAll();
        for (Student student : students) {
            System.out.println(student);
        }

        //6.释放资源
        session.close();
        in.close();
    }


}
