// 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

// 示例 1：
// 输入："abc"
// 输出：3
// 解释：三个回文子串: "a", "b", "c"

// 示例 2：
// 输入："aaa"
// 输出：6
// 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
//  
// 提示：
// 输入的字符串长度不会超过 1000 。

// 马拉车算法(Manacher's Algorithm), 参考 https://zhuanlan.zhihu.com/p/70532099
class Solution {
    public int countSubstrings(String s) {
        String str = preprocess(s);
        int[] P = new int[str.length()];
        int C = 0, R = 0;
        int res = 0;
        for (int i = 1; i < P.length - 1; i++) {
            int iMirror = 2 * C - i;
            P[i] = R <= i ? 0 : Math.min(P[iMirror], R - i);

            while (str.charAt(i + P[i] + 1) == str.charAt(i - P[i] - 1)) {
                P[i]++;
            }

            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }

            res += (P[i] + 1) / 2;
        }

        return res;
    }

    private String preprocess(String s) {
        var str = new StringBuilder(s.length() * 2 + 2);
        str.append("^");
        for (var c : s.toCharArray()) {
            str.append("#");
            str.append(c);
        }
        str.append("#$");
        return str.toString();
    }
}
