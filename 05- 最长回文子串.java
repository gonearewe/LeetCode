/*
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"
*/

class Solution {  // 动态规划
    public String longestPalindrome(String s) {
        int n=s.length();
        boolean[][] dp=new boolean[n][n];
        String ans="";

        // i,j为字符索引，它们确定了一个子串；显然i<=j，所以使用的只有dp表的一半（右上）
        for(int i=n-1;i>=0;i--){ 
            for(int j=i;j<n;j++){
                if(j==i){   // 斜对角线，一个字符的字符串       
                   dp[i][j]=true;
                }else if(j==i+1){  // 两个字符的字符串  
                    // 斜对角线上方第一条线，它按状态转移方程来看，没有上一个dp元素，单独讨论
                    dp[i][j]=(
                        s.charAt(j)==s.charAt(i)
                    );
                }else{
                    // 状态转移方程，当前子串[i,j]是回文的条件是子串[i+1,j-1](它的左下角的dp元素)为回文且s[i]==s[j]
                    dp[i][j]=(
                        (s.charAt(j)==s.charAt(i))&&dp[i+1][j-1]
                    );
                }

                if(dp[i][j]&&(j-i+1>ans.length()))
                    ans=s.substring(i,j+1);  // 发现更长的回文
            }
        }

        return ans;
    }
}