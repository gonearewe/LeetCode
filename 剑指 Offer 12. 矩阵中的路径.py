# 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
# 路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
# 如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，
# 在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
#
# [["a","b","c","e"],
#  ["s","f","c","s"],
#  ["a","d","e","e"]]
#
# 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，
# 路径不能再次进入这个格子。
#
# 示例 1：
# 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
# 输出：true
#
# 示例 2：
# 输入：board = [["a","b"],["c","d"]], word = "abcd"
# 输出：false
#
# 提示：
# 1 <= board.length <= 200
# 1 <= board[i].length <= 200

class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        w, h = len(board[0]), len(board)

        def exist(i, j, id):
            if id >= len(word):
                return True
            if i < 0 or i >= h or j < 0 or j >= w or board[i][j] != word[id]:
                return False
            cur = board[i][j]
            board[i][j] = ''
            ans = exist(i - 1, j, id + 1) or exist(i + 1, j, id + 1) or \
                  exist(i, j - 1, id + 1) or exist(i, j + 1, id + 1)
            # fails somehow
            # ans = any([exist(x, y, id + 1) for x, y in [(i - 1, j), (i + 1, j), (i, j - 1), (i, j + 1)]])
            board[i][j] = cur
            return ans

        for i in range(h):
            for j in range(w):
                if exist(i, j, 0):
                    return True
        return False
