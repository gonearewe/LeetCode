# 一个 平方和三元组 (a,b,c) 指的是满足 a2 + b2 = c2 的 整数 三元组 a，b 和 c 。
#
# 给你一个整数 n ，请你返回满足 1 <= a, b, c <= n 的 平方和三元组 的数目。
#
# 示例 1：
# 输入：n = 5
# 输出：2
# 解释：平方和三元组为 (3,4,5) 和 (4,3,5) 。
#
# 示例 2：
# 输入：n = 10
# 输出：4
# 解释：平方和三元组为 (3,4,5)，(4,3,5)，(6,8,10) 和 (8,6,10) 。
#
# 提示：
# 1 <= n <= 250

import math


class Solution:
    def countTriples(self, n: int) -> int:
        ans = 0
        for c in range(5, n + 1):
            for a in range(c):
                b = int(math.sqrt(c ** 2 - a ** 2))
                for i in [b - 1, b, b + 1]:  # 防止浮点数误差
                    if i ** 2 + a ** 2 == c ** 2:
                        ans += 2
                        break
        return ans
