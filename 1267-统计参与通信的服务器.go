package LeetCode

// 这里有一幅服务器分布图，服务器的位置标识在 m * n 的整数矩阵网格 grid 中，1 表示单元格上有服务器，0 表示没有。
// 如果两台服务器位于同一行或者同一列，我们就认为它们之间可以进行通信。
// 请你统计并返回能够与至少一台其他服务器进行通信的服务器的数量。
//
// 示例 1：
// 输入：grid = [[1,0],[0,1]]
// 输出：0
// 解释：没有一台服务器能与其他服务器进行通信。
//
// 示例 2：
// 输入：grid = [[1,0],[1,1]]
// 输出：3
// 解释：所有这些服务器都至少可以与一台别的服务器进行通信。
//
// 示例 3：
// 输入：grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
// 输出：4
// 解释：第一行的两台服务器互相通信，第三列的两台服务器互相通信，但右下角的服务器无法与其他服务器通信。
//
// 提示：
// m == grid.length
// n == grid[i].length
// 1 <= m <= 250
// 1 <= n <= 250
// grid[i][j] == 0 or 1

type server struct {
	x, y int
}

func countServers(grid [][]int) int {
	var rows = make(map[int]int)
	var columns = make(map[int]int)
	var s = make([]server, 0)
	var i, j int
	for i = 0; i < len(grid); i++ {
		for j = 0; j < len(grid[0]); j++ { // 遍历网格
			if grid[i][j] == 1 { // 此处有服务器
				rows[i]++
				columns[j]++
				s = append(s, server{x: i, y: j}) // 记下服务器
			}
		}
	}

	var ans int
	for _, c := range s { // 遍历所有服务器，如果所在行或列服务器数量大于 1，它可参与通信
		if cnt, ok := rows[c.x]; ok && cnt > 1 {
			ans++
		} else if cnt, ok := columns[c.y]; ok && cnt > 1 {
			ans++
		}
	}

	return ans
}
