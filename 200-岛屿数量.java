// 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
// 此外，你可以假设该网格的四条边均被水包围。

// 示例 1:
// 输入:
// [
// ['1','1','1','1','0'],
// ['1','1','0','1','0'],
// ['1','1','0','0','0'],
// ['0','0','0','0','0']
// ]
// 输出: 1

// 示例 2:
// 输入:
// [
// ['1','1','0','0','0'],
// ['1','1','0','0','0'],
// ['0','0','1','0','0'],
// ['0','0','0','1','1']
// ]
// 输出: 3
// 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。

import java.util.stream.IntStream;
// 并查集
class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        var set = new UnionFindSet(m * n + 1);
        int virtualPoint = m * n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') { 
                    // 当前是“陆地”，尝试与周围（按顺序遍历，只需要处理“右”和“下”）合并一下；
                    if (i < m - 1 && grid[i + 1][j] == '1') {
                        set.union(i * n + j, (i + 1) * n + j);
                    }
                    if (j < n - 1 && grid[i][j + 1] == '1') {
                        set.union(i * n + j, i * n + j + 1);
                    }
                } else {
                    // 当前是“水域”，就把所有的“水域”合并在一起（通过与虚拟的结点连接）
                    set.union(i * n + j, virtualPoint);
                }
            }
        }
        return set.getCount() - 1; // 连通图数目减去一个水域
    }

    private class UnionFindSet {
        private final int[] parent;
        private int cnt; // 记录连通图数目

        UnionFindSet(int n) {
            cnt = n;
            parent = IntStream.range(0, n).toArray();
        }

        // 彻底路径压缩（推荐）
        int find(int a) {
            if (parent[a] != a) { // I'm not the root
                parent[a] = find(parent[a]); // set my parent to the root
            }
            return parent[a]; // return the root
        }

        void union(int a, int b) {
            if (find(a) != find(b)) {
                cnt--;
                parent[find(a)] = find(b);
            }
        }

        int getCount() {
            return cnt;
        }
    }
}
