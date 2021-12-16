package Mytest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution39 {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            int length = target/candidates[0];
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            List<Integer> temp = new ArrayList<Integer>();
            int sum = 0;
            dfs(candidates,temp,res,target,sum);
            return res;



        }
        public void dfs(int[] candidates,List<Integer> temp,List<List<Integer>> res,int target,int sum){
            if(sum==target){
                res.add(new ArrayList<Integer>(temp));
            }else if(sum>target){
                return;
            }
            int max = temp.size()>0?Collections.max(temp):candidates[0];
            for(int i=0;i<candidates.length;i++){
                if(candidates[i]>=max){
                    sum = sum + candidates[i];
                    temp.add(candidates[i]);
                    dfs(candidates,temp,res,target,sum);
                    sum = sum -candidates[i];
                    temp.remove(temp.size()-1);
                }
            }
        }

    public static void main(String[] args) {
        Solution39 solution39 = new Solution39();
        System.out.println(solution39.combinationSum(new int[]{2,3,6,7},7));
    }

}
