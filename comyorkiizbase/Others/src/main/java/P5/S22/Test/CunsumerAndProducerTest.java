package P5.S22.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class CunsumerAndProducerTest {

    public static void main(String[] args) {
        Queue<String> list = new LinkedList<String>();
        Integer size = 15;

        Thread consumer = new Consumer(list,size);
        Thread producer = new Producer(list,15);
        consumer.start();
        producer.start();


    }

}
