# 给你一个字符串 s ，返回 s 中 长度为 3 的不同回文子序列 的个数。
#
# 即便存在多种方法来构建相同的子序列，但相同的子序列只计数一次。
#
# 回文 是正着读和反着读一样的字符串。
#
# 子序列 是由原字符串删除其中部分字符（也可以不删除）且不改变剩余字符之间相对顺序形成的一个新字符串。
#
# 例如，"ace" 是 "abcde" 的一个子序列。
#
# 示例 1：
# 输入：s = "aabca"
# 输出：3
# 解释：长度为 3 的 3 个回文子序列分别是：
# - "aba" ("aabca" 的子序列)
# - "aaa" ("aabca" 的子序列)
# - "aca" ("aabca" 的子序列)
#
# 示例 2：
# 输入：s = "adc"
# 输出：0
# 解释："adc" 不存在长度为 3 的回文子序列。
#
# 示例 3：
# 输入：s = "bbcbaba"
# 输出：4
# 解释：长度为 3 的 4 个回文子序列分别是：
# - "bbb" ("bbcbaba" 的子序列)
# - "bcb" ("bbcbaba" 的子序列)
# - "bab" ("bbcbaba" 的子序列)
# - "aba" ("bbcbaba" 的子序列)
#
# 提示：
# 3 <= s.length <= 105
# s 仅由小写英文字母组成

class Solution:
    def countPalindromicSubsequence(self, s: str) -> int:
        left, right = {}, {}
        for c in s:
            if c not in right:
                right[c] = 0
            right[c] += 1

        ans = {}
        for i in range(ord('a'), ord('z') + 1):
            ans[chr(i)] = set()

        for i, c in enumerate(s):
            right[c] -= 1
            if right[c] == 0:
                del right[c]

            if i != 0:
                if s[i - 1] not in left:
                    left[s[i - 1]] = 0
                left[s[i - 1]] += 1
            print(i, left, right)
            ans[c] = ans[c] | (left.keys() & right.keys())
        return sum([len(m) for m in ans.values()])
