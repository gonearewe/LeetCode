# 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
# 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
#
# 示例:
# 给定如下二叉树，以及目标和   sum = 22，
#
#          5
#         / \
#        4   8
#       /   / \
#      11  13  4
#     /  \    / \
#    7    2  5   1
# 返回:
# [
#     [5,4,11,2],
#     [5,8,4,5]
# ]
#
# 提示：
# 节点总数 <= 10000

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def pathSum(self, root: TreeNode, sum: int) -> List[List[int]]:
        ans = []

        def dfs(root: TreeNode, sum: int, path: List[int]):
            if root is None:
                return
            if root.left is None and root.right is None:
                if sum == root.val:
                    ans.append(path + [root.val])
                return
            path.append(root.val)
            dfs(root.left, sum - root.val, path)
            dfs(root.right, sum - root.val, path)
            path.pop()

        dfs(root, sum, [])
        return ans
