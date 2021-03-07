import heapq as hp


class Solution:
    def findCheapestPrice(self, n: int, flights: List[List[int]], src: int, dst: int, K: int) -> int:
        vertices = [[] for _ in range(n)]
        for u, v, cost in flights:
            vertices[u].append((v, cost))

        heap = []
        for v, cost in vertices[src]:
            hp.heappush(heap, (cost, v, 0))
        while heap:
            costSum, u, curK = hp.heappop(heap)
            if u == dst:
                return costSum
            if curK >= K:
                continue
            for v, cost in vertices[u]:
                hp.heappush(heap, (cost + costSum, v, curK + 1))

        return -1
