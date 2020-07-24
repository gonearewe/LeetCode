// 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
// 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

// 注意：答案中不可以包含重复的三元组。

// 示例：
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
// 满足要求的三元组集合为：
// [
//   [-1, 0, 1],
//   [-1, -1, 2]
// ]

import scala.collection.mutable.ListBuffer
// 双指针法，枚举中间的值，按结果正负决定左右两个指针是否移动
object Solution {
  def threeSum(nums: Array[Int]): List[List[Int]] = {
    val numbers = nums.sortWith(_ < _)
    val res = ListBuffer[List[Int]]() // ListBuffer for better performance
    for (i <- 1 until numbers.length - 1) {
      var (l, r) = (0, numbers.length - 1) // 左右指针分别初始化在最左端，最右端
      while (l < i && i < r) {
        val sum = numbers(l) + numbers(i) + numbers(r)
        if (sum > 0) {
          r -= 1
        } else if (sum == 0) {
          val n = List(numbers(l), numbers(i), numbers(r))
          if (!res.contains(n))
            res += n
          // there may be more than one pair with the same middle value
          l += 1
          r -= 1
        } else {
          l += 1
        }
      }
    }
    res.toList
  }
}

