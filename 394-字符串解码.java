
注意示例，可以嵌套。先写出尽可能普遍的一个情况来研究：
4[ab34[dbc2[f]]k]2[2[m]]
那么，本题难点在于括号内嵌套括号，需要从内向外生成与拼接字符串，联想到栈的先入后出特性。
然后考虑栈有两种实现：手动维护辅助栈，回溯函数栈。我选择回溯法。
回溯法考虑函数起点和终点，传递的参数。
循环遍历时每次只能考虑到一个字符，数字不一定是个位数，所以不好使用数字作为起点，所以选择‘[’做起点，‘]’做终点。
//栈
class Solution {
    public String decodeString(String s) {
        return backtrack(2,s.charAt(0),s);
    }
    private String backtrack(int i,String s){
        StringBuilder str =new StringBuilder();
        int times=0;  //分析可知，每个栈只处理一次数字
        while(s.charAt(i)!=']'){
            if(Character.isDigit(s.charAt(i)))
                times=times*10+s.charAt(i)-'0';  //注意：不一定是个位数
            else if(s.charAt(i)=='['){
                String tmp=backtrack(i+1, s);  //临时保存一下，避免重复字符串时反复递归
                for(int j=0;j<times;j++)
                    str.append(tmp);
            }else
                str.append(s.charAt(i));
            i++;
        }
        return str.toString();
    }
}
str.append(backtrack(i+2,s.charAt(i)-'0',s));