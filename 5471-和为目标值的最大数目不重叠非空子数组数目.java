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

// 设 dp[i] 表示前 i 个数字（不包括索引 i）中，满足要求的子数组个数。
// 当 i = 0 时，显然有 dp[0] = 0。
// 当 i > 0 时，有两种情况：
//    a.如果存在最近的 j 使得 sum(nums[j] .. nums[i-1]) 等于 target，那么：
//      dp[i] = max(dp[i-1], dp[j-1] + 1)。
//    b.如果不存在这样的 j，那么 dp[i] 只能是 dp[i-1]，因为第 i 个数组没有做出任何贡献。

import java.util.HashMap;
import java.util.Map;

class Solution { // 动态规划
    private Map<Integer, Integer> map = new HashMap<>(); // key 为前缀和，value 为最近的位置

    public int maxNonOverlapping(int[] nums, int target) {
        int sum = 0;
        var dp = new int[nums.length + 1]; // 注意 nums.length + 1
        dp[0] = 0;
        map.put(0, 0);
        for (int i = 1; i <= nums.length; i++) {
            sum += nums[i - 1];
            var last = map.get(sum - target);
            if (last == null) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = Math.max(dp[last] + 1, dp[i - 1]);
            }
            map.put(sum, i);
        }

        return dp[nums.length];
    }
}
