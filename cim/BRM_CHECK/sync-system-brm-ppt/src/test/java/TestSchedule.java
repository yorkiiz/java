import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:applicationContext-quartz.xml"})
public class TestSchedule {

    @Test
    public void testMain() throws IOException {
        System.out.println("System started successful .............");
        System.out.println("按任意键退出");
        System.in.read();


    }
}
