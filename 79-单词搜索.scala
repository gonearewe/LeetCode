// 给定一个二维网格和一个单词，找出该单词是否存在于网格中。

// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
// 同一个单元格内的字母不允许被重复使用。

// 示例:
// board =
// [
//   ['A','B','C','E'],
//   ['S','F','C','S'],
//   ['A','D','E','E']
// ]

// 给定 word = "ABCCED", 返回 true
// 给定 word = "SEE", 返回 true
// 给定 word = "ABCB", 返回 false
//  
// 提示：
// board 和 word 中只包含大写和小写英文字母。
// 1 <= board.length <= 200
// 1 <= board[i].length <= 200
// 1 <= word.length <= 10^3

import scala.collection.mutable

object Solution {
  private var word: String = _
  private var board: Array[Array[Char]] = _

  def exist(board: Array[Array[Char]], word: String): Boolean = {
    this.board = board
    this.word = word

    val starts = for {
      i <- board.indices
      j <- board(0).indices
      if board(i)(j) == word(0)
    } yield (i, j)

    starts exists {
      case (i, j) => dfs(i, j, 1, Set((i, j)))
    }
  }

  private def dfs(i: Int, j: Int, cur: Int, visited: Set[(Int, Int)]): Boolean =
    if (cur == word.length) true
    else {
      Set((i - 1, j), (i + 1, j), (i, j - 1), (i, j + 1)) diff visited filter {
        case (x, y) => board.indices.contains(x) && board(0).indices.contains(y) && board(x)(y) == word(cur)
      } exists {
        case (x, y) => dfs(x, y, cur + 1, visited.+((x, y)))
      }
    }
}

