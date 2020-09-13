// 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。

// 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 
// 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。

// 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。

// 示例 1：
// 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
// 输出：20
// 解释：
// 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
// 注意到任意两个点之间只有唯一一条路径互相到达。

// 示例 2：
// 输入：points = [[3,12],[-2,5],[-4,1]]
// 输出：18

// 示例 3：
// 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
// 输出：4

// 示例 4：
// 输入：points = [[-1000000,-1000000],[1000000,1000000]]
// 输出：4000000

// 示例 5：
// 输入：points = [[0,0]]
// 输出：0

// 提示：
// 1 <= points.length <= 1000
// -106 <= xi, yi <= 106
// 所有点 (xi, yi) 两两不同。
import scala.collection.mutable

object Solution { // Prim MST
  def minCostConnectPoints(points: Array[Array[Int]]): Int = {
    type Pos = (Int, Int)
    type Distance = Int
    type Edge = (Pos, Distance)
    implicit val ord: Ordering[Edge] = Ordering.by[Edge, Int](_._2).reverse

    val postions = points.map(p => (p(0), p(1))).toSet
    val visited = mutable.Set[Pos]((points(0)(0), points(0)(1)))

    def edgesOf(vertex: (Int, Int)) = vertex match {
      case (x, y) =>
        (postions diff visited map { pos =>
          (pos, Math.abs(x - pos._1) + Math.abs(y - pos._2))
        }).toArray
    }

    val queue = mutable.PriorityQueue[Edge](edgesOf((points(0)(0), points(0)(1))): _*)
    var cost = 0
    while (visited.size < points.length) {
      var (pos, distance) = queue.dequeue()
      while (visited(pos)) {
        (pos, distance) = queue.dequeue()
      }
      visited += pos
      cost += distance
      queue.enqueue(edgesOf(pos): _*)
    }

    cost
  }
}
