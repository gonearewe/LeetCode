// 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。
// 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
// 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。

// 示例：
// 输入：nums: [1, 1, 1, 1, 1], S: 3
// 输出：5
// 解释：

// -1+1+1+1+1 = 3
// +1-1+1+1+1 = 3
// +1+1-1+1+1 = 3
// +1+1+1-1+1 = 3
// +1+1+1+1-1 = 3

// 一共有5种方法让最终目标和为3。
//  
// 提示：
// 数组非空，且长度不会超过 20 。
// 初始的数组的和不会超过 1000 。
// 保证返回的最终结果能被 32 位整数存下。

// 此问题可以转化为子集划分问题
//
// 将一个集合划分成两个子集 A,B, 问满足 sum(A) - sum(B) = Target的分法有多少种
// 两端同时加上集合元素和 sum,得到 2 * sum(A) = Target + sum ==> sum(A) = (Target + sum) / 2
//
// 又已知 sum(A) = sum(B) + Target >= Target, sum = sum(A) + sum(B) >= sum(A)
// 故得预筛选条件 sum >= Target, 且 (Target + sum) % 2 == 0

import java.util.Arrays;

class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = Arrays.stream(nums).sum();
        if (sum < S || (sum + S) % 2 != 0) {
            return 0;
        }

        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = dp.length - 1; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[(sum + S) / 2];
    }
}
