class Solution:
    def minTrioDegree(self, n: int, edges: List[List[int]]) -> int:
        edgeM = [[] for _ in range(n + 1)]
        for a, b in edges:
            edgeM[a].append(b)
            edgeM[b].append(a)

        m = {}
        for a in range(1, n + 1):
            for i in range(len(edgeM[a]) - 1):
                b = edgeM[a][i]
                for j in range(i + 1, len(edgeM[a])):
                    c = edgeM[a][j]
                    key = "".join(map(str, sorted([a, b, c])))
                    if c not in edgeM[b] or key in m:
                        continue
                    m[key] = len(edgeM[a]) + len(edgeM[b]) + len(edgeM[c]) - 6
        print(edgeM, m)
        return -1 if not m else min(m.values())
# 6
# [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]
# 7
# [[1,3],[4,1],[4,3],[2,5],[5,6],[6,7],[7,5],[2,6]]
# 3
# [[3,2],[2,1]]
# 6
# [[6,5],[4,3],[5,1],[1,4],[2,3],[4,5],[2,6],[1,3]]
