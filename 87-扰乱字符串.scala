// 给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。

// 下图是字符串 s1 = "great" 的一种可能的表示形式。

//     great
//    /    \
//   gr    eat
//  / \    /  \
// g   r  e   at
//            / \
//           a   t
// 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。

// 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。

//     rgeat
//    /    \
//   rg    eat
//  / \    /  \
// r   g  e   at
//            / \
//           a   t
// 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。

// 同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。

//     rgtae
//    /    \
//   rg    tae
//  / \    /  \
// r   g  ta  e
//        / \
//       t   a
// 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。

// 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。

// 示例 1:
// 输入: s1 = "great", s2 = "rgeat"
// 输出: true

// 示例 2:
// 输入: s1 = "abcde", s2 = "caebd"
// 输出: false

object Solution {
  def isScramble(s1: String, s2: String): Boolean = {
    val L = s1.length
    val dp = dim3[Boolean](L, L, L + 1)
    for {
      i <- dp.indices
      j <- dp(i).indices
    } {
      dp(i)(j)(1) = s1(i) == s2(j)
    }

    for {
      len <- 2 to L
      i <- 0 to L - len
      j <- 0 to L - len
    } {
      dp(i)(j)(len) = (1 until len) exists { w =>
        dp(i)(j)(w) && dp(i + w)(j + w)(len - w) || dp(i)(j + len - w)(w) && dp(i + w)(j)(len - w)
      }
    }

    dp(0)(0)(L)
  }


  private def dim3[T: Manifest](i: Int, j: Int, k: Int) = {
    val array = new Array[Array[Array[T]]](i)
    array.indices foreach { i =>
      array(i) = new Array[Array[T]](j)
      array(i).indices foreach { j =>
        array(i)(j) = new Array[T](k)
      }
    }
    array
  }
}
