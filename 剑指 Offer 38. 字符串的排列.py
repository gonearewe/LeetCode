# 输入一个字符串，打印出该字符串中字符的所有排列。
#
# 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
#
# 示例:
# 输入：s = "abc"
# 输出：["abc","acb","bac","bca","cab","cba"]
#
# 限制：
# 1 <= s 的长度 <= 8

class Solution:
    def permutation(self, s: str) -> List[str]:
        m = {}
        for c in s:
            if c not in m:
                m[c] = 1
            else:
                m[c] += 1

        ans = []

        def dfs(s: str, n: int):
            if n == 0:
                ans.append(s)
            else:
                for c, cnt in m.items():
                    if cnt == 0:
                        continue
                    m[c] -= 1
                    dfs(s + c, n - 1)
                    m[c] += 1

        dfs("", len(s))
        return ans

