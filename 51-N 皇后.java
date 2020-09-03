// n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

// 示例：
// 输入：4
// 输出：[
//  [".Q..",  // 解法 1
//   "...Q",
//   "Q...",
//   "..Q."],

//  ["..Q.",  // 解法 2
//   "Q...",
//   "...Q",
//   ".Q.."]
// ]
// 解释: 4 皇后问题存在两个不同的解法。
//  
// 提示：
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。

import java.util.ArrayList;
import java.util.List;

// 问题转化为“求满足要求的全排列”：全排列 one[n] 中 value = one[i] 表示第 i 行
// 第 value 列放置皇后（全排列保证任意两个皇后不在同一行、同一列），
// one[n] 需满足对所有的 i,j <- [0,n)，abs(one[i],one[j]) != abs(i,j)
// （即任意两个皇后不在同一对角线）
class Solution {
    private final List<List<Integer>> res = new ArrayList<>();
    private boolean[] taken;
    private int n;

    public List<List<String>> solveNQueens(int n) {
        this.taken = new boolean[n];
        this.n = n;
        dfs(n, new ArrayList<>());
        return generateResults();
    }

    // leftN 表示还需要放下的皇后数，one 容纳已放下的皇后
    private void dfs(int leftN, List<Integer> one) {
        // 当前函数要在第 one.size() 行放置一个皇后

        if (leftN == 0) { // end of DFS
            res.add(new ArrayList<>(one)); // copy the path to the results 
            return;
        }

        for (int i = 0; i < n; i++) {
            if (taken[i]) { // 第 i 列已经有皇后了
                continue;
            }

            // 扫描 (one.size(),i) 坐标的皇后是否与先前的皇后对角线冲突
            boolean ok = true;
            for (int j = 0; j < one.size(); j++) { 
                if (Math.abs(one.get(j) - i) == Math.abs(j - one.size())) {
                    ok = false;
                }
            }

            if (ok) { // (one.size(),i) 坐标可以放皇后
                taken[i] = true;
                one.add(i);
                dfs(leftN - 1, one); // 对下一层的搜索前后围绕着全局变量的标记与还原
                one.remove(one.size() - 1); // `one` is reused throughout the whole DFS
                taken[i] = false;
            }
        }
    }

    // 不重要，转化为题目要求的输出结果
    private List<List<String>> generateResults() {
        var ret = new ArrayList<List<String>>(res.size());
        for (var r : res) {
            var e = new ArrayList<String>(n);
            for (var col : r) {
                var row = new StringBuilder();
                for (int i = 0; i < col; i++) {
                    row.append(".");
                }
                row.append("Q");
                for (int i = row.length(); i < n; i++) {
                    row.append(".");
                }
                e.add(row.toString());
            }
            ret.add(e);
        }
        return ret;
    }
}
