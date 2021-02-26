# 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
#
# 示例 1:
# 输入: "aacecaaa"
# 输出: "aaacecaaa"
#
# 示例 2:
# 输入: "abcd"
# 输出: "dcbabcd"

class Solution:
    def shortestPalindrome(self, s: str) -> str:
        def preprocess(s: str) -> str:
            if not s:
                return "^$"
            T = ['^']
            for c in s:
                T.append('#')
                T.append(c)
            T.append("#$")
            return "".join(T)

        T = preprocess(s)
        P = [0] * len(T)
        C, R = 0, 0
        for i in range(1, len(T) - 1):
            i_mirror = 2 * C - i
            P[i] = min(R - i, P[i_mirror]) if R > i else 0
            while T[i + P[i] + 1] == T[i - P[i] - 1]:
                P[i] += 1
            if i + P[i] > R:
                C, R = i, i + P[i]

        end = 0
        for i, r in enumerate(P):
            if i - 1 == r:
                end = max(end, (i - r) // 2 + r - 1)
        return s[-1:end:-1] + s
