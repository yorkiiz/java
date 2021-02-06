import org.junit.Test;
import src.Service.ListUser;

import java.sql.SQLException;

/**
 * @auther:
 * @date:
 * @describtion:
 **/


public class TestUser {


    @Test
    public static void main(String[] args) throws SQLException {
        ListUser listUser = new ListUser();
        listUser.printuser();
    }



}
