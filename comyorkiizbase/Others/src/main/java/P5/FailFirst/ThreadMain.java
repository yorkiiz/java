package P5.FailFirst;

import P5.S11.ThreadAdd;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class ThreadMain {

    private static List list = new ArrayList();

    public static void main(String[] args) {
        new ThreadAdd(list).start();
        new ThreadIterate(list).start();
    }
}

