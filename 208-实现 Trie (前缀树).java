// 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
// 
// 示例:
// 
// Trie trie = new Trie();
// 
// trie.insert("apple");
// trie.search("apple");   // 返回 true
// trie.search("app");     // 返回 false
// trie.startsWith("app"); // 返回 true
// trie.insert("app");   
// trie.search("app");     // 返回 true
// 
// 说明:
// 你可以假设所有的输入都是由小写字母 a-z 构成的。
// 保证所有输入均为非空字符串。

import java.util.*;

class Trie {
    private final Character val;
    private final Map<Character, Trie> children = new HashMap<>(64);
    private boolean isEnd = false; // 必需的

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        this.val = null;
    }

    private Trie(Character c) {
        this.val = c;
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        assert word.length() >= 1;

        if (isRoot()) {
            var c = word.charAt(0);
            if (!children.containsKey(c)) {
                children.put(c, new Trie(c));
            }
            children.get(c).insert(word);
            return;
        }

        if (word.length() == 1) {
            isEnd = true;
            return;
        }

        // 在这里 word.charAt(0) 是匹配当前结点的，因为父结点已经检查过了
        var c = word.charAt(1);
        // 我们要做的是检查孩子结点
        if (!children.containsKey(c)) {
            children.put(c, new Trie(c));
        }
        // 事实上，substring 的时间复杂度是 O(n)，把 String 转成 List 使用 subList（O(1) 复杂度）更合适
        children.get(c).insert(word.substring(1));
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        return match(word, true);
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return match(prefix, false);
    }

    private boolean match(String word, boolean wholeMatch) {
        assert word.length() >= 1;

        if (isRoot()) {
            var c = word.charAt(0);
            if (children.containsKey(c)) {
                return children.get(c).search(word);
            } else {
                return false;
            }
        }

        if (word.length() == 1) {
            return !wholeMatch || isEnd; // 注意这儿
        }

        var c = word.charAt(1);
        if (children.containsKey(c)) {
            return children.get(c).search(word.substring(1));
        } else {
            return false;
        }
    }

    private boolean isRoot() {
        return val == null;
    }

    public static void main(String[] args) {
        // Your Trie object will be instantiated and called as such:
        Trie obj = new Trie();
        obj.insert("apple");
        System.out.println(obj.search("apple"));
        System.out.println(obj.search("app"));
        System.out.println(obj.startsWith("app"));
        obj.insert("app");
        System.out.println(obj.search("app"));
    }
}