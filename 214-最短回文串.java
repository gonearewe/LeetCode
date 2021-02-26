// 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
//
// 示例 1:
// 输入: "aacecaaa"
// 输出: "aaacecaaa"
//
// 示例 2:
// 输入: "abcd"
// 输出: "dcbabcd"

// 马拉车算法(Manacher's Algorithm), 参考 https://zhuanlan.zhihu.com/p/70532099
//
// 用 RK 算法和 KMP 算法也可以做，
// 参见 https://leetcode-cn.com/problems/shortest-palindrome/solution/zui-duan-hui-wen-chuan-by-leetcode-solution/
class Solution {
    public String shortestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }

        String str = preprocess(s);
        int[] P = new int[str.length()];
        int C = 0, R = 0;
        for (int i = 1; i < P.length - 1; i++) {
            int iMirror = 2 * C - i;
            P[i] = R > i ? Math.min(P[iMirror], R - i) : 0;

            while (str.charAt(i + P[i] + 1) == str.charAt(i - P[i] - 1)) { // 尝试中心扩展
                P[i]++;
            }

            if (i + P[i] > R) { // 更新 R，C
                C = i;
                R = i + P[i];
            }
        }

        int rightBoundry = 0; // 找到最长的回文串前缀的最后一个字符的索引
        for (int i = 1; i < P.length - 1; i++) {
            int start = (i - P[i]) / 2;
            if (start == 0) {
                rightBoundry = Math.max(rightBoundry, start + P[i]);
            }
        }

        var res = new StringBuilder(s.length() * 2);
        res.append(s.substring(rightBoundry + 1));
        res.reverse(); // 回文串前缀后的部分经过逆序就是新字符串要添加的的前缀
        res.append(s); // 连接原来的字符串
        return res.toString();
    }

    private String preprocess(String s) { // 插入占位符，使所有回文子串的长度都变成奇数
        var res = new StringBuilder(s.length() * 2 + 1);
        res.append("^");
        for (var c : s.toCharArray()) {
            res.append(c);
            res.append("#");
        }
        res.deleteCharAt(res.length() - 1);
        res.append("$");
        return res.toString();
    }
}
