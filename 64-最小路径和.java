/*
给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

示例:

输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。
*/
class Solution {//动态规划
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        for (int i = 1; i < m; i++)
            grid[i][0] += grid[i - 1][0];
        for (int i = 1; i < n; i++)
            grid[0][i] += grid[0][i - 1];
        //第一行和第一列都是前n个格子直接相加

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) // 二重循环打表
                if (grid[i][j - 1] > grid[i - 1][j])
                    grid[i][j] += grid[i - 1][j];
                else//grid[i][j]的值等于左边和右边格子中较小的值与自己的和
                    grid[i][j] += grid[i][j - 1];// 状态转移方程
        //  for (int i = 0; i < m; i++)
        //      for (int j = 0; j < n; j++)
        //          System.out.println(grid[i][j]);
        //动态规划调试时直接打出各个阶段的表
        //提交时记得要注释掉，打印耗时很严重
        return grid[m - 1][n - 1];
    }
}