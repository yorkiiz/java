/**
 * @auther:
 * @date:
 * @describtion:
 **/

import com.yorkiiz.service.IAccountService;
import com.yorkiiz.service.Imp.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class AccountTest {

    @Autowired
    private IAccountService accountService;

    @Test
    public void testtransfer() throws SQLException {


        accountService.transferaccount("aaa","bbb",100);
    }

}
