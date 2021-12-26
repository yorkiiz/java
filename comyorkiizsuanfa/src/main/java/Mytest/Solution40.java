package Mytest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Solution40 {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            List<Integer> temp = new ArrayList<Integer>();
            int index=0;

            Arrays.sort(candidates);
            dfs(res,temp,index,target,candidates);
            return res;
        }

        public void dfs(List<List<Integer>> res,List<Integer> temp,int index,int target,int[] candidates){
            if(target==0){
                if(!res.contains(temp)){
                    res.add(new ArrayList(temp));
                }
                return;
            }else if(target<0){
                return;
            }
            for(int i=index;i<candidates.length;i++){
                if(target-candidates[i]>=0){
                    if(i>index&&candidates[i]==candidates[i-1]){
                        continue;
                    }
                    temp.add(candidates[i]);
                    index=i+1;
                    target=target-candidates[i];
                    dfs(res,temp,index,target,candidates);
                    temp.remove(temp.size()-1);
                    index=i-1;
                    target=target+candidates[i];
                }
            }
        }

    public static void main(String[] args) {
        Solution40 solution40 = new Solution40();
        System.out.println(solution40.combinationSum2(new int[]{10,1,2,7,6,1,5,2},8));
    }
}
