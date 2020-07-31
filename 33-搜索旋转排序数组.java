// 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
// 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
// 你可以假设数组中不存在重复的元素。
// 你的算法时间复杂度必须是 O(log n) 级别。

// 示例 1:
// 输入: nums = [4,5,6,7,0,1,2], target = 0
// 输出: 4

// 示例 2:
// 输入: nums = [4,5,6,7,0,1,2], target = 3
// 输出: -1

import java.util.Arrays;

class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        if (nums.length == 0)
            return -1;
        if (nums[left] == target)
            return left;
        if (nums[right] == target)
            return right;

        while (nums[left] > nums[right]) { // target 位于旋转排序区间内
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[left] < nums[mid]) { // [left,mid] 是有序的
                if (target < nums[mid] && target > nums[left]) // 它在有序的一边
                    right = mid;
                else
                    left = mid;

            } else { // [mid,right] 是有序的
                if (target > nums[mid] && target < nums[right]) // 它在有序的一边
                    left = mid;
                else
                    right = mid;
            }
        }

        // 标准库二分搜索有序数组
        int res = Arrays.binarySearch(nums, left, right + 1, target);
        return res >= 0 ? res : -1;
    }
}
