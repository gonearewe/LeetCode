// 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好
// 可以由 words 中所有单词串联形成的子串的起始位置。

// 注意子串要与 words 中的单词完全匹配，
// 中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。

// 示例 1：
// 输入：
//   s = "barfoothefoobarman",
//   words = ["foo","bar"]
// 输出：[0,9]
// 解释：
// 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
// 输出的顺序不重要, [9,0] 也是有效答案。

// 示例 2：
// 输入：
//   s = "wordgoodgoodgoodbestword",
//   words = ["word","good","best","word"]
// 输出：[]

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    // 用 HashMap 代表无序元素 words，键是字符串本身，值是其在 words 中出现的次数
    private HashMap<String, Integer> wordsPattern = new HashMap<>();

    public List<Integer> findSubstring(String s, String[] words) {
        var res = new ArrayList<Integer>(0);
        if (s.length() == 0 || (words.length == 0 || words[0].length() == 0)) {
            return res;
        }

        for (var e : words) {
            wordsPattern.put(e, wordsPattern.getOrDefault(e, 0) + 1);
        }

        var l = words[0].length();
        var n = words.length;
        out:
        for (int i = 0; i + l * n <= s.length(); i++) {
            // 滑动窗口的总长是 words 自由组合得到的字符串的长度
            var str = s.substring(i, i + l * n);
            var strPattern = new HashMap<String, Integer>(n);
            for (int j = 0; j < n; j++) {
                var substr = str.substring(j * l, j * l + l);
                if (wordsPattern.getOrDefault(substr, -1) == -1) {
                    continue out; // 子串中有 words 里没有的元素
                }
                strPattern.put(substr, strPattern.getOrDefault(substr, 0) + 1);
                if (strPattern.get(substr) > wordsPattern.get(substr)) {
                    continue out; // 子串中的某元素出现次数比 words 中的多
                }
            }
            res.add(i);
        }
        return res;
    }
}
