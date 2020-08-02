// 给定一个数组 candidates 和一个目标数 target ，
// 找出 candidates 中所有可以使数字和为 target 的组合。
// candidates 中的每个数字在每个组合中只能使用一次。

// 说明：
// 所有数字（包括目标数）都是正整数。
// 解集不能包含重复的组合。 

// 示例 1:
// 输入: candidates = [10,1,2,7,6,1,5], target = 8,
// 所求解集为:
// [
//   [1, 7],
//   [1, 2, 5],
//   [2, 6],
//   [1, 1, 6]
// ]

// 示例 2:
// 输入: candidates = [2,5,2,1,2], target = 5,
// 所求解集为:
// [
//   [1,2,2],
//   [5]
// ]

import java.util.*;

class Solution { // 回溯 + 剪枝
    private int[] candidates;
    private int target;
    private Set<List<Integer>> res = new HashSet<>(); // 去重

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        this.target = target;
        helper(new ArrayList<>(), 0, 0);
        return new ArrayList<>(res);
    }

    private void helper(List<Integer> li, int sum, int nextIndex) {
        var c = candidates[nextIndex];
        if (c + sum == target) { // 找到了一组
            li.add(c);
            res.add(li);
        } else if (c + sum < target && nextIndex + 1 <= candidates.length - 1) {
            helper(new ArrayList<>(li), sum, nextIndex + 1); // 不加上当前元素的情况
            li.add(c);
            helper(li, sum + c, nextIndex + 1); // 加上当前元素的情况
        }
        // c + sum > target 的情况被剪枝了
    }
}
