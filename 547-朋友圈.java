// 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。
// 如果已知 A 是 B 的朋友，B 是 C 的朋友，
// 那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。

// 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，
// 表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。

// 示例 1：
// 输入：
// [[1,1,0],
//  [1,1,0],
//  [0,0,1]]
// 输出：2 
// 解释：已知学生 0 和学生 1 互为朋友，他们在一个朋友圈。
// 第2个学生自己在一个朋友圈。所以返回 2 。

// 示例 2：
// 输入：
// [[1,1,0],
//  [1,1,1],
//  [0,1,1]]
// 输出：1
// 解释：已知学生 0 和学生 1 互为朋友，学生 1 和学生 2 互为朋友，
// 所以学生 0 和学生 2 也是朋友，所以他们三个在一个朋友圈，返回 1 。
//  
// 提示：
// 1 <= N <= 200
// M[i][i] == 1
// M[i][j] == M[j][i]

import java.util.stream.IntStream;

class Solution {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        var set = new UnionFindSet(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    set.union(i, j);
                }
            }
        }
        return set.getCount();
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
