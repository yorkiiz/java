import com.matrix.cim.config.MailUtilInfo;
import com.matrix.cim.dao.ams.AlarmDao;
import com.matrix.cim.entity.ams.AlarmMessage;
import com.matrix.cim.job.MainProcessJob;
import com.matrix.cim.service.*;
import com.matrix.cim.util.MailSendUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@Slf4j
public class TestJdbc {

    @Autowired
    private CodeService codeService;

    @Autowired
    private RouteiService routeiService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private PrdctService prdctService;

    @Autowired
    private PrdctmtService prdctmtService;

    @Autowired
    private OperService operService;

    @Autowired
    private EqptService eqptService;

    @Autowired
    private MailSendUtil mailSendUtil;

    @Autowired
    private MailUtilInfo mailUtilInfo;


    @Autowired
    private MainProcessJob mainProcessJob;

    @Autowired
    private AlarmDao alarmDao;

    @Test
    public void testCodeCopmare() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        boolean isSame = codeService.compare();
        log.info("本次比对结果 {}", isSame ? "成功" : "失败");
        Assert.assertTrue(isSame);
    }

    @Test
    public void testRouteiCopmare() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        boolean isSame = routeiService.compare();
        log.info("本次比对结果 {}", isSame ? "成功" : "失败");
        Assert.assertTrue(isSame);
    }

    @Test
    public void testRouteCopmare() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        boolean isSame = routeService.compare();
        log.info("本次比对结果 {}", isSame ? "成功" : "失败");
        Assert.assertTrue(isSame);
    }

    @Test
    public void testOperCopmare() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        boolean isSame = operService.compare();
        log.info("本次比对结果 {}", isSame ? "成功" : "失败");
        Assert.assertTrue(isSame);
    }

    @Test
    public void testEqptCopmare() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        AlarmMessage.list = new ArrayList<>();
        boolean isSame = eqptService.compare();
        log.info("本次比对结果 {}", isSame ? "成功" : "失败");
//        Assert.assertTrue(isSame);
    }

    @Test
    public void testPrdctCopmare() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        boolean isSame = prdctService.compare();
        log.info("本次比对结果 {}", isSame ? "成功" : "失败");
        Assert.assertTrue(isSame);
    }

    @Test
    public void testPrdctmtCopmare() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        boolean isSame = prdctmtService.compare();
        log.info("本次比对结果 {}", isSame ? "成功" : "失败");
        Assert.assertTrue(isSame);
    }


//    @Test

//    public void testSendAlarm() {
//        alarmDao.sendAlarm("brm", "code比对异常");
//    }

    @Test
    public void testMailSender(){
        try{
//            mailSendUtil.sendMail("",null);
            TestMail.sendMail("aicheng.hua@everdisplay.com","cim_report@everdisplay.com","测试","正文信息，该邮件为表同步异常报警测试邮件");
        }catch (Exception e){
            log.error("",e);
        }

    }

    @Test
    public void mainJobTest(){
String ss = "123";
log.info("日志信息{}{}",ss,ss);
        mainProcessJob.monitor();
    }
}

