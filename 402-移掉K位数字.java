// 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。

// 注意:
// num 的长度小于 10002 且 ≥ k。
// num 不会包含任何前导零。

// 示例 1 :\
// 输入: num = "1432219", k = 3
// 输出: "1219"
// 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。

// 示例 2 :
// 输入: num = "10200", k = 1
// 输出: "200"
// 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。

// 示例 3 :
// 输入: num = "10", k = 2
// 输出: "0"
// 解释: 从原数字移除所有的数字，剩余为空就是0。

// 贪心算法，从左到右遍历，删除比右边数字大的数字
class Solution {
    public String removeKdigits(String num, int k) {
        if (num.length() <= 1) {
            return k == 1 ? "0" : num;
        }

        int cnt = 0;
        StringBuilder s = new StringBuilder(num);
        for (int i = 0; i < s.length() - 1 && cnt != k; i++) {
            if ((int) s.charAt(i) > (int) s.charAt(i + 1)) {
                s.deleteCharAt(i);
                cnt++;
                // 边迭代边删除需要修改 i，至于为什么不是 i--，考虑用例 "1234567890" with 9
                // 其实碰到迭代索引带回撤的应该想到用栈代替 ...
                i -= 2; 
                i = Math.max(-1, i);
            }
        }

        for (int i = 0; i < k - cnt; i++) { // 未删满 k 个，此时 s 完全升序，由后向前删除满 k 个字符
            s.deleteCharAt(s.length() - 1);
        }

        while (s.length() > 0 && s.charAt(0) == '0') { // 删除前导 0
            s.deleteCharAt(0);
        }
        return s.length() == 0 ? "0" : s.toString();
    }
}
