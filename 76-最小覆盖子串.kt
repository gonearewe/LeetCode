// 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。

// 示例：
// 输入: S = "ADOBECODEBANC", T = "ABC"
// 输出: "BANC"
// 说明：
// 如果 S 中不存这样的子串，则返回空字符串 ""。
// 如果 S 中存在这样的子串，我们保证它是唯一的答案。

class Solution {
    private val requirement = HashMap<Char, Int>() // 滑动窗口需要满足的条件
    private val window = HashMap<Char, Int>() // 记录滑动窗口当前状态
    fun minWindow(s: String, t: String): String {
        t.forEach { // 初始化窗口条件
            requirement[it] = requirement.getOrDefault(it, 0) + 1
        }

        // 确认当前的窗口是否满足条件
        fun windowMeet(): Boolean =
                requirement.all {
                    if (!window.contains(it.key)) false
                    else window.getValue(it.key) >= it.value
                }

        // 窗口左开右闭 [left, right) [start, start+minLen)
        // left 和 right 是窗口的左右指针
        var left = 0
        var right = 0
        // start 和 start+minLen 保存截至目前的最优解窗口的左右指针
        var start = 0
        var minLen = Int.MAX_VALUE
        while (right < s.length) {
            // 窗口右边界向右移动
            window[s[right]] = window.getOrDefault(s[right], 0) + 1
            right++

            while (windowMeet()) { // 当前窗口满足要求
                // 是否更新结果？（本题求的是最优解）
                if (right - left < minLen) {
                    start = left
                    minLen = right - left
                }

                // 窗口左边界向右移动以缩小范围，以便可以得到最优解
                window[s[left]] = window.getValue(s[left]) - 1
                if (window.getValue(s[left]) == 0) {
                    window.remove(s[left])
                }
                left++
            }
        }

        return if (minLen == Int.MAX_VALUE) "" else s.slice(start until start + minLen)
    }
}

fun main() {
    println(Solution().minWindow("ADOBECODEBANC", "ABC"))
}