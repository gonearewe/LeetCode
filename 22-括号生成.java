/*
给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

例如，给出 n = 3，生成结果为：

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
*/
class Solution { //回溯法
    //动态构建时保证括号有效见此问
    //静态验证括号有效参考第20题，使用堆栈
    List<String> ans =new ArrayList<String>();
    public List<String> generateParenthesis(int n) {
        char[] s=new char[n*2];  //数组创建方法
        trace(s, n,0,0);
        return ans;
    }
    public void trace(char[] s,int n,int leftnum,int rightnum){
        //交给回溯函数更改的变量（rightnum等）要通过函数参数传给子函数以保证各分叉的独立，不能使用类成员变量
        //传递并修改String时会带来大量复制开销，使用char数组效率更高，char数组也是引用传递
        if(leftnum==n&&rightnum==n)//左右括号数都为n时，该分叉结束
            ans.add(String.valueOf(s));//转换为String，添加到结果中
        else{//构建过程中保证括号有效的两个条件
            if(leftnum<n){//左括号数小于n
                s[rightnum+leftnum]='(';
                //一共只有一个char数组，一个分支用完了其他分支可以接着用（会覆盖上一个分支记录的元素)
                //本题结果是定长的数组（n*2),否则应该使用stack保存路径（即动态生成的结果）
                trace(s, n,leftnum+1,rightnum);

            }
            if(rightnum<leftnum){//右括号只可以在左括号数大于右括号数时添加
                s[rightnum+leftnum]=')';
                trace(s, n,leftnum,rightnum+1);
            }
        }

    }
}