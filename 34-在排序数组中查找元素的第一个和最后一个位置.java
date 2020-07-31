// 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
// 找出给定目标值在数组中的开始位置和结束位置。

// 你的算法时间复杂度必须是 O(log n) 级别。
// 如果数组中不存在目标值，返回 [-1, -1]。

// 示例 1:
// 输入: nums = [5,7,7,8,8,10], target = 8
// 输出: [3,4]

// 示例 2:
// 输入: nums = [5,7,7,8,8,10], target = 6
// 输出: [-1,-1]

class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0 || target < nums[0] || target > nums[nums.length - 1])
            return new int[] { -1, -1 };

        int left = 0, right = nums.length - 1, mid = -1;
        while (left < right) {
            mid = (left + right) / 2;
            if (nums[mid] > target)
                right = mid;
            else if (nums[mid] < target)
                left = mid + 1; // mid+1 makes sure it always narrows the range
            else
                break;
        }
        if (left == right)
            return nums[left] == target ? new int[] { left, right } : new int[] { -1, -1 };

        // now mid == target
        int l = mid, r = mid;
        while (l > left) { // 二分搜索左端点
            mid = (left + l) / 2;
            if (nums[mid] == target)
                l = mid;
            else
                left = mid + 1;
        }
        while (right > r) { // 二分搜索右端点
            mid = (right + r + 1) / 2;
            if (nums[mid] == target)
                r = mid;
            else
                right = mid - 1;
        }

        return new int[] { l, r };
    }
}
