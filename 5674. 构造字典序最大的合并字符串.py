class Solution:
    def largestMerge(self, word1: str, word2: str) -> str:
        a, b = [c for c in word1], [c for c in word2]

        def select():
            i = 0
            while i < len(a) and i < len(b):
                if a[i] > b[i]:
                    return a
                elif a[i] < b[i]:
                    return b
                else:
                    i += 1
            if i < len(a):
                return a
            else:
                return b

        ans = []
        while a or b:
            li = select()
            ans.append(li[0])
            li.pop(0)
        return "".join(ans)
