package Mytest;

public class SolutionBinarySearchGood {
    public int[] searchRange(int[] nums, int target) {
        int length = nums.length;
        int[] res = new int[]{-1,-1};
        int startindex = 0;
        int endindex = length-1;
        if(length==0){
            return res;
        }

        startindex = findfirstindex(nums,0,length-1,target);
        endindex = findlastindex(nums,0,length-1,target);
        if(startindex!=-1&&endindex!=-1){
            res[0]=startindex;
            res[1]=endindex;
        }
        return res;

    }

    public int findfirstindex(int[] nums,int startindex,int endindex,int target){
        while(startindex<endindex){
            int mid =(startindex+endindex)>>>1;
            if(nums[mid]>target){
                endindex = mid-1;
            }else if(nums[mid]<target){
                startindex=mid+1;
            }else if(nums[mid]==target){
                endindex=mid;
            }
        }
        if(nums[startindex]==target){
            return startindex;
        }
        return -1;
    }

    public int findlastindex(int[] nums,int startindex,int endindex,int target){
        while(startindex<endindex){
            int mid =(startindex+endindex+1)>>>1;
            if(nums[mid]>target){
                endindex = mid-1;
            }else if(nums[mid]<target){
                startindex=mid+1;
            }else if(nums[mid]==target){
                startindex=mid;
            }
        }
        if(nums[endindex]==target){
            return endindex;
        }
        return -1;
    }
}
