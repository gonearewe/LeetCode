/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null&&q==null) return true;
        if (p==null&&q!=null) return false;
        if (p!=null&&q==null) return false;
        if (p.val!=q.val) return false;    //只考虑当前节点要做的事和结束递归的条件

        return isSameTree(p.left, q.left)&&isSameTree(p.right, q.right);//递归
    }
}