package main

import (
	"sort"
)

// 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，
// 对该行程进行重新规划排序。所有这些机票都属于一个从JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 出发。
//
// 说明:
// 1. 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，
//    行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
// 2. 所有的机场都用三个大写字母表示（机场代码）。
// 3. 假定所有机票至少存在一种合理的行程。
//
// 示例 1:
// 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
// 输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
//
// 示例 2:
// 输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
// 输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
// 解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。

// 图的深度优先搜索
// 严谨地说，一个连通有向图 G 有欧拉路径，指存在一个顶点，从它出发，沿着有向边的方向，可以不重复地遍历图中所有的边。
// 题目中给定的机场名称是图的顶点，行程是图的边。题目要求重新安排行程，从示例可以看出每个行程都必须用到且只用一次。
// 对应到欧拉路径的定义，每条边都要走到，且不重复。那么，这道题就转化成了给定起点，求一条字典顺序最小的欧拉路径。
func findItinerary(tickets [][]string) []string {
	var graph = make(map[string][]string)
	for _, ticket := range tickets { // 建立邻接表
		graph[ticket[0]] = append(graph[ticket[0]], ticket[1])
	}
	for _, g := range graph {
		sort.Strings(g) // 字典序
	}

	var ans = make([]string, 0)
	var stack = make([]string, 1)
	stack[0] = "JFK"      // 起点入栈
	for len(stack) != 0 { // 遍历没有完成
		// 一路搜索，路径只进栈不出栈，用于回溯
		// 发现无路可走时，保存该节点到答案；并 pop(),回到上一个节点，开始下一次循环
		for {
			cur := stack[len(stack)-1] // stack.peek()
			if val, ok := graph[cur]; ok && len(val) != 0 {
				stack = append(stack, graph[cur][0]) // stack.push()
				graph[cur] = graph[cur][1:]          // 删除刚走过的路，防止环
			} else {
				ans = append([]string{cur}, ans...) // 倒序插入答案
				stack = stack[:len(stack)-1]        // stack.pop()
				break                               // 死路，开始下一次循环
			}
		}
	}

	return ans
}

func main() {
	findItinerary([][]string{
		{"MUC", "LHR"},
		{"JFK", "MUC"},
		{"SFO", "SJC"},
		{"LHR", "SFO"},
	})
}
