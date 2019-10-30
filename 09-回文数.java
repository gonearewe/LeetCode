/*
判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

示例 1:

输入: 121
输出: true
示例 2:

输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
示例 3:

输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。
*/
class Solution { //转换成字符串，较慢
    public boolean isPalindrome(int x) {
        if(x<0) 
            return false;
        if(x==0)
            return true;
        String s=Integer.toString(x);
        for(int i=0;i<s.length()/2;i++){
            if (s.charAt(i)!=s.charAt(s.length()-i-1))
                return false;
        }
        return true;
        
    }
}

class Solution {//数学方法，较快
    public boolean isPalindrome(int x) {
        int div=1,z=10;
        if(x==0)
            return true;
        if(x<0||x%10==0) 
            return false;
        if(x<=9)
            return true;
        while(x/div>=10){
            div*=10;
        }
        int i,j;
        while(div>=z/100){  
            i=x/div%10;
            div/=10;
            //System.out.printf("i=%d,div=%d\n",i,div);

            j=(x%z)/(z/10);
            z*=10;
            //System.out.printf("j=%d,z=%d\n",j,z);
            if(i!=j)
                return false;
        }
        return true;
}
}