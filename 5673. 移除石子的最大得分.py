class Solution:
    def maximumScore(self, a: int, b: int, c: int) -> int:
        ans = 0
        li = [a, b, c]
        li = sorted(li)
        ans += li[1] - li[0]
        li[1], li[2] = li[0], li[2] - (li[1] - li[0])
        if li[0] + li[1] >= li[2]:
            cut = li[2] // 2 * 2
            ans += cut
            li[0], li[1], li[2] = li[0] - cut // 2, li[1] - cut // 2, li[2] - cut
            ans += li[0]
            return ans
        else:
            ans += li[0] + li[1]
            return ans
