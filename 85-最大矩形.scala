// 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

// 示例:
// 输入:
// [
//   ["1","0","1","0","0"],
//   ["1","0","1","1","1"],
//   ["1","1","1","1","1"],
//   ["1","0","0","1","0"]
// ]
// 输出: 6

import scala.collection.mutable

object Solution {
  def maximalRectangle(matrix: Array[Array[Char]]): Int = {
    if (matrix.isEmpty) {
      return 0
    }

    val heights = new Array[Array[Int]](matrix.length + 2)
    heights.indices foreach {
      heights(_) = new Array[Int](matrix(0).length)
    }

    for (i <- matrix.indices) {
      var h = 0
      for (j <- matrix(i).indices) {
        if (matrix(i)(j) == '1') {
          h += 1
        } else {
          h = 0
        }
        heights(i + 1)(j) = h
      }
    }

    //    heights foreach { row => row foreach print; println() }

    var res = 0
    for (j <- heights(0).indices) {
      val stack = mutable.Stack[Int]()
      for (i <- heights.indices) {
        while (stack.nonEmpty && heights(i)(j) < heights(stack.top)(j)) {
          val cur = stack.pop()
          val left = stack.top + 1
          res = res max (i - left) * heights(cur)(j)
        }
        stack push i
      }
    }

    res
  }
}
