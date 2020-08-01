// 编写一个程序，通过已填充的空格来解决数独问题。
// 一个数独的解法需遵循如下规则：

// 数字 1-9 在每一行只能出现一次。
// 数字 1-9 在每一列只能出现一次。
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
// 空白格用 '.' 表示。

// 一个数独。

// [[".",".","9","7","4","8",".",".","."],
//  ["7",".",".",".",".",".",".",".","."],
//  [".","2",".","1",".","9",".",".","."],
//  [".",".","7",".",".",".","2","4","."],
//  [".","6","4",".","1",".","5","9","."],
//  [".","9","8",".",".",".","3",".","."],
//  [".",".",".","8",".","3",".","2","."],
//  [".",".",".",".",".",".",".",".","6"],
//  [".",".",".","2","7","5","9",".","."]]

// 答案。

// [["5","1","9","7","4","8","6","3","2"],
//  ["7","8","3","6","5","2","4","1","9"],
//  ["4","2","6","1","3","9","8","7","5"],
//  ["3","5","7","9","8","6","2","4","1"],
//  ["2","6","4","3","1","7","5","9","8"],
//  ["1","9","8","5","2","4","3","6","7"],
//  ["9","7","5","8","6","3","1","2","4"],
//  ["8","3","2","4","9","1","7","5","6"],
//  ["6","4","1","2","7","5","9","8","3"]]

// Note:
// 给定的数独序列只包含数字 1-9 和字符 '.' 。
// 你可以假设给定的数独只有唯一解。
// 给定数独永远是 9x9 形式的。

import scala.collection.mutable

object Solution {
  private var board = new Array[Array[Char]](0)
  private val constraints = new Array[Array[mutable.Map[Char, Int]]](9)

  def solveSudoku(board: Array[Array[Char]]): Unit = {
    (0 until 9).foreach(i => {
      constraints(i) = new Array[mutable.Map[Char, Int]](9)
      (0 until 9).foreach(
        constraints(i)(_) = new mutable.HashMap[Char, Int]()
      )
    })

    this.board = board
    initConstraints()
    solve()
  }

  def solve(): Boolean = {
    val (i, j) = findUnfilled()
    if ((i, j) == (-1, -1)) {
      return true
    }

    for (c <- '1' to '9') {
      if (constraints(i)(j).getOrElse(c, 0) == 0) {
        updateConstraints(i, j, m => m.put(c, m.getOrElse(c, 0) + 1))
        board(i)(j) = c
        if (solve())
          return true
        else {
          board(i)(j) = '.'
          updateConstraints(i, j, m => m.put(c, m(c) - 1))
        }
      }
    }

    false
  }

  def findUnfilled(): (Int, Int) = {
    for (i <- 0 until 9) {
      for (j <- 0 until 9) {
        if (board(i)(j) == '.') {
          return (i, j)
        }
      }
    }
    (-1, -1)
  }

  def initConstraints(): Unit = {
    for (i <- 0 until 9) {
      for (j <- 0 until 9) {
        val c = board(i)(j)
        if (c != '.') {
          updateConstraints(i, j, m => m.put(c, m.getOrElse(c, 0) + 1))
        }
      }
    }
  }

  def updateConstraints[U](ci: Int, cj: Int, action: mutable.Map[Char, Int] => U): Unit = {
    for (i <- 0 until 9) {
      for (j <- 0 until 9) {
        if (ci == i || cj == j || (ci / 3 == i / 3 && cj / 3 == j / 3)) {
          action(constraints(i)(j))
        }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val input =
      """
        |["5","3",".",".","7",".",".",".","."],
        |["6",".",".","1","9","5",".",".","."],
        |[".","9","8",".",".",".",".","6","."],
        |["8",".",".",".","6",".",".",".","3"],
        |["4",".",".","8",".","3",".",".","1"],
        |["7",".",".",".","2",".",".",".","6"],
        |[".","6",".",".",".",".","2","8","."],
        |[".",".",".","4","1","9",".",".","5"],
        |[".",".",".",".","8",".",".","7","9"
        |""".stripMargin

    solveSudoku(input.split("],").map(s => {
      s.filter(c => ('1' to '9').contains(c) || c == '.').toArray
    }))
  }
}
