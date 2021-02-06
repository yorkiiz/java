import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class Eazyconnect {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String url = "";
        String sql = "select * from user";

        Class.forName("com.mysql.jdbc.Driver");
        url = "jdbc:mysql://localhost:3306/test";

        conn = DriverManager.getConnection(url, "root", "welcome88!");
        st = (Statement) conn.createStatement();
        rs = st.executeQuery(sql);
        int index=1;
        while (rs.next()){
            System.out.println(rs.getString(1));
            index++;
        }
        rs.close();

    }


}
