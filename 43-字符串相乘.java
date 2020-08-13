// 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

// 示例 1:
// 输入: num1 = "2", num2 = "3"
// 输出: "6"

// 示例 2:
// 输入: num1 = "123", num2 = "456"
// 输出: "56088"

// 说明：
// num1 和 num2 的长度小于110。
// num1 和 num2 只包含数字 0-9。
// num1 和 num2 均不以零开头，除非是数字 0 本身。
// 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。

// 假设数字全部是低位在前，那么 num1[i] x num2[j] 的结果的第一位位于 res[i+j]，第二位向
// res[i+j+1] 进位。

// index 3 2 1 0
// 
// num1    5 0 1
// num2  x   1 6
//       _______
//       3 0 0 6
//     + 5 0 1
//       _______
// res   8 0 1 6

class Solution {
    public String multiply(String num1, String num2) {
        int[] res = new int[num1.length() + num2.length() + 5]; // 预留充足的空间
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                // 注意 res 低位在前，输入字符串低位在后
                // res[i+j] 位置的数字累加，最后才一起计算进位
                res[i + j] += (num1.charAt(num1.length() - 1 - i) - '0') * (num2.charAt(num2.length() - 1 - j) - '0');
            }
        }

        var str = new StringBuilder(res.length);
        int carry = 0; // 进位
        for (var e : res) {
            str.append((e + carry) % 10); // 当前位
            carry = (e + carry) / 10; // 进位
        }

        int i;
        for (i = str.length() - 1; i > 0 && str.charAt(i) == '0'; i--); // 去除前导的 0
        return str.replace(i + 1, str.length(), "").reverse().toString(); // 结果反转形成字符串
    }
}
