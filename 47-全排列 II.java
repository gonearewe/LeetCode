// 给定一个可包含重复数字的序列，返回所有不重复的全排列。
//
// 示例:
// 输入: [1,1,2]
// 输出:
// [[1,1,2],
//  [1,2,1],
//  [2,1,1]]

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Solution { // 在 “47-全排列” 的基础上小作改动
    private final List<List<Integer>> res = new LinkedList<>();

    List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums); // 排序，使得相同元素位置相邻
        backtrace(Arrays.stream(nums).boxed().collect(Collectors.toList()), new LinkedList<>());
        return res;
    }

    private void backtrace(List<Integer> supplier, List<Integer> one) {
        if (supplier.isEmpty()) { // end of dfs
            res.add(one);
            return;
        }

        var last = Integer.MAX_VALUE; // 记录上一个分支在当前位置选择的元素
        for (int i = 0; i < supplier.size(); i++) {
            if (supplier.get(i) == last) { // 一个位置上的元素必须不同
                continue;
            }

            var li = new LinkedList<>(supplier); // replication
            var newOne = new LinkedList<>(one); // replication
            var elem = li.remove(i);
            newOne.add(elem);
            last = elem; // 更新记录
            backtrace(li, newOne);
        }
    }
}
