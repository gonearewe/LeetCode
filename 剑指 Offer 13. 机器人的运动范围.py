# 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
# 也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，
# 机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，
# 因为3+5+3+8=19。请问该机器人能够到达多少个格子？
#
# 示例 1：
# 输入：m = 2, n = 3, k = 1
# 输出：3
#
# 示例 2：
# 输入：m = 3, n = 1, k = 0
# 输出：1
#
# 提示：
# 1 <= n,m <= 100
# 0 <= k <= 20

class Solution:
    def movingCount(self, m: int, n: int, k: int) -> int:
        def canEnter(i: int, j: int) -> bool:
            sum = 0

            def help(a: int) -> bool:
                while a != 0:
                    nonlocal sum
                    sum += a % 10
                    if sum > k:
                        return False
                    a //= 10
                return True

            return help(i) and help(j)

        dp = [canEnter(0, j) for j in range(n)]
        for j in range(1, n):
            dp[j] = dp[j] and dp[j - 1]
        ans = dp.count(True)

        for i in range(1, m):
            for j in range(n):
                if not canEnter(i, j):
                    dp[j] = False
                    continue
                if j != 0:
                    dp[j] = dp[j] or dp[j - 1]
            ans += dp.count(True)
        return ans
