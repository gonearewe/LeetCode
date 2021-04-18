import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks.{break, breakable}

object Solution {
  def getOrder(tasks: Array[Array[Int]]): Array[Int] = {
    val newTasks = tasks.zipWithIndex.sortBy(pair => pair._1(0))
    implicit val ordering: Ordering[(Int, Int)] = Ordering.by(pair => (-pair._2, -pair._1, 0))
    val queue = new mutable.PriorityQueue[(Int, Int)]()
    val ans = ArrayBuffer[Int]()
    var i = 0
    var time = 0
    println(newTasks.mkString)
    while (i < newTasks.length || queue.nonEmpty) {
      breakable {
        if (i < newTasks.length && queue.isEmpty && time < newTasks(i)._1(0)) {
          time = newTasks(i)._1(0)
          break
        }
        while (i < newTasks.length && newTasks(i)._1(0) <= time) {
          queue.enqueue((newTasks(i)._2, newTasks(i)._1(1)))
          i += 1
        }
        val pair = queue.dequeue()
        ans.append(pair._1)
        time += pair._2
      }
    }
    ans.toArray
  }
}

