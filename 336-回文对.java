// 给定一组互不相同的单词，找出所有不同的索引对(i, j)，使得列表中的两个单词，words[i] + words[j] ，可拼接成回文串。

// 示例 1：
// 输入：["abcd","dcba","lls","s","sssll"]
// 输出：[[0,1],[1,0],[3,2],[2,4]] 
// 解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]

// 示例 2：
// 输入：["bat","tab","cat"]
// 输出：[[0,1],[1,0]] 
// 解释：可拼接成的回文串为 ["battab","tabbat"]
import java.util.*;

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>(words.length);
        for (int i = 0; i < words.length; i++) {
            var s = new StringBuilder(words[i]);
            map.put(s.reverse().toString(), i);
        }

        var res = new HashSet<List<Integer>>();
        for (int j = 0; j < words.length; j++) {
            String T = preprocess(words[j]);
            int[] P = new int[T.length()];
            int C = 0, R = 0; // init

            // 马拉车核心代码
            for (int i = 1; i < T.length() - 1; i++) { // i 为 0、T.length()-1 对应前缀，后缀，P[i] 必为 0
                int i_mirror = 2 * C - i;
                // 简洁版这么写
                // P[i] = R > i ? Math.min(R - i, P[i_mirror]) : 0;
                if (R > i) {
                    P[i] = Math.min(R - i, P[i_mirror]); // 防止超出 R
                } else { // R == i 或者循环开始时 R < i
                    P[i] = 0;
                }

                // 碰到之前讲的三种情况时候，需要利用中心扩展法
                while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i])) {
                    P[i]++;
                }

                // 判断是否需要更新 R
                if (i + P[i] > R) {
                    C = i;
                    R = i + P[i];
                }
            }

            for (int i = 1; i < T.length() - 1; i++) {
                int start = (i - P[i]) / 2;
                int end = start + P[i];
                String postfix = words[j].substring(end);
                String prefix = words[j].substring(0, start);
                if (start == 0 && map.containsKey(postfix) && map.get(postfix) != j) { // words[j] 以回文串开头
                    var a = new ArrayList<Integer>(2);
                    a.add(map.get(postfix));
                    a.add(j);
                    res.add(a);
                }
                if (end == words[j].length() && map.containsKey(prefix) && map.get(prefix) != j) { // words[j] 以回文串结尾
                    var a = new ArrayList<Integer>(2);
                    a.add(j);
                    a.add(map.get(prefix));
                    res.add(a);
                }
            }
        }

        return new ArrayList<>(res);
    }

    private String preprocess(String s) {
        if (s.length() == 0) {
            return "^$";
        }

        var str = new StringBuilder(s.length() * 2 + 3);
        str.append("^"); // prefix
        for (var c : s.toCharArray()) {
            str.append("#");
            str.append(c);
        }
        str.append("#$");
        return str.toString();
    }
}
