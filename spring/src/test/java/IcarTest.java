import com.service.Icar;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class IcarTest {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        //BeanFactory beanFactory = applicationContext;
        Icar bike = (Icar) applicationContext.getBean("bike");
        System.out.println(bike);
    }

}
