package main

// 有 N 个花园，按从 1 到 N 标记。在每个花园中，你打算种下四种花之一。
// paths[i] = [x, y] 描述了花园 x 到花园 y 的双向路径。
// 另外，没有花园有 3 条以上的路径可以进入或者离开。
// 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
// 以数组形式返回选择的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。
// 花的种类用  1, 2, 3, 4 表示。保证存在答案。
//
// 示例 1：
// 输入：N = 3, paths = [[1,2],[2,3],[3,1]]
// 输出：[1,2,3]
//
// 示例 2：
// 输入：N = 4, paths = [[1,2],[3,4]]
// 输出：[1,2,1,2]
//
// 示例 3：
// 输入：N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
// 输出：[1,2,3,4]
//
// 提示：
// 1 <= N <= 10000
// 0 <= paths.size <= 20000
// 不存在花园有 4 条或者更多路径可以进入或离开。
// 保证存在答案。

func gardenNoAdj(N int, paths [][]int) []int {
	var graph = make([][]int, N+1) // 大小 N+1，graph[0] 不用，保证索引对应关系
	var ans = make([]int, N)
	for _, relation := range paths { // 从输入构建图
		graph[relation[0]] = append(graph[relation[0]], relation[1])
		graph[relation[1]] = append(graph[relation[1]], relation[0])
	}

	// 广度优先搜索，遍历所有节点
	for i, vertex := range graph {
		if i == 0 { // 跳过不用的 graph[0]
			continue
		}

		if len(vertex) == 0 { // 此节点无边，随便染色
			ans[i-1] = 1
		}

		var flower [4]bool
		for _, c := range vertex { // 遍历邻接节点，查看染色情况
			if ans[c-1] != 0 {
				flower[ans[c-1]-1] = true // 标记邻接节点占用了这个颜色
			}

			for j, f := range flower {
				if !f { // 选择第一个没有被邻接节点占用的颜色给当前节点染色
					ans[i-1] = j + 1
					break
				}
				// 题目保证有解，循环结束时当前节点必染上了色，也不用回溯
			}
		}
	}

	return ans
}
