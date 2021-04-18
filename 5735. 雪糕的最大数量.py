class Solution:
    def maxIceCream(self, costs: List[int], coins: int) -> int:
        costs.sort()
        total = 0
        for i, cost in enumerate(costs):
            total += cost
            if cost > coins:
                return i
        return len(costs)
