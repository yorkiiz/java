package Mytest;

import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Map<List<Integer>,Integer> resmap = new HashMap<List<Integer>,Integer>();
        List<Integer> templist;
        int length = nums.length;
        int temp=0;
        Arrays.sort(nums);
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int index=0;index<length;index++){
            map.put(target-nums[index],index);
        }

        for(int i=0;i<length-3;i++){
            for(int j=i+1;j<length-2;j++){
                for(int p=j+1;p<length-1;p++){
                    temp =  nums[i]+nums[j]+nums[p];
                    if(temp+nums[p+1]>0||temp+nums[length-1]<0){
                        //break;
                    }else{
                        if(map.containsKey(temp)&&map.get(temp)>p){
                            //templist = Arrays.aslist(nums[i],nums[j],nums[p],target-temp]);
                            templist = Arrays.asList(nums[i],nums[j],nums[p],target-temp);
                            if(!resmap.containsKey(templist)){
                                resmap.put(templist,null);
                                res.add(templist);
                            }
                        }
                    }
                }
            }
        }

        return res;

    }
}