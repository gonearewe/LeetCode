// 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。

// 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是
// 找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。 如果没有这样的路线，则输出 -1。

// 示例 1：
// 输入: 
// n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
// src = 0, dst = 2, k = 1
// 输出: 200
// 解释: 
// 城市航班图如下

// 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。

// 示例 2：
// 输入: 
// n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
// src = 0, dst = 2, k = 0
// 输出: 500
// 解释: 
// 城市航班图如下

// 从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。

// 提示：
// n 范围是 [1, 100]，城市标签从 0 到 n - 1.
// 航班数量范围是 [0, n * (n - 1) / 2].
// 每个航班的格式 (src, dst, price).
// 每个航班的价格范围是 [1, 10000].
// k 范围是 [0, n - 1].
// 航班没有重复，且不存在环路
import scala.collection.mutable
import scala.util.control.Breaks.{break, breakable}

object Solution {
  def findCheapestPrice(n: Int, flights: Array[Array[Int]], src: Int, dst: Int, K: Int): Int = {
    type Vertex = (Int, Int) // (vertexID, price)
    type Elem = (Int, Int, Int) // elem of priority queue
    implicit val ord: Ordering[Elem] = Ordering.by[Elem, Int](_._2).reverse
    //    val visited=new Array[Boolean](n) 
    val vertices = new Array[mutable.Set[Vertex]](n)
    vertices.indices foreach {
      vertices(_) = mutable.Set()
    }
    flights foreach { case Array(src, dst, price) =>
      vertices(src) add(dst, price)
    }

    val queue = mutable.PriorityQueue((src, 0, 0))
    while (queue.nonEmpty) {
      breakable {
        val (vertex, priceSum, curK) = queue.dequeue()
        //        if(visited(vertex)) break
        if (vertex == dst) return priceSum
        if (curK > K) break

        //        visited(vertex)=true
        vertices(vertex) foreach { case (neighbor, price) =>
          println(s"$vertex->$neighbor $priceSum + $price;")

          queue.enqueue((neighbor, priceSum + price, curK + 1))
        }
      }
    }

    -1
  }
}
// don't use `visited` to simplify, consider following case:
// INPUT 
// 5
// [[0,1,5],[1,2,5],[0,3,2],[3,1,2],[1,4,1],[4,2,1]]
// 0
// 2
// 2
// EXPECTED
// 7 (0->1->4->2)