# 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
#
# 示例 1:
#
# 输入: "abcabcbb"
# 输出: 3
# 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
# 示例 2:
#
# 输入: "bbbbb"
# 输出: 1
# 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
# 示例 3:
#
# 输入: "pwwkew"
# 输出: 3
# 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
# 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        if len(s) == 0:
            return 0
        ans = 1
        m = {s[0]: 0}  # m 动态记录窗口划过的各个字符上一次出现的位置
        begin, end = 0, 1
        while end < len(s):
            if s[end] in m and m[s[end]] >= begin:
                begin = m[s[end]] + 1
            m[s[end]] = end
            end += 1
            ans = max(ans, end - begin)
        return ans
