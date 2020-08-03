// 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

// 提示：
// num1 和num2 的长度都小于 5100
// num1 和num2 都只包含数字 0-9
// num1 和num2 都不包含任何前导零
// 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式

class Solution {
    public String addStrings(String num1, String num2) {
        int carry = 0, maxlen = Math.max(num1.length(), num2.length());
        var res = new StringBuilder(maxlen + 1); // maxlen + 1 防止最高位的进位
        for (int i = 0; i <= maxlen; i++) {
            int sum = ch(num1, i) - '0' + ch(num2, i) - '0' + carry;
            carry = sum / 10; // 进位
            res.append(sum % 10); // 本位
        }
        // 判断最高位有无进位，结果逆序输出
        return res.charAt(maxlen) == '0' ? res.reverse().substring(1) : res.reverse().toString();
    }

    private char ch(String s, int index) { // 辅助函数
        if (index >= s.length()) {
            return '0';
        } else {
            return s.charAt(s.length() - index - 1); // 逆序取字符
        }
    }
}
