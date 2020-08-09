// 给你一个数组 nums 和一个整数 target 。
// 请你返回 非空不重叠 子数组的最大数目，且每个子数组中数字和都为 target 。

// 示例 1：
// 输入：nums = [1,1,1,1,1], target = 2
// 输出：2
// 解释：总共有 2 个不重叠子数组（加粗数字表示） [1,1,1,1,1] ，它们的和为目标值 2 。

// 示例 2：
// 输入：nums = [-1,3,5,1,4,2,-9], target = 6
// 输出：2
// 解释：总共有 3 个子数组和为 6 。
// ([5,1], [4,2], [3,5,1,4,2,-9]) 但只有前 2 个是不重叠的。

// 示例 3：
// 输入：nums = [-2,6,6,3,5,4,1,2,8], target = 10
// 输出：3

// 示例 4：
// 输入：nums = [0,0,0], target = 0
// 输出：3
 
// 提示：
// 1 <= nums.length <= 10^5
// -10^4 <= nums[i] <= 10^4
// 0 <= target <= 10^6

import java.util.ArrayList;
import java.util.List;

class Solution {
    private int[] nums;
    private int[] sums;
    public int maxNonOverlapping(int[] nums, int target) {
        this.nums=nums;
        calc();
        return backtrace(0,target);
    }

    private void calc(){
        sums =new int[nums.length];
        sums[0]=nums[0];
        for(int i=1;i< sums.length;i++){
            sums[i]=sums[i-1]+nums[i];
        }
    }

    private List<Integer> findEnds(int start,int target){
        var li=new ArrayList<Integer>();
        if(nums[start]==target){
            li.add(start+1);
        }
        for(int i=start+2;i<=sums.length;i++){
            if (sums[i-1]-sums[start]+nums[start]==target){
                li.add(i);
            }
        }
        return li;
    }

    private int backtrace(int start,int target){

        for(int i=start;i<nums.length;i++){
            var ends=findEnds(i,target);
            var max=0;
            for(var end:ends){
                var submax=backtrace(end,target);
                if(submax>max){
                    max=submax;
                }
            }
        }
    }
}
