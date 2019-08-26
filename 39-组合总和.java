/*
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: candidates = [2,3,6,7], target = 7,
所求解集为:
[
  [7],
  [2,2,3]
]
示例 2:

输入: candidates = [2,3,5], target = 8,
所求解集为:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
*/
class Solution {  //回溯法
    int[] nums;  //用成员变量，减少回溯函数传参
    int val;
    List<List<Integer>> ans=new ArrayList<>();//用new初始化是必须的
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);//递增排序，方便后面优化
        this.nums=candidates;
        this.val=target;
        backtrack(new Stack<>(),0,0);//使用stack保存路径
        return ans;
    }
    private void backtrack(Stack<Integer> stack,int sum,int start){
        if(sum==val){//加起来刚好是target，保存结果
            ans.add(new ArrayList<>(stack));
        }
        if(sum<val){
            for(int i=start;i<nums.length&&nums[i]+sum<=val;i++){
                //循环条件nums[i]+sum<=val保证一旦加和超过target就不会继续下去，达到剪枝效果
                //i=start配合递增排序的数组防止重复的组合（如[2,3,2]和[2,2,3]）
                stack.push(nums[i]);
                backtrack(stack, nums[i]+sum,i);
                stack.pop();//回溯时还原分支上的修改，以便其他分支继续使用同一个stack               
            }
        }
    }
}