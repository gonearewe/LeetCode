# 给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges ，
# 其中 edges[i] = [fromi, toi, weighti] 表示在 fromi 和 toi 节点之间有一条带权无向边。
# 最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。
#
# 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，
# 那么我们就说它是一条关键边。伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
#
# 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。
#
# 示例 1：
# 输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
# 输出：[[0,1],[2,3,4,5]]
# 解释：
# 注意到第 0 条边和第 1 条边出现在了所有最小生成树中，所以它们是关键边，我们将这两个下标作为输出的第一个列表。
# 边 2，3，4 和 5 是所有 MST 的剩余边，所以它们是伪关键边。我们将它们作为输出的第二个列表。
#
# 示例 2 ：
# 输入：n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
# 输出：[[],[0,1,2,3]]
# 解释：可以观察到 4 条边都有相同的权值，任选它们中的 3 条可以形成一棵 MST 。所以 4 条边都是伪关键边。
#
# 提示：
# 2 <= n <= 100
# 1 <= edges.length <= min(200, n * (n - 1) / 2)
# edges[i].length == 3
# 0 <= fromi < toi < n
# 1 <= weighti <= 1000
# 所有 (fromi, toi) 数对都是互不相同的。

class Solution:
    def findCriticalAndPseudoCriticalEdges(self, n: int, edges: List[List[int]]) -> List[List[int]]:
        class UnionFindSet:
            def __init__(self, n: int):
                self.parent = [i for i in range(n)]

            def _find(self, i: int):
                if self.parent[i] == i:
                    return i
                self.parent[i] = self._find(self.parent[i])
                return self.parent[i]

            def union(self, i: int, j: int):
                self.parent[self._find(i)] = self._find(j)

            def connected(self, i: int, j: int):
                return self._find(i) == self._find(j)

        def kruskal(skip: int, must: int):
            sum = 0
            set = UnionFindSet(n)
            if must >= 0:
                set.union(edges[must][0], edges[must][1])
                sum += edges[must][2]
            for i, e in enumerate(edges):
                v0, v1, w = e
                if i == skip or set.connected(v0, v1):
                    continue
                set.union(v0, v1)
                sum += w
            return sum

        m = {}
        for i, e in enumerate(edges):
            m["".join(map(str, e))] = i
        edges.sort(key=lambda e: e[2])
        base = kruskal(-1, -1)
        ans = [[], []]
        for i, e in enumerate(edges):
            if kruskal(-1, i) != base:
                continue
            if kruskal(i, -1) == base:
                ans[1].append(m["".join(map(str, e))])
            else:
                ans[0].append(m["".join(map(str, e))])

        return ans
