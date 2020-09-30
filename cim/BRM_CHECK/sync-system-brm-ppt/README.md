## 数据同步检查说明
#### 一、 项目说明
    项目实现BRM表记录与PPT表记录同步结果校验
    
#### 二、添加校验实体
    
    1. 在src/mian/java/com/matrix/cim/entity/mes目录下增加实体类，映射数据库中的表字段;
    2. 继承父类BaseEntity;
    2. 类上添加@Data注解，实现getter．setter,toString方法;
    3. 类上添加@TableField注解，设置属性brmTableName值为BRM中的表名，pptTableName值为PPT中的表名，
    如@TableField(brmTableName="RBCODE",pptTableName = "ACODE");
    4. 主键字段属性上添加主键注解@PKField;
    5. 示例代码如下：
```java
package com.matrix.cim.entity.mes;

import com.matrix.cim.annotation.PKField;
import com.matrix.cim.annotation.TableField;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@TableField(brmTableName="RBCODE",pptTableName = "ACODE")
public class Code extends BaseEntity {
    @PKField
    private String code_cate;
    @PKField
    private String code;
    @PKField
    private String code_ext;

    private String subitem;
    private String code_dsc;
    private String ext_1;
    private String ext_2;
    private String ext_3;
    private String ext_4;
    private String ext_5;
}

```
    
#### 三、添加校验Service
    1. 在src/mian/java/com/matrix/cim/service目录下，新建service，对新建的实体进行比较
    2. 新建service需要继承AbstractCompareService抽象类，并引入泛型类为新建的实体，添加Service注解
    3. 实现compare方法，进行两个数据库表的记录比较
    4. 示例代码如下：
```java
    package com.matrix.cim.service;
    
    import com.matrix.cim.entity.mes.Code;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.stereotype.Service;
    
    import java.lang.reflect.InvocationTargetException;
    
    @Service
    @Slf4j
    public class CodeService extends AbstractCompareService<Code> {
    
        public boolean compare() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
            return compateList(new Code());
        }
    
    }

```

#### 四、将Service 添加到定时任务中
    1. 将service通过@Autowired注入到定时任务job/MainProcessJob中,
    2. 初始化集合
    3. 在monitor方法上添加代码，执行新增service的比较方法
    4. 将比对结果存入StringBuffer中，并添加标题
    3. 示例代码如下
```java
    package com.matrix.cim.job;
    
    import com.matrix.cim.dao.ams.MailAddressDao;
    import com.matrix.cim.entity.ams.AlarmMessage;
    import com.matrix.cim.entity.ams.EmailGroup;
    import com.matrix.cim.service.*;
    import com.matrix.cim.util.MailSendUtil;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    
    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.List;
    
    
    @Service
    @Slf4j
    public class MainProcessJob {
    
        // 步骤一：通过Autowired注解将CodeService注入到当前job
        @Autowired
        private CodeService codeService;
    
        @Autowired
        private EqptService eqptService;
    
        @Autowired
        private PrdctService prdctService;
    
        @Autowired
        private OperService operService;
    
        @Autowired
        private RouteService routeService;
        
        
        @Autowired
        private RouteiService routeiService;
    
        @Autowired
        private MailAddressDao mailAddressDao;
        @Autowired
        private MailSendUtil mailSendUtil;
    
        public void monitor() {
            StringBuffer sb = new StringBuffer();
    
            sb.append("任务名称：数据同步检查<br/>");
            sb.append("当前版本：1.0<br/>");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sb.append("检查时间：").append(sdf.format(new Date())).append("<br/>");
            sb.append("详细信息：<br/>");
            AlarmMessage.isAbnormal = false;
            try {

                // 步骤二：通过Autowired注解将CodeService注入到当前job
                AlarmMessage.list = new ArrayList<>();
                //步骤三： 添加service比较方法
                codeService.compare();
                //步骤四：将service比较结果，保存到StringBuffer中，并命名标题，此处标题为“CODE”
                getMessageFromList("CODE", sb);
                AlarmMessage.list = new ArrayList<>();
                eqptService.compare();
                getMessageFromList("EQPT", sb);
                AlarmMessage.list = new ArrayList<>();
                prdctService.compare();
                getMessageFromList("PRDCT", sb);
                AlarmMessage.list = new ArrayList<>();
                operService.compare();
                getMessageFromList("OPER", sb);
                AlarmMessage.list = new ArrayList<>();
                routeService.compare();
                getMessageFromList("ROUTE", sb);
                AlarmMessage.list = new ArrayList<>();
                routeiService.compare();
                getMessageFromList("ROUTEI", sb);
    
    
    //            TODO: 2019/9/25 查询该报警邮件的发送人
                List<EmailGroup> emailGroups = mailAddressDao.queryMailGroup();
                if (AlarmMessage.isAbnormal) {
                    mailSendUtil.sendMail(sb.toString(), emailGroups);
                }
            } catch (Exception e) {
                log.error("数据同步检查任务执行异常，异常信息", e);
            }
        }
    
        private void getMessageFromList(String title, StringBuffer sb) {
            if (AlarmMessage.list.size() != 0) {
                AlarmMessage.isAbnormal = true;
                sb.append("<font color=\"red\">&emsp;[").append(title).append("]比对异常详情：").append("</font><br/>");
                int i = 1;
                for (String s : AlarmMessage.list) {
    
                    sb.append("&emsp;&emsp;").append(i).append(": ").append(s).append("<br/>");
                    i++;
                }
            } else {
                sb.append("<font color=\"green\">&emsp;[").append(title).append("]检查正常").append("</font><br/>");
            }
        }
    }

```
#### 五、定时任务配置(一般不用变动)
    1. 在resource目录下文件applicationContext-quartz.xml中配置当前Service执行周期
    2. 在XML中添加bean
```xml
    <!-- class 指定service的全包名,指定当前service job bean id (全局唯一，servicename + job)-->
    <bean id="mainServiceJob" class="com.matrix.cim.job.MainProcessJob"></bean>
    <!-- 指定定时任务执行方法，执行参数，是否并行等属性，并设置targetObject 为以上　job 的　bean id -->
    <bean id="importOneJob" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
          <property name="targetObject" ref="mainServiceJob"/>
          <property name="targetMethod" value="compare"/>
          <property name="concurrent" value="false"/>
          <property name="arguments">
              <list></list>
          </property>
    </bean>
  
    <!-- 设置定时任务的调度周期cron 表达式 -->
    <bean id="cronTrigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
          <property name="jobDetail" ref="importOneJob"/>
          <property name="cronExpression" value="0 */1 * * * ?"/>
    </bean>

    <!-- 设置定时任务的工厂类 -->
    <bean id="schedulerFactoryBean" class="org.springframework.scheduling.quartz.SchedulerFactoryBean"
            autowire="no">
          <property name="triggers">
              <list>
                  <ref bean="cronTrigger"/>
              </list>
          </property>
    </bean>  
```
    
    
    
    