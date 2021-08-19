# 给你两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
# 换句话说，s1 的排列之一是 s2 的 子串 。
#
# 示例 1：
# 输入：s1 = "ab" s2 = "eidbaooo"
# 输出：true
# 解释：s2 包含 s1 的排列之一 ("ba").
#
# 示例 2：
# 输入：s1= "ab" s2 = "eidboaoo"
# 输出：false
#
# 提示：
# 1 <= s1.length, s2.length <= 104
# s1 和 s2 仅包含小写字母

class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        if len(s1) > len(s2):
            return False

        need = {}
        for c in s1:
            if c not in need:
                need[c] = 0
            need[c] += 1

        window = {}
        for i in range(len(s1)):
            if s2[i] not in window:
                window[s2[i]] = 0
            window[s2[i]] += 1

        def check(need, window):
            for k, v in need.items():
                if k not in window or window[k] < v:
                    return False
            return True

        for i in range(len(s1), len(s2)):
            if check(need, window):
                return True

            if s2[i] not in window:
                window[s2[i]] = 0
            window[s2[i]] += 1

            window[s2[i - len(s1)]] -= 1
        return check(need, window)
           
