# 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
#
# 示例：
# 输入：
# A: [1,2,3,2,1]
# B: [3,2,1,4,7]
# 输出：3
# 解释：
# 长度最长的公共子数组是 [3, 2, 1] 。
#
# 提示：
# 1 <= len(A), len(B) <= 1000
# 0 <= A[i], B[i] < 100

class Solution:
    def findLength(self, nums1: List[int], nums2: List[int]) -> int:
        ans = 0
        dp = [0] * len(nums2)
        for i in range(len(nums1)):
            for j in range(len(nums2) - 1, -1, -1):
                if i == 0 or j == 0:
                    dp[j] = 0 if nums1[i] != nums2[j] else 1
                else:
                    dp[j] = 0 if nums1[i] != nums2[j] else dp[j - 1] + 1
                ans = max(ans, dp[j])
        return ans
