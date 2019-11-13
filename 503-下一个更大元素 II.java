/*
给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。

示例 1:

输入: [1,2,1]
输出: [2,-1,2]
解释: 第一个 1 的下一个更大的数是 2；
数字 2 找不到下一个更大的数； 
第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
注意: 输入数组的长度不会超过 10000。
*/
//环形的数组计算机无法实现，但是对于只转一圈，直接再接一个相同的数组就行了
class Solution {  //巧妙的方法，但是效率不高
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[nums.length];
        int n = nums.length;

        //对于环形数组使用i%n的方法模拟，不需要真的接一个相同的数组
        for (int i = n * 2 - 1; i >= 0; i--) {  
            //一开始LeetCode显示超出时间限制，后来发现是写成了i++
            //注意：是反方向遍历的，因为找的是右侧的下一个最大数
            // 注意首先判断栈是否为空的特殊情况再查看栈顶
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) // 维护递减栈
                // 另：whlie单独判断栈是否非空是弹出栈中所有元素的方法
                stack.pop();
            // 发现比栈顶大的就在哈希表中记录栈中所有比它小的元素的nextGreaterElement是当前进行比较的元素
            if (stack.isEmpty())  //栈为空说明右侧没有比它更大的数
                ans[i % n] = -1;
            else
                ans[i % n] = stack.peek();

            stack.push(nums[i % n]);
        }

        return ans;
    }
}