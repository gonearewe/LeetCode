/*
一条包含字母 A-Z 的消息通过以下方式进行了编码：

'A' -> 1
'B' -> 2
...
'Z' -> 26
给定一个只包含数字的非空字符串，请计算解码方法的总数。

示例 1:

输入: "12"
输出: 2
解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
示例 2:

输入: "226"
输出: 3
解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
*/
class Solution {//无返回值的回溯，递归的传递有开销，耗时844ms
    int cnt;//没有返回值的话，让各个分支函数修改同一个类成员变量
    public int numDecodings(String s) {
        char[] nums=s.toCharArray();
        // !!!!!! 我暂时还不确定（～…～）
        //如果传递只读值，String是引用传递，效率高
        //如果传递需要修改的值，char数组效率更高
        backtrack(nums, 0, s.length());
        return cnt;
    }
    public void backtrack(char[] nums,int i,int len){
        if(i<=len-1&&nums[i]=='0') return ;  //任何情况下，0开头的数都不可以解码
        //一个坑 ：比较对象是字符‘0’，而不是数字0
        if(i>=len-1){
            cnt++;
        }else {//两个分叉
            if(i<=len-2&&((nums[i]-'0')*10+(nums[i+1]-'0')<=26)){
                //(nums[i]-'0')*10+(nums[i+1]-'0')<=26比nums[i]=='1'||(nums[i]=='2'&&nums[i+1]<='6')更加优雅
                backtrack(nums,i+2, len);
            }
                backtrack(nums, i+1, len);
            
        }//在无返回值的回溯中，不符合继续分叉条件和结束条件的分支自然终结，无法修改结果cnt
    }//无返回值回溯其实结果在递归中通过更新cnt就已经得到了，事实上并没有回溯
}


// 作者：reedfan
// 链接：https://leetcode-cn.com/problems/decode-ways/solution/dong-tai-gui-hua-ji-bai-liao-99de-javayong-hu-by-r/
class Solution{  //有返回值的回溯，写出来相对整洁一点，耗时786ms
    public int numDecodings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            return digui(s, 0);
        }
    
    //递归的套路，加一个index控制递归的层次
        private int digui(String s, int start) {
            //书写的第一步，应该是加递归结束条件，避免死循环
            if (s.length() == start) {
                return 1;//正常结束分支通过返回1实现
            }
            //以0位开始的数是不存在的
            if (s.charAt(start) == '0') {
                return 0;//终止分支通过返回0实现
            }
            //递归的递推式应该是如果index的后两位小于等于26，  
            // digui(s, start) = digui(s, start+1)+digui(s, start+2)   
            // 否则digui(s, start) = digui(s, start+1)
            int ans1 = digui(s, start + 1);
            int ans2 = 0;
            if (start < s.length() - 1) {
                int ten = (s.charAt(start) - '0') * 10;
                int one = (s.charAt(start + 1) - '0');
                //拆开写更加清楚一些
                if (ten + one <= 26) {
                    ans2 = digui(s, start + 2);
                }
            }
            return ans1 + ans2;//有返回值的回溯最后需要用于回溯的return，回溯结束才可以计算出结果
        }
    }


class Solution{//动态规划，快了很多
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[len] = 1;
        if (s.charAt(len - 1) == '0') {
            dp[len - 1] = 0;
        } else {
            dp[len - 1] = 1;
        }
        for (int i = len - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp[i] = 0;
                continue;
            }
            if ((s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0') <= 26) {
                dp[i] = dp[i + 1] + dp[i + 2];
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }
}
