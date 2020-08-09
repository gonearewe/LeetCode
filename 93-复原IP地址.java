// 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
// 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。

// 示例:
// 输入: "25525511135"
// 输出: ["255.255.11.135", "255.255.111.35"]

import java.util.ArrayList;
import java.util.List;

class Solution {
    private final List<String> li = new ArrayList<>();
    private String s;

    public List<String> restoreIpAddresses(String s) {
        this.s = s;
        backtrace(new StringBuilder(), 0);
        return li;
    }

    private void backtrace(StringBuilder str, int num) {
        var nextIndex = str.length() - num;
        if (nextIndex >= s.length() && num == 4) { // 满足条件
            li.add(str.substring(0, str.length() - 1)); // 注意结尾会多出一个点
            return;
        } else if (nextIndex >= s.length() || num == 4) { // 走到头了却不满足条件
            return;
        }

        if (s.charAt(nextIndex) != '0') { // "010010" 为例，'0' 开头的只能是自己本身
            // 选三位
            if (nextIndex + 2 < s.length() && Integer.valueOf(s.substring(nextIndex, nextIndex + 3)) <= 255) {
                var newStr = new StringBuilder(str);
                newStr.append(s.substring(nextIndex, nextIndex + 3));
                newStr.append('.');
                backtrace(newStr, num + 1);
            }

            // 选两位
            if (nextIndex + 1 < s.length()) {
                var newStr = new StringBuilder(str);
                newStr.append(s.substring(nextIndex, nextIndex + 2));
                newStr.append('.');
                backtrace(newStr, num + 1);
            }
        }

        // 选一位
        str.append(s.charAt(nextIndex));
        str.append('.');
        backtrace(str, num + 1);
    }
}
