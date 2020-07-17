import java.util.*
import kotlin.collections.ArrayList

// 对于一个具有树特征的无向图，我们可选择任何一个节点作为根。图因此可以成为树，
// 在所有可能的树中，具有最小高度的树被称为最小高度树。给出这样的一个图，
// 写出一个函数找到所有的最小高度树并返回他们的根节点。

// 格式
// 该图包含 n 个节点，标记为 0 到 n - 1。给定数字 n 和一个无向边 edges 列表
// （每一个边都是一对标签）。

// 你可以假设没有重复的边会出现在 edges 中。由于所有的边都是无向边， [0, 1]和 [1, 0] 是相同的，
// 因此不会同时出现在 edges 里。

// 示例 1:
// 输入: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

//         0
//         |
//         1
//        / \
//       2   3 

// 输出: [1]

// 示例 2
// 输入: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

//      0  1  2
//       \ | /
//         3
//         |
//         4
//         |
//         5 

// 输出: [3, 4]

// 说明:
// 根据树的定义，树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
// 树的高度是指根节点和叶子节点之间最长向下路径上边的数量。
class Solution {
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        val graph = Array(n) { ArrayList<Int>() }
        val degree = IntArray(n)
        edges.forEach {
            degree[it[0]]++
            degree[it[1]]++
            graph[it[0]].add(it[1])
            graph[it[1]].add(it[0])
        }

        val queue = LinkedList<Int>()
        degree.forEachIndexed {
            index, i ->
            if (i == 1) {
                queue.add(index)
            }
        }

        val res = ArrayList<Int>()
        while (!queue.isEmpty()) {
            val len = queue.size
            for (i in 0 until len) {
                val index = queue.poll()
                if (graph[index].isEmpty()) {
                    res.add(index)
                }

                graph[index].forEach {
                    queue.add(it)
                    graph[it].remove(index)
                }
            }
        }

        return res
    }


}
fun main(){
    val p =Array(5){IntArray(2) }
    p[0][0]=0
    p[0][1]=3
    p[1][0]=1
    p[1][1]=3
    p[2][0]=2
    p[2][1]=3
    p[3][0]=4
    p[3][1]=3
    p[4][0]=5
    p[4][1]=4

    Solution(). findMinHeightTrees(6,p)
}
