/*
给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

示例:

s = "3[a]2[bc]", 返回 "aaabcbc".
s = "3[a2[c]]", 返回 "accaccacc".
s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".



注意示例，可以嵌套。先写出尽可能普遍的一个情况来研究：
4[ab34[dbc2[f]]k]2[2[m]]
那么，本题难点在于括号内嵌套括号，需要从内向外生成与拼接字符串，联想到栈的先入后出特性。
然后考虑栈有两种实现：手动维护辅助栈，回溯函数栈。我选择回溯法。
回溯法考虑函数起点和终点，传递的参数。

循环遍历时每次只能考虑到一个字符，数字不一定是个位数，所以不好使用数字作为起点，所以选择‘[’做起点，‘]’做终点。
但是‘[’和‘]’不是一个标准的结构（标准结构是‘xxx[abcd]'),所以还要考虑开始情形。
不妨假设整个字符串在一个外层的'['和']'内。即输入是：
[4[ab34[dbc2[f]]k]2[2[m]]]
这样，主函数入口搞定了。

为了减少传递开销，应当使用索引向子函数分配任务。输入的字符串要一直传递下去，那么子函数需要知道自己从哪一个索引开始。
同时，子函数完成自己的任务，应当把它构建的字符串返回给上层。但是注意，上层函数逐个字符遍历，一定要跳过子函数处理的
部分，那么，子函数还需要返回自己任务结束时的索引。Java不支持多返回值，所以只能够返回String数组，封装两个返回值。

*/
//栈
class Solution {
    public String decodeString(String s) {
        return backtrack(0, s)[1];//索引1对应字符串
    }

    private String[] backtrack(int index, String s) {
        StringBuilder str = new StringBuilder();
        int times = 0; 
        while (index<s.length()&&s.charAt(index) != ']') {  
            //因为假设整个字符串在一个外层的'['和']'内，所以检查索引防止最外层函数越界
            if (Character.isDigit(s.charAt(index)))
                times = times * 10 + s.charAt(index) - '0'; // 注意：不一定是个位数
            else if (s.charAt(index) == '[') {
                String[] tmp = backtrack(index + 1, s); // 临时保存一下，避免重复字符串时反复递归
                for (int j = 0; j < times; j++)
                    str.append(tmp[1]);
                times = 0;  // 分析可知，每个栈可能处理多次数字，所以清零
                index = Integer.parseInt(tmp[0]);  //把结束索引变成String，数组必须同类型
            } else
                str.append(s.charAt(index));  //如果是字符，放进StringBuilder
            index++;
        }
        return new String[] { String.valueOf(index), str.toString() };//注意要有 new
    }
}