// n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
// 上图为 8 皇后问题的一种解法。
// 给定一个整数 n，返回 n 皇后不同的解决方案的数量。

// 示例:
// 输入: 4
// 输出: 2
// 解释: 4 皇后问题存在如下两个不同的解法。
// [
//  [".Q..",  // 解法 1
//   "...Q",
//   "Q...",
//   "..Q."],

//  ["..Q.",  // 解法 2
//   "Q...",
//   "...Q",
//   ".Q.."]
// ]
//  

// 提示：
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。
// 当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N-1 步，可进可退。
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
    private int cnt = 0;
    private boolean[] visited;
    private int n;

    public int totalNQueens(int n) {
        this.visited = new boolean[n];
        this.n = n;
        dfs(new ArrayList<>());
        return cnt;
    }

    private void dfs(List<Integer> one) {
        if (one.size() == n) {
            cnt++;

            return;
        }

        final var v = visited;
        var unvisited = IntStream.range(0, visited.length).filter(i -> !v[i]).toArray();
        for (final int i : unvisited) {
            if (IntStream.range(0, one.size()).noneMatch(j -> Math.abs(one.get(j) - i) == Math.abs(j - one.size()))) {
                visited[i] = true;
                one.add(i);

                dfs(one);
                
                one.remove(one.size() - 1);
                visited[i] = false;
            }
        }
    }
}
