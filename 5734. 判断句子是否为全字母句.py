class Solution:
    def checkIfPangram(self, sentence: str) -> bool:
        m = set()
        for c in sentence:
            m.add(c)
        return len(m) == 26
