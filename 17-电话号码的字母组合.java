/*
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。



示例:

输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
*/
class Solution {  //回溯法
    Map<String,String> m=new HashMap<String,String>(){
        private static final long serialVersionUID = 1L;

        { //java中构建map的方法
        put("2","abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    List<String> ans =new ArrayList<String>();
    public List<String> letterCombinations(String digits) {//参考示例，数字的顺序是不变的
        if (digits.length()!=0){
                   list("", digits);
        }

       return ans;
    }
    public void list(String combination,String remaining){  //一个处理当前节点的函数
        if(remaining.length()!=0){
            String num=remaining.substring(0,1);  //当前节点只处理下一个字母
            String letters=m.get(num);
            for(int i=0;i<letters.length();i++){
                list(combination+letters.substring(i,i+1), remaining.substring(1));
                //string只可以加string，使用substring()而不是charAt()
                //在当前节点，调用自己来对下一个数字对应的字母进行分叉
            }
        }else{
            ans.add(combination);   //到达终节点，把生成的字符串添加到结果List中
        }
    }
}