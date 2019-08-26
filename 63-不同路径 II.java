/*
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？



网格中的障碍物和空位置分别用 1 和 0 来表示。

说明：m 和 n 的值均不超过 100。

示例 1:

输入:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
输出: 2
解释:
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右
*/
class Solution {  //有限制条件的动态规划
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        //这是获取二维数组行数和列数的方法
        int[][] dp = obstacleGrid;  //直接使用传递的数组，避免扩大空间复杂度
       
        // boolean flag = false;
        // for (int i = 0; i < m; i++)
        //     if (flag == false) {
        //         if (dp[i][0] == 1) {
        //             dp[i][0] = 0;
        //             flag = true;
        //         }
        //         else
        //             dp[i][0] = 1;
        //     } else
        //         dp[i][0] = 0;
        
        // flag=false;
        // for (int i = 0; i < n; i++)
        //     if (flag == false) {
        //         if (dp[0][i] == 1) {
        //             dp[0][i] = 0;
        //             flag = true;
        //         }
        //         else
        //             dp[0][i] = 1;
        //     } else
        //         dp[0][i] = 0;
        //我一开始这么写，嵌套太多，重构如下
        if(dp[0][0]==1)
            return 0;
        dp[0][0]=1;
        // 初始化第一行和第一列
        //一般来说，操作数组索引都要防止索引越界，这里i<m可以保证
        for (int i = 1; i < m; i++)
            if (dp[i][0]==1)
                dp[i][0]=0;
            else
                dp[i][0]=dp[i-1][0];
        for (int i = 1; i < n; i++)
            if (dp[0][i]==1)
                dp[0][i]=0;
            else
                dp[0][i]=dp[0][i-1];
        //遍历第一行，如果有一个格点初始值为 1 ，说明当前节点有障碍物，没有路径可以通过，设值为 0 ；
        //否则设这个值是前一个格点的值，即obstacleGrid[i,j] = obstacleGrid[i,j-1]
        
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) // 二重循环打表
                if (dp[i][j] == 1)
                    dp[i][j] = 0;//对于障碍物，清零
                else
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];// 状态转移方程
        // dp[i][j]位置的路径数等于左边和上边一格的路径数之和
        return dp[m - 1][n - 1];// 表的最后一个元素就是要求的结果
    }
}