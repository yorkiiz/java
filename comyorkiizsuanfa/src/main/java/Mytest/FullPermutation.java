package Mytest;

import java.util.Scanner;
import java.util.stream.Stream;

public class FullPermutation {
    /**
     * 存放数据数组
     */
    private static Integer[] data;

    /**
     * 数据数量
     */
    private static Integer num;

    /**
     * 交换数据
     */
    public static void swap(int x, int y) {
        Integer temp = data[x];
        data[x] = data[y];
        data[y] = temp;
    }

    /**
     * 初始化数据
     */
    private static void initData() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入数据数量:");
        num = input.nextInt();
        System.out.println("请输入数组数据");
        data = new Integer[num];
        for (int i = 0; i < data.length; i++) {
            data[i] = input.nextInt();
        }
    }

    /**
     * 递归回溯排列树求解
     */
    private static void backtrack(int t) {
        if (t == data.length) {
            System.out.print("排列树为: ");
            Stream.of(data).forEach(element -> System.out.print(element + " "));
            System.out.println();
        }
        for (int i = t; i < data.length; i++) {
            swap(t, i);
            backtrack(t + 1);
            swap(t, i);
        }

    }


    public static void main(String[] args) {
        // 初始化数据
        initData();

        // 递归回溯排列树求解
        backtrack(0);
    }

}
