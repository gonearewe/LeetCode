// 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。

// 示例 1：
// 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
// 输出：true

// 示例 2：
// 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
// 输出：false
object Solution {
  def isInterleave(s1: String, s2: String, s3: String): Boolean = {
    val (m1, m2, n) = (s1.length, s2.length, s3.length)
    if (m1 + m2 != n) return false

    val dp = new Array[Boolean](m1 + 1) // 压缩数组

    for {
      i <- 0 to n
      j <- m1 to 0 by -1 // 逆序
      if i >= j
    } {
      dp(j) = if (i == 0) true
      else j - 1 >= 0 && dp(j - 1) && s1(j - 1) == s3(i - 1) ||
        dp(j) && i - j - 1 < m2 && s2(i - j - 1) == s3(i - 1)
    }

    dp exists identity
  }
}
