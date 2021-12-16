package Mytest;

import java.util.Arrays;

class GoodSolution1 {

    public int[] maxSubsequence(int[] nums, int k) {
        Integer[] index = new Integer[nums.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }
        Arrays.sort(index, (o, p) -> nums[p] - nums[o]);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = index[i];
        }
        Arrays.sort(result);
        for (int i = 0; i < k; i++) {
            result[i] = nums[result[i]];
        }
        return result;
    }

    public static void main(String[] args) {
        GoodSolution1 solution1 = new GoodSolution1();
        System.out.println(Arrays.toString(solution1.maxSubsequence(new int[]{4,-1,2,4},3)));
    }
}