/*
给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

示例 1:

输入: s = "anagram", t = "nagaram"
输出: true

示例 2:

输入: s = "rat", t = "car"
输出: false

说明:
你可以假设字符串只包含小写字母。

进阶:
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
*/
class Solution {  //排序或者哈希表
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] map = new int[26];  
        //因为key是英文字母，使用数组充当哈希表，哈希函数是从字母到它在字母表中的序号的映射
        for (int i = 0; i < s.length(); i++)
            map[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) {
            int tmp = t.charAt(i) - 'a';  //注意：写题时复制上一个循环粘贴下来，要修改对应的变量（s和t）
            map[tmp]--;  //在同一个表上面减，不用再创建一个表然后比较两个表
            if (map[tmp] < 0)
                return false;  //出现负数，提前退出
        }
        for (int cnt : map)
            if (cnt != 0)
                return false;  //常数时间遍历哈希表

        return true;

    }
}