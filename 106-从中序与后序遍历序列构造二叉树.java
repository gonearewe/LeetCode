import java.util.*;
// 根据一棵树的中序遍历与后序遍历构造二叉树。

// 注意:
// 你可以假设树中没有重复的元素。

// 例如，给出
// 中序遍历 inorder = [9,3,15,20,7]
// 后序遍历 postorder = [9,15,7,20,3]
// 返回如下的二叉树：

//     3
//    / \
//   9  20
//     /  \
//    15   7
//
// Definition for a binary tree node.
// public class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode(int x) { val = x; }
// } 

// 首先，postorder 中的最后一个元素一定是树的根，这个根又将 inorder 序列分成了左右两棵子树。
// 现在我们只需要将后序遍历的数组中删除根元素，然后重复上面的过程处理左右两棵子树。
class Solution {
    private int postorderIndex;
    private int[] postorder;
    private Map<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.postorderIndex = postorder.length - 1; // 初始时根为后序数组的最后一个元素
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i); // 存储前序数组，用于快速查找
        }
        return build(0, inorder.length - 1);
    }

    private TreeNode build(int inorderStart, int inorderEnd) {
        if (inorderStart > inorderEnd) { // 递归结束条件
            return null;
        }

        var rootVal = postorder[postorderIndex]; // 此时根节点的值
        var root = new TreeNode(rootVal); // 建立当前节点的树
        var rootPosition = inorderMap.get(rootVal); // 中序数组中根节点的索引
        postorderIndex--; // 从右向左建立树，后序索引一直递减
        root.right = build(rootPosition + 1, inorderEnd); // 右子树
        root.left = build(inorderStart, rootPosition - 1); // 左子树

        return root;
    }
}