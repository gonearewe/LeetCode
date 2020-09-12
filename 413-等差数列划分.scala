// 如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。

// 例如，以下数列为等差数列:

// 1, 3, 5, 7, 9
// 7, 7, 7, 7
// 3, -1, -5, -9

// 以下数列不是等差数列。

// 1, 1, 2, 5, 7
//  
// 数组 A 包含 N 个数，且索引从0开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q 是整数且满足 0<=P<Q<N 。
// 如果满足以下条件，则称子数组(P, Q)为等差数组：
// 元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且 P + 1 < Q 。
// 函数要返回数组 A 中所有为等差数组的子数组个数。

// 示例:
// A = [1, 2, 3, 4]
// 返回: 3, A 中有三个子等差数组: [1, 2, 3], [2, 3, 4] 以及自身 [1, 2, 3, 4]。

// dp[i] 表示的是以 A[i] 结尾的等差数列个数，可以从一维 DP 压缩到常数
object Solution { 
  def numberOfArithmeticSlices(A: Array[Int]): Int = {
    // 判断以 end 索引的数字结尾的三元组是否是等差数列
    def ok(end: Int) = A(`end`) - A(`end` - 1) == A(`end` - 1) - A(`end` - 2)

    if (A.length < 3) 0
    else {
      val last = if (ok(2)) 1 else 0
      (3 until A.length).foldLeft((last, last)) { (ctx, i) =>
        val (last, cnt) = ctx
        if (ok(i)) (last + 1, cnt + last + 1) else (0, cnt)
      }._2
    }
  }
}
