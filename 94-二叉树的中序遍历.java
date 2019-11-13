/*
给定一个二叉树，返回它的中序 遍历。

示例:

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,3,2]

进阶: 递归算法很简单，你可以通过迭代算法完成吗？
*/
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
//递归与迭代的转化
class Solution { // 简单的递归
    private List<Integer> ans = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root != null) // 检查一下根节点
            backtrack(root);
        return ans;
    }

    private void backtrack(TreeNode root) {
        if (root.left != null)
            backtrack(root.left);

        ans.add(root.val); // 中序遍历就是左根右

        if (root.right != null)
            backtrack(root.right);
    }
}

class Solution { // 迭代法，代码不够清晰
    private List<Integer> ans = new ArrayList<>();
    private Stack<TreeNode> stack = new Stack(); //栈保存结点

    public List<Integer> inorderTraversal(TreeNode root) {
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {//栈不为空时也要循环，向上查找
            while (cur != null) {
                stack.push(cur);  //手动使用栈保存路径
                cur = cur.left;  //一直向左
            }

            cur = stack.pop();  
            ans.add(cur.val);  //根结点
            cur=cur.right;   //再向右寻找
        }
        return ans;
    }
}