// 给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges ，
// 其中 edges[i] = [fromi, toi, weighti] 表示在 fromi 和 toi 节点之间有一条带权无向边。
// 最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。

// 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，
// 那么我们就说它是一条关键边。伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。

// 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。

// 示例 1：
// 输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
// 输出：[[0,1],[2,3,4,5]]
// 解释：
// 注意到第 0 条边和第 1 条边出现在了所有最小生成树中，所以它们是关键边，我们将这两个下标作为输出的第一个列表。
// 边 2，3，4 和 5 是所有 MST 的剩余边，所以它们是伪关键边。我们将它们作为输出的第二个列表。

// 示例 2 ：
// 输入：n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
// 输出：[[],[0,1,2,3]]
// 解释：可以观察到 4 条边都有相同的权值，任选它们中的 3 条可以形成一棵 MST 。所以 4 条边都是伪关键边。
//  
// 提示：
// 2 <= n <= 100
// 1 <= edges.length <= min(200, n * (n - 1) / 2)
// edges[i].length == 3
// 0 <= fromi < toi < n
// 1 <= weighti <= 1000
// 所有 (fromi, toi) 数对都是互不相同的。

import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        var res = new ArrayList<List<Integer>>(2);
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());

        var indices = new HashMap<int[], Integer>(edges.length); // 记录原来的索引，因为接下来要排序
        for (int i = 0; i < edges.length; i++) {
            indices.put(edges[i], i);
        }
        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));

        int minWeight = weightOfMST(n, edges, 0, 0);
        for (int i = 0; i < edges.length; i++) {
            int weight = weightOfMST(n, edges, i, -1); // 跳过第 i 条边
            if (weight == -1 || weight > minWeight) {
                res.get(0).add(indices.get(edges[i])); // 这是关键边
            } else if (weight == minWeight && weightOfMST(n, edges, -1, i) == minWeight) {
                res.get(1).add(indices.get(edges[i])); // 这是伪关键边
            }
        }
        return res;
    }

    private int weightOfMST(int n, int[][] edges, int skip, int must) {
        var set = new UnionFindSet(n);
        int weight = 0, cnt = 0;
        if (must >= 0) {
            set.union(edges[must][0], edges[must][1]);
            weight += edges[must][2];
            cnt++;
            if (cnt == n - 1) {
                return weight;
            }
        }
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            if (i == skip) {
                continue;
            }
            if (set.connected(edge[0], edge[1])) { // 这不是 MST 的边
                continue;
            }

            set.union(edge[0], edge[1]);
            weight += edge[2];
            cnt++;
            if (cnt == n - 1) {
                return weight; // 返回 MST 的边权值总和
            }
        }
        return -1;
    }

    private class UnionFindSet {
        private final int[] parent;

        UnionFindSet(int n) {
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
            parent[find(a)] = find(b);
        }

        boolean connected(int a, int b) {
            return find(a) == find(b);
        }
    }
}
