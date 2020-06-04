

## spring web项目三大核心包

```java
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.11</version>
  <scope>test</scope>
</dependency>

    
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-context</artifactId>
  <version>5.0.0.RELEASE</version>
</dependency>

<dependency>
   <groupId>javax.servlet</groupId>
   <artifactId>javax.servlet-api</artifactId>
   <version>3.1.0</version>
   <scope>provided</scope>
</dependency>
```

junit用于@Test注解，该注解用于测试单独类





## 注解

### 配置类组件

#### @configuration



传统applicationcontext.xml文件new对象

```java
ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
Object object = app.getBean("Person");
```



现在注解方式new对象

```java
@Configuration
public class Myconfig {
    @Bean
    public Person person(){
        return new Person("yongjie",18);

    }
}
```

```
ApplicationContext app = new AnnotationConfigApplicationContext(Myconfig.class);
Object bean = app.getBean("person");
```




#### @componentscan



#### @scope

prototype 原型，多例
singleton 单例
request 主要应用于web，同一次请求，应用于web模块
session 主要应用于web模块



默认为单例模式



![image-20200530124500185](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200530124500185.png)





#### @Lazy

延迟加载

![image-20200531121850877](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200531121850877.png)

![image-20200530133751377](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200530133751377.png)

对单例模式而言，在创建applicationcontext的时候加载对象，

使用Lazy模式后，在创建对象时加载对象

多例模式一律在创建对象时加载对象



#### @conditional

根据conditional里面class的返回结果判断是否要执行



```
@Bean
@Conditional(LinuxOs.class)
public Person Jie(){
    System.out.println("将Jie初始化到IOC容器");
    return new Person("Jie",18);

}

```

class继承condition接口

```
public class LinuxOs implements Condition {


    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConfigurableBeanFactory beanFactory = context.getBeanFactory();
        Environment environment = context.getEnvironment();
        String osName = environment.getProperty("os.name");
        System.out.println("osName");
        if (osName.contains("Linux")) {
            return true;
        }
        return false;
    }
}
```



#### 小结（IOC注册bean的几种方式）

1.@bean直接导入单个类

2.@ComponentScan默认扫描（@Controller，@Repository，@Component）

3.import 快速给容器导入bean的方式

  a.@import 直接参数导入

  b.实现ImportSelector自定义规则实现

  c.实现importBeanDefinitioinRegistrar，获得BeanDefinationregiste，可以直接往IOC容器中注入



#### @import

![image-20200531141037254](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200531141037254.png)



导入类名，构建bean时导入的类会被构建

![image-20200531141129254](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200531141129254.png)

导入类指定位置



#### Lifecycle

1.用bean注入

![image-20200531154521745](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200531154521745.png)

2.实现InitializingBean和DisposableBean接口

![image-20200531154547916](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200531154547916.png)

3.使用@PostConstruct和@PreDestroy注解



### 赋值组件

#### @value

![image-20200531163154760](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200531163154760.png)

配置在成员变量前，可以是值，也可以引用配置文件

![image-20200531163257712](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200531163257712.png)

引用配置文件时，config文件引用注解PropertySource

![image-20200531163338666](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200531163338666.png)

配置文件加入值