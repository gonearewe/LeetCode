// 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

// 示例:
// 输入: n = 4, k = 2
// 输出:
// [
//   [2,4],
//   [3,4],
//   [2,3],
//   [1,2],
//   [1,3],
//   [1,4],
// ]
import java.util.ArrayList;
import java.util.List;

class Solution {
    private int k;
    private int n;
    private final List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        this.k = k;
        this.n = n;
        dfs(1, new ArrayList<>());
        return res;
    }

    private void dfs(int cur, List<Integer> one) {
        if (one.size() == k) {
            res.add(new ArrayList<>(one));
            return;
        }
        if (cur > n) {
            return;
        }

        dfs(cur + 1, one);

        one.add(cur);
        dfs(cur + 1, one);
        one.remove(one.size() - 1);
    }
}
