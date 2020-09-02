// N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，
// 以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。

// 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，
// 第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。

// 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。

// 示例 1:
// 输入: row = [0, 2, 1, 3]
// 输出: 1
// 解释: 我们只需要交换row[1]和row[2]的位置即可。

// 示例 2:
// 输入: row = [3, 2, 0, 1]
// 输出: 0
// 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
// 说明:
// len(row) 是偶数且数值在 [4, 60]范围内。
// 可以保证row 是序列 0...len(row)-1 的一个全排列。

import java.util.stream.IntStream;

class Solution {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int[] location = new int[n]; // location[i] 记录标识为 i 的人在 row 中的位置索引
        for (int i = 0; i < n; i++) {
            location[row[i]] = i;
        }

        var set = new UnionFindSet(n);
        for (int i = 0; i < n; i += 2) {
            set.union(i / 2, location[pairOf(row[i])] / 2);
            set.union(i / 2, location[pairOf(row[i + 1])] / 2);
        }

        return n - set.getCount();
    }

    // 返回 a 的情侣的 id
    private int pairOf(int a) {
        if (a % 2 == 0) {
            return a + 1;
        } else {
            return a - 1;
        }
    }

    private class UnionFindSet {
        private final int[] parent;
        private int cnt; // 记录连通图数目

        UnionFindSet(int n) {
            cnt = n;
            parent = IntStream.range(0, n).toArray();
        }

        // 彻底路径压缩（推荐）
        int find(int a) {
            if (parent[a] != a) { // I'm not the root
                parent[a] = find(parent[a]); // set my parent to the root
            }
            return parent[a]; // return the root
        }

        void union(int a, int b) {
            if (find(a) != find(b)) {
                cnt--;
                parent[find(a)] = find(b);
            }
        }

        int getCount() {
            return cnt;
        }
    }
}
