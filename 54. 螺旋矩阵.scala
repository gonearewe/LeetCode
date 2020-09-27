// 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

// 示例 1:
// 输入:
// [
//  [ 1, 2, 3 ],
//  [ 4, 5, 6 ],
//  [ 7, 8, 9 ]
// ]
// 输出: [1,2,3,6,9,8,7,4,5]

// 示例 2:
// 输入:
// [
//   [1, 2, 3, 4],
//   [5, 6, 7, 8],
//   [9,10,11,12]
// ]
// 输出: [1,2,3,4,8,12,11,10,9,5,6,7]

import scala.collection.mutable.ListBuffer

object Solution {
  def spiralOrder(matrix: Array[Array[Int]]): List[Int] = {
    if (matrix.length == 0) return Nil
    val (m, n) = (matrix.length, matrix(0).length)
    val res = ListBuffer[Int]()
    0 to ((n - 1) / 2 min (m - 1) / 2 min m min n) foreach { i =>
      val w = n - 2 * i
      val h = m - 2 * i
      if (h == 1) i until i + w foreach (res += matrix(i)(_))
      else if (w == 1) i until i + h foreach (res += matrix(_)(i))
      else {
        i until i + w foreach (res += matrix(i)(_))
        i + 1 until i + h - 1 foreach (res += matrix(_)(i + w - 1))
        i + w - 1 until i - 1 by -1 foreach (res += matrix(i + h - 1)(_))
        i + h - 2 until i by -1 foreach (res += matrix(_)(i))
      }
    }
    res.toList
  }
}
