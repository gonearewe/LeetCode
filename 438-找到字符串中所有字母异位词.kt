// 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
// 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

// 说明：
// 字母异位词指字母相同，但排列不同的字符串。
// 不考虑答案输出的顺序。

// 示例 1:
// 输入:
// s: "cbaebabacd" p: "abc"

// 输出:
// [0, 6]

// 解释:
// 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
// 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。

// 示例 2:
// 输入:
// s: "abab" p: "ab"

// 输出:
// [0, 1, 2]

// 解释:
// 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
// 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
// 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。

class Solution {
    // Key 分别是 26 个英文字母，Value 都是 0
    // 滑动窗口需要满足的字母频次
    private val requirement = hashMapOf(*('a'..'z').toList().zip(List(26) { 0 }).toTypedArray())

    // 记录当前滑动窗口的字母频次
    private val window = hashMapOf(*('a'..'z').toList().zip(List(26) { 0 }).toTypedArray())
    fun findAnagrams(s: String, p: String): List<Int> {
        if (s.length < p.length) return listOf() // 注意边界条件

        // 初始化两个记录表
        p.forEach {
            increase(requirement, it)
        }
        for (i in p.indices) {
            increase(window, s[i])
        }

        // 窗口左开右闭 [left, right)
        var left = 0
        var right = p.length
        val res = mutableListOf<Int>()

        // 窗口整体向右移动一步
        fun move() {
            increase(window, s[right])
            right++
            reduce(window, s[left])
            left++
        }

        while (right < s.length) {
            if (window == requirement) { // 当前窗口满足要求
                res.add(left)
            }

            move() // 移动窗口
        }
        if (window == requirement) { // 退出循环后需要检查一下最后一个窗口
            res.add(left)
        }

        return res
    }

    // 哈希表中字母出现次数加 1
    private fun <K> increase(counter: HashMap<K, Int>, key: K) {
        counter[key] = counter.getOrDefault(key, 0) + 1
    }

    // 哈希表中字母出现次数减 1
    private fun <K> reduce(counter: HashMap<K, Int>, key: K) {
        counter[key] = counter.getOrDefault(key, 0) - 1
    }
}

fun main() {
    Solution().findAnagrams("abab", "ab").also { println(it) }
}