// 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
// 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

// 示例：
// 输入：nums = [-1,2,1,-4], target = 1
// 输出：2
// 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。

// 提示：
// 3 <= nums.length <= 10^3
// -10^3 <= nums[i] <= 10^3
// -10^4 <= target <= 10^4

import scala.collection.mutable.ListBuffer

object Solution {
  def threeSumClosest(nums: Array[Int], target: Int): Int = {
    val numbers = nums.sortWith(_ < _)
    var res = Int.MaxValue / 2
    for (i <- 1 until numbers.length - 1) {
      var (l, r) = (0, numbers.length - 1)
      while (l < i && i < r) {
        val errand = numbers(l) + numbers(i) + numbers(r) - target
        if (errand > 0) {
          r -= 1
        } else if (errand == 0) {
          return target
        } else {
          l += 1
        }
        if (math.abs(errand) < math.abs(res - target)) {
          res = errand + target
        }
      }
    }
    res
  }
}
