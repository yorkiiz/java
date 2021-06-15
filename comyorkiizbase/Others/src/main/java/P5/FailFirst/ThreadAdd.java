package P5.FailFirst;

import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class ThreadAdd extends Thread{

    private List list;

    public ThreadAdd(List list){
        this.list = list;
    }

    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println("loop execute : " + i);
            try {
                Thread.sleep(5);
                list.add(i);
            }catch (Exception e){

            }
        }
    }
}

