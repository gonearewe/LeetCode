// Say you have an array for which the ith element is the price of a given stock on day i.

// Design an algorithm to find the maximum profit. You may complete at most two transactions.

// Note: You may not engage in multiple transactions at the same time 
// (i.e., you must sell the stock before you buy again).

// Example 1:
// Input: prices = [3,3,5,0,0,3,1,4]
// Output: 6
// Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
// Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

// Example 2:
// Input: prices = [1,2,3,4,5]
// Output: 4
// Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
// Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.

// Example 3:
// Input: prices = [7,6,4,3,1]
// Output: 0
// Explanation: In this case, no transaction is done, i.e. max profit = 0.

// Example 4:
// Input: prices = [1]
// Output: 0

// Constraints:
// 1 <= prices.length <= 10^5
// 0 <= prices[i] <= 10^5
object Solution {
  def maxProfit(prices: Array[Int]): Int = {
    // 状态：0:未交易; 1:买入一次; 2:卖出1次; 3:买入2次; 4:卖出2次
    val dp = Array(0, -prices(0), Int.MinValue / 2, Int.MinValue / 2, Int.MinValue / 2)
    for {
      i <- 1 until prices.length
    } {
      dp(4) = dp(4) max dp(3) + prices(i)
      dp(3) = dp(3) max dp(2) - prices(i)
      dp(2) = dp(2) max dp(1) + prices(i)
      dp(1) = dp(1) max dp(0) - prices(i)
      // dp(0) = dp(0) // dp(0) 完全可以用常数 0 代替
    }

    dp.max
  }
}
