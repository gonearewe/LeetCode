/*
给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
说明：解集不能包含重复的子集。

示例:
输入: nums = [1,2,3]
输出:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

import java.util.*;

class Solution {
    private List<List<Integer>> result = new ArrayList<>();

    private void helper(List<Integer> li, int length, List<Integer> nums) {
        if (li.size() == length) { // 一个组合构建完成了
            result.add(li);
            return;
        }

        if (nums.size() < length - li.size()) { // 列表无法提供足够的元素以形成组合
            return;
        }

        var subnums = nums.subList(1, nums.size()); // 去掉列表第一个元素，传给下一层
        helper(new ArrayList<>(li), length, subnums); // 不要列表第一个元素的所有组合
        li.add(nums.get(0));
        helper(li, length, subnums); // 要列表第一个元素的所有组合
    }

    public List<List<Integer>> subsets(int[] nums) {
        // 把参数类型变成 Integer[] 以方便处理
        var numbers = new ArrayList<Integer>(nums.length);
        for (int e : nums) {
            numbers.add(e);
        }

        result.add(new ArrayList<>());
        for (int i = 1; i <= nums.length; i++) { // 问题分解为找到给定长度的所有组合
            helper(new ArrayList<>(i), i, numbers);
        }

        return result;
    }
}
