package exercise;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class ParamPassing {

    private static int intStatic=222;
    private static String stringStatic="oled String";
    private static StringBuilder StringBuilderStatic = new StringBuilder("old StringBuilder");

    public static void main(String[] args) {
        method(intStatic);
        method(stringStatic);
        method(StringBuilderStatic,StringBuilderStatic);

        System.out.println(intStatic);
        method();
        System.out.println(intStatic);
        System.out.println(stringStatic);
        System.out.println(StringBuilderStatic);
    }

    public static void method(int intStatic){
        intStatic=777;
    }

    public static void method(){
        intStatic = 888;
    }

    public static  void method(String stringStatic){
        stringStatic = "new String";
    }

    public static void method(StringBuilder stringBuilderStatic1,StringBuilder stringBuilderStatic2){
        stringBuilderStatic1.append(".method first1");
        stringBuilderStatic2.append(".method second");

        stringBuilderStatic1 = new StringBuilder("new StringBuilder");
        stringBuilderStatic1.append("new Stringbuilder's append");
    }

}
