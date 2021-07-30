# 给你一个 m 行 n 列的矩阵   matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
#
# 示例 1：
# 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
# 输出：[1,2,3,6,9,8,7,4,5]
#
# 示例 2：
# 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
# 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
#
# 提示：
# m == matrix.length
# n == matrix[i].length
# 1 <= m, n <= 10
# -100 <= matrix[i][j] <= 100

class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        ans = []

        def helper(ai: int, bi: int, aj: int, bj: int):
            for j in range(aj, bj + 1):
                ans.append(matrix[ai][j])
            for i in range(ai + 1, bi):
                ans.append(matrix[i][bj])
            # 防止重复
            if ai != bi:
                for j in range(bj, aj - 1, -1):
                    ans.append(matrix[bi][j])
            if aj != bj:
                for i in range(bi - 1, ai, -1):
                    ans.append(matrix[i][aj])

        ai, bi, aj, bj = 0, len(matrix) - 1, 0, len(matrix[0]) - 1
        while ai <= bi and aj <= bj:
            helper(ai, bi, aj, bj)
            ai += 1
            aj += 1
            bi -= 1
            bj -= 1
        return ans
