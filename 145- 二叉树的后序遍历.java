/*
给定一个二叉树，返回它的 后序 遍历。

示例:

输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [3,2,1]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution { // 官方给出的迭代法，作弊式的逆向前序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> ans = new LinkedList<>();  //注意最前面类型是LinkedList而不是List
        if (root == null) // 因为下面root结点直接进栈，必须检查null
            return ans;

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollLast(); 
            ans.addFirst(cur.val);

            if (cur.left != null)
                stack.add(cur.left);
            if (cur.right != null)
                stack.add(cur.right);
            
        }

        return ans;
    }
}