class Solution:
    def largeGroupPositions(self, s: str) -> List[List[int]]:
        i = 0
        ans = []
        while i < len(s):
            start = i
            while i < len(s) and s[i] == s[start]:
                i += 1
            if i - start >= 3:
                ans.append([start, i - 1])
        return ans
