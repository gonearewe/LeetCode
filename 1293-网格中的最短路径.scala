// 给你一个 m * n 的网格，其中每个单元格不是 0（空）就是 1（障碍物）。每一步，
// 您都可以在空白单元格中上、下、左、右移动。

// 如果您 最多 可以消除 k 个障碍物，请找出从左上角 (0, 0) 到右下角 (m-1, n-1) 的最短路径，
// 并返回通过该路径所需的步数。如果找不到这样的路径，则返回 -1。

// 示例 1：
// 输入： 
// grid = 
// [[0,0,0],
//  [1,1,0],
//  [0,0,0],
//  [0,1,1],
//  [0,0,0]], 
// k = 1
// 输出：6
// 解释：
// 不消除任何障碍的最短路径是 10。
// 消除位置 (3,2) 处的障碍后，最短路径是 6 。该路径是 (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
//  
// 示例 2：
// 输入：
// grid = 
// [[0,1,1],
//  [1,1,1],
//  [1,0,0]], 
// k = 1
// 输出：-1
// 解释：
// 我们至少需要消除两个障碍才能找到这样的路径。
//  
// 提示：
// grid.length == m
// grid[0].length == n
// 1 <= m, n <= 40
// 1 <= k <= m*n
// grid[i][j] == 0 or 1
// grid[0][0] == grid[m-1][n-1] == 0

// 过了一半用例空间就爆了...
object Solution {
    private var grid: Array[Array[Int]] = _
    private var k: Int = _
    private var visited: Array[Array[Boolean]] = _ // 记录访问的路径
  
    def shortestPath(grid: Array[Array[Int]], k: Int): Int = {
      this.grid = grid
      this.visited = new Array(grid.length)
      visited.indices foreach { // 生成 visited 矩阵
        visited(_) = new Array(grid(0).length)
      }
      this.k = k
      dfs((0, 0, 0))
    }
  
    // state: (i,j,visitedK), i,j 为矩阵的索引，visitedK 为初态到此经过的障碍数
    private def dfs(state: (Int, Int, Int)): Int = {
      // returns a list of posible next states of current state
      def nextStates(state: (Int, Int, Int)) =
        state match {
          case (i, j, visitedK) =>
            List((i - 1, j), (i + 1, j), (i, j - 1), (i, j + 1)) filter {
              case (i, j) if i < 0 || i >= grid.length || j < 0 || j >= grid(0).length || visited(i)(j) => false
              case _ => true
            } map {
              case (m, n) => (m, n, if (grid(m)(n) == 1) visitedK + 1 else visitedK)
            }
        }
  
      state match {
        case (_, _, visitedK) if visitedK > this.k => -1
        case (i, j, _) if i == grid.length && j == grid(0).length => 0 // 到达末态
        case (i, j, _) =>
          visited(i)(j) = true
          val res = nextStates(state).map(bfs).filterNot(_ == -1) match {
            case Nil => -1
            case li => li.min + 1
          }
          visited(i)(j) = false
          res
      }
    }
  }
  
  