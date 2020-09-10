// 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

// 你可以对一个单词进行如下三种操作：
// 插入一个字符
// 删除一个字符
// 替换一个字符
//  
// 示例 1：
// 输入：word1 = "horse", word2 = "ros"
// 输出：3
// 解释：
// horse -> rorse (将 'h' 替换为 'r')
// rorse -> rose (删除 'r')
// rose -> ros (删除 'e')

// 示例 2：
// 输入：word1 = "intention", word2 = "execution"
// 输出：5
// 解释：
// intention -> inention (删除 't')
// inention -> enention (将 'i' 替换为 'e')
// enention -> exention (将 'n' 替换为 'x')
// exention -> exection (将 'n' 替换为 'c')
// exection -> execution (插入 'u')
import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        IntStream.range(0, n + 1).forEach(i -> dp[0][i] = i);
        IntStream.range(0, m + 1).forEach(i -> dp[i][0] = i);

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int[] opts = new int[3];
                opts[0] = dp[i - 1][j] + 1;
                opts[1] = dp[i][j - 1] + 1;
                opts[2] = dp[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);
                dp[i][j] = Arrays.stream(opts).min().getAsInt();
            }
        }

        return dp[m][n];
    }
}
