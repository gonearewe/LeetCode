// 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

// 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，
// 否则不为 NULL 的节点将直接作为新二叉树的节点。

// 示例 1:
// 输入: 
// 	Tree 1                     Tree 2                  
//           1                         2                             
//          / \                       / \                            
//         3   2                     1   3                        
//        /                           \   \                      
//       5                             4   7                  
// 输出: 
// 合并后的树:
// 	     3
// 	    / \
// 	   4   5
// 	  / \   \ 
// 	 5   4   7
// 注意: 合并必须从两个树的根节点开始。

object Solution {
  def mergeTrees(t1: TreeNode, t2: TreeNode): TreeNode =
    (t1, t2) match {
      case (null, null) => null
      case (null, node) => node
      case (node, null) => node
      case (node1, node2) =>
        val node = new TreeNode(node1.value + node2.value)
        node.left = mergeTrees(node1.left, node2.left)
        node.right = mergeTrees(node1.right, node2.right)
        node
    }

  // Definition for a binary tree node.
  // class TreeNode(var _value: Int) {
  //   var value: Int = _value
  //   var left: TreeNode = null
  //   var right: TreeNode = null
  // }
}

