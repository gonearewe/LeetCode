// 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
// 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

// 示例:
// X X X X
// X O O X
// X X O X
// X O X X

// 运行你的函数后，矩阵变为：
// X X X X
// X X X X
// X X X X
// X O X X

// 解释:
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'V'。 任何不在边界上，
// 或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'V'。如果两个元素在水平或垂直方向相邻，
// 则称它们是“相连”的。

// 1. 对于每一个边界上的 O，我们以它为起点，标记所有与它直接或间接相连的字母 O；
// 2. 最后我们遍历这个矩阵，对于每一个字母：
//    a.如果该字母被标记过，则该字母为没有被字母 V 包围的字母 O，我们将其还原为字母 O；
//    b.如果该字母没有被标记过，则该字母为被字母 V 包围的字母 O，我们将其修改为字母 V。

class Solution {
    private char[][] board;

    public void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }
        this.board = board;

        for (int i = 0; i < board.length; i++) {
            dfs(i, 0);
            dfs(i, board[i].length - 1);
        }
        for (int j = 1; j < board[0].length - 1; j++) {
            dfs(0, j);
            dfs(board.length - 1, j);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                var c = board[i][j];
                if (c == 'V') {
                    board[i][j] = 'O';
                } else if (c == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    // dfs 不要求 i,j 是有效的坐标，也不要求该坐标的字母是 'O'，这样可以把判断的逻辑全部收集到
    // 函数的一开始，简化模板代码
    private void dfs(int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[i].length) {
            return;
        }
        if (board[i][j] != 'O') {
            return;
        }

        board[i][j] = 'V';
        dfs(i - 1, j);
        dfs(i + 1, j);
        dfs(i, j - 1);
        dfs(i, j + 1);
    }

    // for debugging
    private static void print(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
