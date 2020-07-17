// 给你一个整数数组 nums 和一个整数 k。
// 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
// 请返回这个数组中「优美子数组」的数目。

// 示例 1：
// 输入：nums = [1,1,2,1,1], k = 3
// 输出：2
// 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。

// 示例 2：
// 输入：nums = [2,4,6], k = 1
// 输出：0
// 解释：数列中不包含任何奇数，所以不存在优美子数组。

// 示例 3：
// 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
// 输出：16
//  
// 提示：
// 1 <= nums.length <= 50000
// 1 <= nums[i] <= 10^5
// 1 <= k <= nums.length
class Solution {
    private int cnt;

    public int numberOfSubarrays(int[] nums, int k) {
        var oddNum = new int[nums.length];
        if (nums[0] % 2 == 0) {
            oddNum[0] = 0;
            nums[0] = 0;
        } else {
            oddNum[0] = 1;
            nums[0] = 1;
        }
        var i = 1;
        while (i < nums.length) {
            if (nums[i] % 2 != 0) {
                oddNum[i] = oddNum[i - 1] + 1;
                nums[i] = 1;
            } else {
                oddNum[i] = oddNum[i - 1];
                nums[i] = 0;
            }
            i++;
        }

        var left = 0;
        var right = 0;
        while (right < nums.length) {
            var n = 0;
            if (left == 0) {
                n = nums[right];
            } else {
                n = nums[right] - nums[left - 1];
            }

            if (n != k) {
                right++;
                continue;
            }

            var r = right;
            while (right < nums.length && nums[right] == 0) {
                right++;
            }

            var l = left;
            if (nums[l] == 0) {
                while (nums[left] == 0) {
                    left++;
                }
            }else{
                left=l+1;
            }

            cnt += (right - r) * (left - l);
        }

        return cnt;
    }

    public static void main(String[] args) {
        var a = new Solution();
        a.numberOfSubarrays(new int[] { 1, 1, 2, 1, 1 }, 3);
    }
}