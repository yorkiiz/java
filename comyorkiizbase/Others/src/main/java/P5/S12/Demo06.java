package P5.S12;

public class Demo06 {

    public static void main(String[] args) {
        Person p = new Person();
        p.setIdCard("43xxxxxx123");

        PersonBean bean = new PersonBean();
        bean.setAddress("湖南长沙");
        bean.setName("波波");
        bean.setAge(18);
        PersonNew<PersonBean> pn = new PersonNew(bean);
        PersonNew<String> pn1 = new PersonNew<>("hello");
    }
}
