// 给定一个 没有重复 数字的序列，返回其所有可能的全排列。

// 示例:
// 输入: [1,2,3]
// 输出:
// [
//   [1,2,3],
//   [1,3,2],
//   [2,1,3],
//   [2,3,1],
//   [3,1,2],
//   [3,2,1]
// ]

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    private final List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        backtrace(Arrays.stream(nums).boxed().collect(Collectors.toList()), new LinkedList<>());
        return res;
    }

    private void backtrace(List<Integer> supplier, List<Integer> one) {
        if (supplier.isEmpty()) { // end of dfs
            res.add(one);
            return;
        }

        for (int i = 0; i < supplier.size(); i++) {
            var li = new LinkedList<>(supplier); // replication
            var newOne = new LinkedList<>(one); // replication
            var elem = li.remove(i);
            newOne.add(elem);
            backtrace(li, newOne);
        }
    }
}
