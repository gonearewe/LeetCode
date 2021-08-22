# 给你一个字符串 s，找到 s 中最长的回文子串。
#
# 示例 1：
# 输入：s = "babad"
# 输出："bab"
# 解释："aba" 同样是符合题意的答案。
#
# 示例 2：
# 输入：s = "cbbd"
# 输出："bb"
#
# 示例 3：
# 输入：s = "a"
# 输出："a"
#
# 示例 4：
# 输入：s = "ac"
# 输出："a"
#
# 提示：
# 1 <= s.length <= 1000
# s 仅由数字和英文字母（大写和/或小写）组成

class Solution:
    def longestPalindrome(self, s: str) -> str:
        def expand(i, j):
            while i >= 0 and j < len(s) and s[i] == s[j]:
                i -= 1
                j += 1
            return i + 1, j - 1

        left, right = 0, 0
        for i in range(len(s)):
            l, r = expand(i, i)
            if r - l > right - left:
                right, left = r, l
        for i in range(len(s) - 1):
            if s[i] == s[i + 1]:
                l, r = expand(i, i + 1)
                if r - l > right - left:
                    right, left = r, l
        return s[left:right + 1]
