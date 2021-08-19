# 给定两个字符串  s  和 p，找到  s  中所有  p  的  异位词  的子串，
# 返回这些子串的起始索引。不考虑答案输出的顺序。
# 异位词 指字母相同，但排列不同的字符串。
#
# 示例  1:
# 输入: s = "cbaebabacd", p = "abc"
# 输出: [0,6]
# 解释:
# 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
# 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
#
# 示例 2:
# 输入: s = "abab", p = "ab"
# 输出: [0,1,2]
# 解释:
# 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
# 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
# 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
#
# 提示:
# 1 <= s.length, p.length <= 3 * 104
# s  和  p  仅包含小写字母
class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        need, window = {}, {}
        need_cnt = len(p)
        for c in p:
            if c not in need:
                need[c] = 0
            need[c] += 1

        left = 0
        ans = []
        for right in range(len(s)):
            if s[right] not in window:
                window[s[right]] = 0
            window[s[right]] += 1
            if s[right] in need and window[s[right]] <= need[s[right]]:
                need_cnt -= 1

            while right - left + 1 >= len(p):
                if need_cnt == 0 and right - left + 1 == len(p):
                    ans.append(left)

                if s[left] in need and window[s[left]] <= need[s[left]]:
                    need_cnt += 1
                window[s[left]] -= 1
                left += 1
        return ans

