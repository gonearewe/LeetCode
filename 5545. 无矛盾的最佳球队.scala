// 假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。
// 球队的得分是球队中所有球员的分数 总和 。

// 然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。
// 如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。

// 给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。
// 请你返回 所有可能的无矛盾球队中得分最高那支的分数 。

// 示例 1：
// 输入：scores = [1,3,5,10,15], ages = [1,2,3,4,5]
// 输出：34
// 解释：你可以选中所有球员。

// 示例 2：
// 输入：scores = [4,5,6,5], ages = [2,1,2,1]
// 输出：16
// 解释：最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。

// 示例 3：
// 输入：scores = [1,2,3,5], ages = [8,9,10,1]
// 输出：6
// 解释：最佳的选择是前 3 名球员。
 
// 提示：
// 1 <= scores.length, ages.length <= 1000
// scores.length == ages.length
// 1 <= scores[i] <= 106
// 1 <= ages[i] <= 1000

object Solution {
  // 一维动态规划求子序列
  def bestTeamScore(scores: Array[Int], ages: Array[Int]): Int = {
    val people = ages.zip(scores).sorted // 按年龄升序排列
    // maxScores(i) 表示年龄最大的球员是球员 i 的总分最高的球队中所有人中的最高分
    val maxScores = new Array[Int](scores.length)
    // sums(i) 表示年龄最大的球员是球员 i 的总分最高的球队的总分
    val sums = new Array[Int](scores.length)
    people.indices.foreach { i =>
      val score = people(i)._2 // 球员 i 的分数
      sums(i) = (0 until i).view.filter(maxScores(_) <= score) match {
        case x if x.isEmpty => // 球员 i 是球队第一个球员
          maxScores(i) = score
          score
        case x =>
          val j = x.maxBy(sums(_) + score) // 寻找球员 i 可以加入的球队中总分最高的
          maxScores(i) = maxScores(j) max score // 更新球队最高分
          sums(j) + score // 更新球队总分
      }
    }
    sums.max
  }
}

