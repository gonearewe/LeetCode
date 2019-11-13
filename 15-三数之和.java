/*
给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        int n=nums.length;

        Arrays.sort(nums);
        for(int i=0;i<n-2;i++){
            if(i>0&&nums[i]==nums[i-1]){
                i++;
                continue;
            }

            int L=i+1,R=n-1;
            while(L<R){
                int sum=nums[i]+nums[L]+nums[R];
                if(sum>0)
                    L++;
                else if(sum<0)
                    R--;
                else{
                    if(L>i+1&&nums[L]==nums[L-1]){
                        L++;
                        continue;
                    }
                    if(R<n-1&&nums[R]==nums[R+1]){
                        R--;
                        continue;
                    }

                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                }
            }
        }

        return ans;
    }
}