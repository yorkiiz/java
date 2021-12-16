package Mytest;

public class SolutionBinarySearch {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1,-1};
        int length = nums.length;
        int startindex = 0;
        int endindex = length-1;


        if(length==0){
            return res;
        }

        if(nums[0]>target||nums[length-1]<target){
            return res;
        }

        binarysearch(nums,startindex,endindex,res,target);
        return res;



    }

    public void binarysearch(int[] nums,int startindex,int endindex,int[] res,int target){
        if(endindex-startindex==1){
            if(nums[startindex]==target){
                res[0]=startindex;
            }
            if(nums[endindex]==target){
                res[1]=endindex;
            }
        }
        int mid = (startindex+endindex+1)/2;
        if(nums[mid]>target){
            binarysearch(nums,startindex,mid,res,target);
        }else if(nums[mid]<target){
            binarysearch(nums,mid,endindex,res,target);
        }else if(nums[mid]==target){
            startindex=mid;
            endindex=mid;
            while(startindex-1>=0&&nums[startindex-1]==target){
                startindex--;
            }
            while(endindex+1<nums.length&&nums[endindex+1]==target){
                endindex++;
            }
            res[0]=startindex;
            res[1]=endindex;

        }
    }
}
