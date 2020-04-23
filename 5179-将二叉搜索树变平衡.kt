// 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。
// 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。
// 如果有多种构造方法，请你返回任意一种。
//
// 示例：
// 输入：root = [1,null,2,null,3,null,4,null,null]
// 输出：[2,1,3,null,null,null,4]
// 解释：这不是唯一的正确答案，[3,1,4,null,2,null,null] 也是一个可行的构造方案。
//  
// 提示：
// 树节点的数目在 1 到 10^4 之间。
// 树节点的值互不相同，且在 1 到 10^5 之间。
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun balanceBST(root: TreeNode?): TreeNode? {
        root ?: return null
        val li = ArrayList<Int>()
        inoder(root, li)
        return generateBST(li)
    }

    // 从列表中取值构建 BST
    fun generateBST(li: List<Int>): TreeNode? {
        if (li.isEmpty()) {
            return null
        }

        val mid = li.size / 2
        val root = TreeNode(li[mid]) // 取中点作为根节点
        root.left = generateBST(li.subList(0, mid))
        root.right = generateBST(li.subList(mid + 1, li.size))
        return root
    }

    // 中序遍历树，把值放进一个列表里
    fun inoder(root: TreeNode?, li: MutableList<Int>) {
        root ?: return
        inoder(root.left, li)
        li.add(root.`val`)
        inoder(root.right, li)
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun insert(root: TreeNode, new: Int) {
        if (new < root.`val`) {
            if (root.left == null) {
                root.left = TreeNode(new)
                return
            }
            insert(root.left!!, new)
        } else {
            if (root.right == null) {
                root.right = TreeNode(new)
                return
            }
            insert(root.right!!, new)
        }
    }
}