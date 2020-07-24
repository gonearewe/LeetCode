// 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在
// 四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
// 找出所有满足条件且不重复的四元组。

// 注意：
// 答案中不可以包含重复的四元组。

// 示例：
// 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
// 满足要求的四元组集合为：
// [
//   [-1,  0, 0, 1],
//   [-2, -1, 1, 2],
//   [-2,  0, 0, 2]
// ]

import scala.collection.mutable.ListBuffer

object Solution {
  def fourSum(nums: Array[Int], target: Int): List[List[Int]] = {
    val numbers = nums.sorted
    val res = ListBuffer[List[Int]]() // ListBuffer for better performance
    for (i <- 1 until numbers.length - 2) { // 参考三数之和，只不过这里是两层循环
      for (j <- i + 1 until numbers.length - 1) {
        var (l, r) = (0, numbers.length - 1) // 左右指针分别初始化在最左端，最右端
        while (l < i && j < r) {
          val errand = numbers(l) + numbers(i) + numbers(j) + numbers(r) - target
          if (errand > 0) {
            r -= 1
          } else if (errand == 0) {
            val n = List(numbers(l), numbers(i), numbers(j), numbers(r))
            if (!res.contains(n))
              res += n
            // there may be more than one pair with the same middle values
            l += 1
            r -= 1
          } else {
            l += 1
          }
        }
      }
    }
    res.toList
  }
}
