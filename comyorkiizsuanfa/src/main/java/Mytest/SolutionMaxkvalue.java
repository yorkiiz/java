package Mytest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SolutionMaxkvalue {
    public int[] maxSubsequence(int[] nums, int k) {
        int length = nums.length;
        int[] temp = new int[length];
        int[] res = new int[k];
        int tempvalue = 0;
        int index = 0;
        List<Integer> list = new LinkedList<Integer>();

        temp = Arrays.copyOf(nums,length );

        Arrays.sort(temp);
        for(int i=length-k;i<length;i++){
            list.add(temp[i]);
        }

        for(int i=0;i<length;i++){
            if(list.contains(nums[i])){
                res[index] = nums[i];
                index++;
                list.remove(new Integer(nums[i]));
            }
        }

        return res;




    }
}
