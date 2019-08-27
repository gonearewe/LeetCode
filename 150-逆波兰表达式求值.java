/*
根据逆波兰表示法，求表达式的值。

有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

说明：

整数除法只保留整数部分。
给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
示例 1：

输入: ["2", "1", "+", "3", "*"]
输出: 9
解释: ((2 + 1) * 3) = 9
示例 2：

输入: ["4", "13", "5", "/", "+"]
输出: 6
解释: (4 + (13 / 5)) = 6
示例 3：

输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
输出: 22
解释: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22


注; 逆波兰式（Reverse Polish notation，RPN，或逆波兰记法），也叫后缀表达式（将运算符写在操作数之后）
    逆波兰式在计算机看来是比较简单易懂的结构。因为计算机普遍采用的内存结构是栈式结构，它执行先进后出的顺序。
*/

class Solution {  //采用栈解决问题
    public int evalRPN(String[] tokens) {
        Stack<Integer> nums=new Stack<>();
        for(String token:tokens){  //遍历整个数组
            if(token.equals("+"))   //如果是操作符，从栈中弹出2个操作数运算
                nums.push(nums.pop()+nums.pop());
            else   if(token.equals("-"))
            nums.push(-nums.pop()+nums.pop());
            else   if(token.equals("*")) //对于加法和乘法，操作数顺序无所谓
            nums.push(nums.pop()*nums.pop());
            else   if(token.equals("/")){
                Integer tmp=nums.pop(); //注意栈顶操作数是二元操作符后面那个数
                nums.push(nums.pop()/tmp);
            }
            else 
            nums.push(Integer.parseInt(token)); //如果是操作数，压入栈
        }
        return nums.pop(); //弹出最后栈中保存的数，也就是结果
    }
}