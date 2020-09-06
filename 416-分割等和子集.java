// 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

// 注意:
// 每个数组中的元素不会超过 100
// 数组的大小不会超过 200

// 示例 1:
// 输入: [1, 5, 11, 5]
// 输出: true
// 解释: 数组可以分割成 [1, 5, 5] 和 [11].
//  
// 示例 2:
// 输入: [1, 2, 3, 5]
// 输出: false
// 解释: 数组不能分割成两个元素和相等的子集.
import java.util.Arrays;
import java.util.stream.IntStream;

class Solution { // 0-1 背包问题
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;

        boolean[][] dp = new boolean[n][target + 1];
        IntStream.range(0, target + 1).forEach(i -> dp[0][i] = nums[0] == i);
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j] || (j - nums[i] >= 0 && dp[i - 1][j - nums[i]]);
            }
        }

        return dp[n - 1][target];
    }
}
