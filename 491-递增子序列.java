// 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。

// 示例:
// 输入: [4, 6, 7, 7]
// 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]

// 说明:
// 给定数组的长度不会超过15。
// 数组中的整数范围是 [-100,100]。
// 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。

import java.util.ArrayList;
import java.util.List;

class Solution { // 递归枚举
    private List<List<Integer>> res = new ArrayList<>();
    private int[] nums;

    public List<List<Integer>> findSubsequences(int[] nums) {
        this.nums = nums;
        backtrace(new ArrayList<Integer>(), 0, Integer.MIN_VALUE);
        return res;
    }

    private void backtrace(List<Integer> path, int cur, int last) {
        if (cur >= nums.length) {
            if (path.size() >= 2) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        if (last != nums[cur]) { // last 用于防止重复枚举
            backtrace(path, cur + 1, last);
        }
        if (nums[cur] >= last) {
            path.add(nums[cur]);
            backtrace(path, cur + 1, nums[cur]);
            path.remove(path.size() - 1);
        }
    }
}
