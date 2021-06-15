package P5.S13FS;

public  final class User extends Person{

    private String name;

    public String sex;

    public static String address;

    public User() {
    }

    private User(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



    private void jump(){
        System.out.println("jump ... ");
    }

    public static void say(String msg){
        System.out.println("say:" + msg);
    }
}
