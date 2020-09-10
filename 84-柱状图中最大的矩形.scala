// 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

// 求在该柱状图中，能够勾勒出来的矩形的最大面积。

// 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。

// 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。

// 示例:
// 输入: [2,1,5,6,2,3]
// 输出: 10
import scala.collection.mutable

object Solution { // 单调栈（非严格单调递增）
  def largestRectangleArea(heights: Array[Int]): Int = {
    var res = 0
    val hs: Array[Int] = 0 +: heights :+ 0
    val stack = mutable.Stack[Int]()
    for (i <- hs.indices) {
      while (stack.nonEmpty && hs(i) < hs(stack.top)) {
        val cur = stack.pop()
        val left = stack.top + 1
        res = res max (i - left) * hs(cur)
      }
      stack push i
    }
    res
  }
}
