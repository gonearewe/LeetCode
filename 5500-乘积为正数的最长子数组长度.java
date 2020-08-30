// 给你一个整数数组 nums ，请你求出乘积为正数的最长子数组的长度。

// 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。

// 请你返回乘积为正数的最长子数组长度。

// 示例  1：
// 输入：nums = [1,-2,-3,4]
// 输出：4
// 解释：数组本身乘积就是正数，值为 24 。

// 示例 2：
// 输入：nums = [0,1,-2,-3,-4]
// 输出：3
// 解释：最长乘积为正数的子数组为 [1,-2,-3] ，乘积为 6 。
// 注意，我们不能把 0 也包括到子数组中，因为这样乘积为 0 ，不是正数。

// 示例 3：
// 输入：nums = [-1,-2,-3,0,1]
// 输出：2
// 解释：乘积为正数的最长子数组是 [-1,-2] 或者 [-2,-3] 。

// 示例 4：
// 输入：nums = [-1,2]
// 输出：1

// 示例 5：
// 输入：nums = [1,2,3,5,-6,4,0,10]
// 输出：4
//  
// 提示：
// 1 <= nums.length <= 10^5
// -10^9 <= nums[i] <= 10^9

// 首先不看题目，看数组长度，N = 10^5，对于这个规模的数据，O(N^2) 的算法可能会超时，
// 这题和树、二分什么的也没有关系，考虑是不是一个 O(N) 的算法

// 明显感觉是动态规划问题，注意状态是怎么转换的，
// 1）正数乘正数还是正数，乘负数变成负数；
// 2）负数乘正数还是负数，乘负数变成正数；
// 假如设 dp[i] 是以 nums[i] 结尾，乘积为正的最长子数组的长度。那么因为 dp[i+1] 不仅仅与 dp[i] 有关，
// 还可能由负数乘负数转化而来，这个设计不合理。

// 所以设 positive[i] 是以 nums[i] 结尾，乘积为正的最长子数组的长度，
// 又设 negative[i] 是以 nums[i] 结尾，乘积为负的最长子数组的长度。
// 列写正式的状态转换式：
// if (nums[i] > 0):
//     positive[i] = positive[i-1] + 1 
//     negative[i] = negative[i-1] + 1
// if (nums[i] < 0):
//     positive[i] = negative[i-1] + 1 
//     negative[i] = positive[i-1] + 1
// 除状态转换式外还需要的是初始状态，这个简单，单独判断一下就好了：
// positive[0] = nums[0] > 0 ? 1 : 0
// negative[0] = nums[0] < 0 ? 1 : 0

// 但是先别急着写代码，有了思路之后的第一件事应该是代入用例验证一下，以防设计出错，写了半天白忙活。
// 看了用例一下就发现了，还有 nums[i] == 0 的情况没有考虑。
// 以 nums[i] 结尾，乘积为正或负的最长子数组的长度显然都是零了。那么：
// if (nums[i] == 0):
//     positive[i] = 0
//     negative[i] = 0
// 再看一下示例 2，发现不对劲：
// nums       0 1 -2 -3 -4
// positive   0 1  2  3  4
// negative   0 1  2  3  4
// expected: 3 found: 4
// 原因在于 nums[0] == 0 重置了状态，其后的 positive[1]、negative[1] 需要像初始状态一样考虑。
// 换句话说，当 negative[i-1] == 0，nums[i] 为正数时 negative[i] 还是 0，为负数时也没有
// 负负得正的效果，positive[i] 不等于 negative[i-1] + 1。
// 综上，修正状态转换式：
// if (nums[i] > 0):
//     positive[i] = positive[i-1] + 1 
//     negative[i] = negative[i-1] == 0 ? 0 : negative[i-1] + 1
// if (nums[i] < 0):
//     positive[i] = negative[i-1] == 0 ? 0 : negative[i-1] + 1 
//     negative[i] = positive[i-1] + 1
// 同时注意，这里也包含了初始状态，不需要再单独考虑。

// 最后注意，状态 i 仅仅与状态 i-1 有关，所以可以对这个一维线性动态规划进行状态压缩，仅用迭代
// 变量 positive, negative 代替动态规划的数组。
class Solution {
    public int getMaxLen(int[] nums) {
        int positive = 0, negative = 0;
        int res = 0;
        for (final int num : nums) {
            if (num == 0) {
                positive = 0;
                negative = 0;
            } else if (num > 0) {
                positive++;
                negative = negative == 0 ? 0 : negative + 1;
            } else {
                int lastNegative = negative;
                negative = positive + 1;
                positive = lastNegative == 0 ? 0 : lastNegative + 1;
            }
            res = Math.max(res, positive);
        }
        return res;
    }
}
