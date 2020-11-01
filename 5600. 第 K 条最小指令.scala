// Bob 站在单元格 (0, 0) ，想要前往目的地 destination ：(row, column) 。他只能向 右 或向 下 走。你可以为 Bob 提供导航 指令 来帮助他到达目的地 destination 。

// 指令 用字符串表示，其中每个字符：

// 'H' ，意味着水平向右移动
// 'V' ，意味着竖直向下移动
// 能够为 Bob 导航到目的地 destination 的指令可以有多种，例如，如果目的地 destination 是 (2, 3)，"HHHVV" 和 "HVHVH" 都是有效 指令 。

// 然而，Bob 很挑剔。因为他的幸运数字是 k，他想要遵循 按字典序排列后的第 k 条最小指令 的导航前往目的地 destination 。k  的编号 从 1 开始 。

// 给你一个整数数组 destination 和一个整数 k ，请你返回可以为 Bob 提供
// 前往目的地 destination 导航的 按字典序排列后的第 k 条最小指令 。

// 示例 1：
// 输入：destination = [2,3], k = 1
// 输出："HHHVV"
// 解释：能前往 (2, 3) 的所有导航指令 按字典序排列后 如下所示：
// ["HHHVV", "HHVHV", "HHVVH", "HVHHV", "HVHVH", "HVVHH", "VHHHV", "VHHVH", "VHVHH", "VVHHH"].

// 示例 2：
// 输入：destination = [2,3], k = 2
// 输出："HHVHV"

// 示例 3：
// 输入：destination = [2,3], k = 3
// 输出："HHVVH"

// 提示：
// destination.length == 2
// 1 <= row, column <= 15
// 1 <= k <= nCr(row + column, row)，其中 nCr(a, b) 表示组合数，即从 a 个物品中选 b 个物品的不同方案数。
object Solution {
  def kthSmallestPath(destination: Array[Int], k: Int): String = {
    // hCnt + vCnt 个位置中放 hCnt 个 "H" 与 vCnt 个 "V"
    val (hCnt, vCnt) = (destination(1), destination(0))
    // combinations(n)(k) 表示 nCr(n,k)
    val combinations = Array.fill(hCnt + vCnt)(new Array[Int](hCnt + vCnt))
    combinations.foreach(row => row(0) = 1)
    1.until(combinations.length).foreach { i =>
      1.to(i).foreach { j => // 组合数递推公式
        combinations(i)(j) = combinations(i - 1)(j - 1) + combinations(i - 1)(j)
      }
    }

    // 当前位置还剩 h 个 "H" 与 v 个 "V" 可用，当前位置需要选择所有可选项中的第 k 个
    def build(h: Int, v: Int, k: Int): String = {
      if (h + v == 0) ""
      else if (h == 0) "V" + build(0, v - 1, 0) // 无 "H" 可用，只能用 "V"
      else {
        val n = combinations(h + v - 1)(h - 1) // 假如当前位置选 "H"，后面位置的可选项数目
        if (k >= n) "V" + build(h, v - 1, k - n)
        else "H" + build(h - 1, v, k)
      }
    }

    build(hCnt, vCnt, k - 1) // 题目的 k 从 1 开始数
  }
}
