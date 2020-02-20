
/*
将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

L   C   I   R
E T O E S I I G
E   D   H   N
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

请你实现这个将字符串进行指定行数变换的函数：

string convert(string s, int numRows);
示例 1:

输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"
示例 2:

输入: s = "LEETCODEISHIRING", numRows = 4
输出: "LDREOEIIECIHNTSG"
解释:

L     D     R
E   O E   I I
E C   I H   N
T     S     G
*/
import java.util.Collection;
import java.util.List;

class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows)// 一行或者一列的特殊情况
            return s;
        List<StringBuilder> rows = new ArrayList();
        for (int i = 0; i < numRows; i++)
            rows.add(new StringBuilder()); // 为每一行生成一个字符串
        int rowindex = numRows - 1;
        for (char c : s.toCharArray()) {
            rows.get(Math.abs(rowindex)).append(c);// 绝对值的巧妙使用
            rowindex--;
            if (rowindex == -numRows)
                rowindex = numRows - 2; // 注意rowindex的循环连接处，不是numRows-1
        }
        StringBuilder ans = new StringBuilder();
        Collections.reverse(rows); // 反转rows，因为0行一开始在最下面
        for (StringBuilder row : rows) // 冒号foreach的用法
            ans.append(row);
        return ans.toString(); // StringBuilder转化为String
    }
}