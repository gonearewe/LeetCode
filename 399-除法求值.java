// 给出方程式 A / B = k, 其中 A 和 B 均为用字符串表示的变量， k 是一个浮点型数字。
// 根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。

// 示例 :
// 给定 a / b = 2.0, b / c = 3.0
// 问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
// 返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]

// 输入为: 
// vector<pair<string, string>> equations, 
// vector<double>& values, 
// vector<pair<string, string>> queries
// (方程式，方程式结果，问题方程式)， 
// 其中 equations.size() == values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），
// 并且结果值均为正数。以上为方程式的描述。 返回vector<double>类型。

// 基于上述例子，输入如下：
// equations(方程式) = [ ["a", "b"], ["b", "c"] ],
// values(方程式结果) = [2.0, 3.0],
// queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
// 输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    // 带权并查集问题，构建有向图

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equationsSize = equations.size();
        Map<String, Integer> hashMap = new HashMap<>();
        UnionFind unionFind = new UnionFind(2 * equationsSize);

        int id = 0;
        for (int i = 0; i < equationsSize; i++) {
            List<String> equation = equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);

            if (!hashMap.containsKey(var1)) {
                hashMap.put(var1, id);
                id++;
            }

            if (!hashMap.containsKey(var2)) {
                hashMap.put(var2, id);
                id++;
            }
            unionFind.union(hashMap.get(var1), hashMap.get(var2), values[i]);
        }

        // 第 2 步：做查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);
            if (id1 == null || id2 == null) {
                res[i] = -1.0;
            } else {
                res[i] = unionFind.isConnected(id1, id2);
            }
        }
        return res;
    }

    private class UnionFind {

        private int[] parent;

        /**
         * 把父结点作为分母时的商
         */
        private double[] weight;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        public void union(int x, int y, double value) {
            int rootX = find(x);
            int rootY = find(y);
            parent[rootX] = rootY;
            // 需要列方程计算
            weight[rootX] = weight[y] * value / weight[x];
        }

        public int find(int x) {
            if (x != parent[x]) {
                // 注意：这里维护 weight 的定义
                int origin = parent[x];
                parent[x] = find(parent[x]);

                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        public double isConnected(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return weight[x] / weight[y];
            } else {
                return -1.0d;
            }
        }
    }
}