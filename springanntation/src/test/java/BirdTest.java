import com.service.Bird;
import com.service.IsaveAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class BirdTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Bird duck = (Bird) applicationContext.getBean("duck");
        System.out.println(duck);


    }

}
