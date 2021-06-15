package P5.S14Anno;

import java.util.Date;

@SuppressWarnings("all")
public class AnnoDemo01 {

    @Override
    public String toString() {
        return "AnnoDemo01{}";
    }

    @Deprecated
    public void show1(){
        // 发现过时了，功能更不上需求了
    }

    @MyAnno(value="bobo",show4 = @MyAnno2,show5 = PersonEnum.P1,show3 = "a")
    public void show2(){
        // 功能更加强大的方法
    }

    public void demo(){
        show1(); // 不推荐使用，但是可以使用
        show2();
        Date date = new Date();
        date.getYear();
    }
}
