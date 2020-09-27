// 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

// 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

// "123"
// "132"
// "213"
// "231"
// "312"
// "321"

// 给定 n 和 k，返回第 k 个排列。

// 说明：
// 给定 n 的范围是 [1, 9]。
// 给定 k 的范围是[1,  n!]。

// 示例 1:
// 输入: n = 3, k = 3
// 输出: "213"

// 示例 2:
// 输入: n = 4, k = 9
// 输出: "2314"
import scala.collection.mutable

object Solution {
  private val fac = (1 to 9).scanLeft(0 -> 1) { (last, i) => i -> last._2 * i }.toMap

  def getPermutation(n: Int, k: Int): String = {
    val res = new StringBuilder(n)
    val nums = mutable.ListBuffer.range(1, n + 1)
    var m = k - 1
    n until 0 by -1 foreach { i =>
      res append (nums remove m / fac(i - 1))
      m %= fac(i - 1)
    }
    res.toString
  }
}
