import java.sql.*;
import java.util.Collection;

/**
 * @auther:
 * @date:
 * @describtion:程序的耦合
 **/

public class JdbcDemo1 {
    public static void main(String[] args) throws SQLException {

        //1.注册驱动
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //2.获取连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
        //3.获取预处理对象
        PreparedStatement statement = conn.prepareStatement("select * from user ");
        //4.执行结果
        ResultSet rs = statement.executeQuery();
        //5.返回结果
        while(rs.next()){
            System.out.println(rs.getString("name"));
        }
        //6.关闭连接
        rs.close();
        statement.close();
        conn.close();


    }

}
