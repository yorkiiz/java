package P5.S14Anno;

/**
 * 注解的本质就是接口
 */
public @interface MyAnno {

    String value();
    MyAnno2 show4();
    PersonEnum show5();
    String[] show3();
    //String name();
    //int age() default 18; // 指定默认值 在使用注解的时候没有给该属性赋值，那么就使用默认值

    /*String show1();*/
    /*int show2();
    String[] show3();
    MyAnno2 show4();
    PersonEnum show5();*/

}
