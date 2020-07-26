// 实现 strStr() 函数。
// 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中
// 找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

// 示例 1:
// 输入: haystack = "hello", needle = "ll"
// 输出: 2

// 示例 2:
// 输入: haystack = "aaaaa", needle = "bba"
// 输出: -1

// 说明:
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。
// 这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。

class Solution { // Rabin-Karp 算法，参考《算法（第四版）》P.505
    public int strStr(String haystack, String needle) {
        if (needle.length() > haystack.length()) {
            return -1;
        }

        // 向大数 m 取模防溢出
        long nhash = 0, hhash = 0, m = (long) Math.pow(2, 31);
        for (int i = 0; i < needle.length(); i++) {
            nhash = (nhash * 26 + hashcode(needle, i)) % m;
            hhash = (hhash * 26 + hashcode(haystack, i)) % m;
        }
        if (nhash == hhash) {
            return 0;
        }

        // 计算首位的权值
        long n = 1;
        for (int i = 0; i < needle.length() - 1; i++) {
            n = n * 26 % m;
        }

        // 滚动哈希
        for (int i = 0; i < haystack.length() - needle.length(); i++) {
            hhash = ((hhash + m - hashcode(haystack, i) * n) * 26 + hashcode(haystack, i + needle.length())) % m;
            if (hhash == nhash) {
                return i + 1;
            }
        }

        return -1;
    }

    // 计算字符 hash
    private static int hashcode(String s, int index) { 
        return (int) s.charAt(index) - (int) 'a';
    }
}
