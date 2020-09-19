// 给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。

// 一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
// （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）

// 题目数据保证答案符合 32 位带符号整数范围。

// 示例 1：
// 输入：S = "rabbbit", T = "rabbit"
// 输出：3
// 解释：
// 如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
// (上箭头符号 ^ 表示选取的字母)

// rabbbit
// ^^^^ ^^
// rabbbit
// ^^ ^^^^
// rabbbit
// ^^^ ^^^

// 示例 2：
// 输入：S = "babgbag", T = "bag"
// 输出：5
// 解释：
// 如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。 
// (上箭头符号 ^ 表示选取的字母)

// babgbag
// ^^ ^
// babgbag
// ^^    ^
// babgbag
// ^    ^^
// babgbag
//   ^  ^^
// babgbag
//     ^^^
object Solution {
  def numDistinct(s: String, t: String): Int = {
    val (m, n) = (s.length, t.length)
    val dp = new Array[Int](n + 1) // 压缩数组
    dp(0) = 1

    for {
      i <- 0 to m
      j <- n to 1 by -1
      if i >= j
    } {
      dp(j) += (if (j - 1 >= 0 && s(i - 1) == t(j - 1)) dp(j - 1) else 0)
    }

    dp(n)
  }
}
