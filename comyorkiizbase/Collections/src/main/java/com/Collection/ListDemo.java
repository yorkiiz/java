package com.Collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class ListDemo {

    public static void main(String[] args) {
        List<String> ls = new ArrayList<>();
        ls.add("xiaomign");
        ls.add("xiaohuang");
        ls.add("xiaobai");
        ls.remove("xiaohuang");
        System.out.println(ls.get(1));
        System.out.println(ls.size());

        List<String> linkls = new LinkedList<>();
        linkls.add("xiaohei");
        linkls.add("xiaomao");
        linkls.add("xiaogou");
        linkls.remove(1);
        System.out.println(linkls.get(0)+linkls.get(1));

    }

}
