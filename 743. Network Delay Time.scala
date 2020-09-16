// There are N network nodes, labelled 1 to N.

// Given times, a list of travel times as directed edges times[i] = (u, v, w), 
// where u is the source node, v is the target node, and w is the time it takes for 
// a signal to travel from source to target.

// Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

// Example 1:
// Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
// Output: 2

// Note:
// N will be in the range [1, 100].
// K will be in the range [1, N].
// The length of times will be in the range [1, 6000].
// All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
import scala.collection.mutable
import scala.util.control.Breaks.{break, breakable}

object Solution { // Dijkstra
  def networkDelayTime(times: Array[Array[Int]], N: Int, K: Int): Int = {
    val visited = new Array[Boolean](N + 1)
    visited(0) = true

    val vertices = new Array[mutable.Set[(Int, Int)]](N + 1)
    vertices.indices foreach {
      vertices(_) = mutable.Set()
    }
    times foreach { case Array(src, dst, delay) =>
      vertices(src) add(dst, delay)
    }

    implicit val ord = Ordering.by[(Int, Int), Int](_._2).reverse
    val queue = mutable.PriorityQueue[(Int, Int)]()

    var maxDelay = 0
    queue.enqueue((K, 0))
    while (queue.nonEmpty) {
      breakable {
        val (cur, delaySum) = queue.dequeue()
        if (visited(cur)) break
        visited(cur) = true
        maxDelay = maxDelay max delaySum

        vertices(cur) filterNot { case (vertex, _) => visited(vertex) } foreach {
          case (vertex, delay) => queue.enqueue((vertex, delaySum + delay))
        }
      }
    }

    if (visited forall identity) maxDelay else -1
  }
}
