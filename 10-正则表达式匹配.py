# 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
#
# '.' 匹配任意单个字符
# '*' 匹配零个或多个前面的那一个元素
# 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
#
# 说明:
# s 可能为空，且只包含从 a-z 的小写字母。
# p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
#
# 示例 1:
# 输入:
# s = "aa"
# p = "a"
# 输出: false
# 解释: "a" 无法匹配 "aa" 整个字符串。
#
# 示例 2:
# 输入:
# s = "aa"
# p = "a*"
# 输出: true
# 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
#
# 示例 3:
# 输入:
# s = "ab"
# p = ".*"
# 输出: true
# 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
#
# 示例 4:
# 输入:
# s = "aab"
# p = "c*a*b"
# 输出: true
# 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
#
# 示例 5:
# 输入:
# s = "mississippi"
# p = "mis*is*p*."
# 输出: false

# class Solution:
#     def isMatch(self, s: str, p: str) -> bool:
#         if p == '':
#             return s == ''  # 模式有可能匹配空字符串，放行空模式
#
#         match = bool(s) and p[0] in [s[0], '.']
#         # p[0] in [s[0], '.'] 等价于 p[0] == s[0] or p[0] == '.'
#         # 前者是判断元素在不在集合里，后者是遍历集合看与元素相不相等
#         if len(p) >= 2 and p[1] == '*':
#             return self.isMatch(s, p[2:]) or match and self.isMatch(s[1:], p)
#         else:
#             return match and self.isMatch(s[1:], p[1:])

# 如果 p.charAt(j) == s.charAt(i) : dp[i][j] = dp[i-1][j-1]；
# 如果 p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1]；
# 如果 p.charAt(j) == '*'：
#   如果 p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2] // in this case, a* only counts as empty
#   如果 p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.'：
#       dp[i][j] = dp[i-1][j] // in this case, a* counts as multiple a
#       or dp[i][j] = dp[i][j-1] // in this case, a* counts as single a
#       or dp[i][j] = dp[i][j-2] // in this case, a* counts as empty
# 作者：kao-la-7
# 链接：https://leetcode-cn.com/problems/regular-expression-matching/solution/
# dong-tai-gui-hua-zen-yao-cong-0kai-shi-si-kao-da-b/


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        dp = [[False for _ in range(len(p) + 1)] for _ in range(len(s) + 1)]
        # 空串模式串不匹配任何字符串，不过第零列本来就是 False
        # for i in range(len(s) + 1):
        #     dp[i][0] = False
        dp[0][0] = True  # 空串匹配空串
        for j in range(2, len(p) + 1, 2):  # 第零行，a*a*a* 模式可以匹配空串
            if p[j - 1] == '*':
                dp[0][j] = dp[0][j - 2]

        for i in range(1, len(s) + 1):
            for j in range(1, len(p) + 1):
                if p[j - 1] in [s[i - 1], '.']:
                    dp[i][j] = dp[i - 1][j - 1]
                elif p[j - 1] == '*':
                    if s[i - 1] == p[j - 2] or p[j - 2] == '.':
                        dp[i][j] = dp[i][j - 1] or dp[i - 1][j] or dp[i][j - 2]
                    else:  # a* only counts as empty
                        dp[i][j] = dp[i][j - 2]
                else:  # 字符不匹配
                    dp[i][j] = False

        return dp[-1][-1]


if __name__ == "__main__":
    inputs = [("aa", "a"), ("aa", "a*"), ("ab", ".*"), ("aab", "c*a*b"), ("mississippi", "mis*is*p*.")]
    expects = [False, True, True, True, False]
    for i in range(len(inputs)):
        result = Solution().isMatch(inputs[i][0], inputs[i][1])
        if expects[i] != result:
            print("fail: expect %s: got %s" % (expects[i], result))
