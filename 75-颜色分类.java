/*
给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

注意:
不能使用代码库中的排序函数来解决这道题。

示例:

输入: [2,0,2,1,1,0]
输出: [0,0,1,1,2,2]
进阶：

一个直观的解决方案是使用计数排序的两趟扫描算法。
首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
你能想出一个仅使用常数空间的一趟扫描算法吗？
*/
class Solution {//计数排序，简单粗暴，直接超出时间限制了，所以我也不知道对不对
    public void sortColors(int[] nums) {
        int i=0,j=0;
        for(int num:nums) //一次遍历，分别计数
            if(num==0)
                i++;
            else if(num==1)
                j++;

        int cnt=0;
        while(cnt<nums.length){ //二次遍历，按数填充
            if(cnt<i)
                nums[cnt]=0;
            else if(cnt<i+j)
                nums[cnt]=1;
            else
                nums[cnt]=2;
        }
    }
}