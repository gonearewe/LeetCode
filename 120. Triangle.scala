// Given a triangle, find the minimum path sum from top to bottom. 
// Each step you may move to adjacent numbers on the row below.

// For example, given the following triangle
// [
//      [2],
//     [3,4],
//    [6,5,7],
//   [4,1,8,3]
// ]
// The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

// Note:
// Bonus point if you are able to do this using only O(n) extra space, 
// where n is the total number of rows in the triangle.
object Solution {
  def minimumTotal(triangle: List[List[Int]]): Int = {
    val n = triangle.size
    val dp = new Array[Int](n)
    dp.indices foreach {
      dp(_) = Int.MaxValue
    }
    dp(0) = triangle.head.head

    for {
      i <- 1 until n
      j <- i to 0 by -1
    } {
      dp(j) = (dp(j) min (if (j - 1 >= 0) dp(j - 1) else Int.MaxValue)) + triangle(i)(j)
    }

    dp.min
  }
}
