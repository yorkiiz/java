package fileclass;

import java.io.*;
import java.util.HashMap;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class FileReadv1 {
    public static void main(String[] args) throws IOException {
        File filei = new File("E:\\工作\\咕泡学院\\git\\comyorkiizbase\\com.yorkiiz.IO\\src\\source.txt");
        File fileo = new File("E:\\工作\\咕泡学院\\git\\comyorkiizbase\\com.yorkiiz.IO\\src\\source2.txt");

        BufferedReader bfd = new BufferedReader(new FileReader(filei));
        BufferedWriter bfw = new BufferedWriter(new FileWriter(fileo));

        String line;
        HashMap<String, String> map = new HashMap<>();
        String[] arr;
        while ((line = bfd.readLine())!= null){
           arr = line.split("->");
           map.put(arr[0],arr[1]);

        }

        System.out.println(map);
        for (String s : map.keySet()) {
            bfw.write(map.get(s));
            bfw.newLine();
        }

        bfd.close();
        bfw.close();



    }


}
