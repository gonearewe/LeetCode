# 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
#
# 示例 1：
# 输入：nums = [1,2,3]
# 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
#
# 示例 2：
# 输入：nums = [0,1]
# 输出：[[0,1],[1,0]]
#
# 示例 3：
# 输入：nums = [1]
# 输出：[[1]]
#
# 提示：
# 1 <= nums.length <= 6
# -10 <= nums[i] <= 10
# nums 中的所有整数 互不相同

class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        self.visited = [False for i in range(len(nums))]
        self.ans = []

        def dfs(li):
            if len(li) == len(nums):
                self.ans.append(li[:])
                return
            for i, v in enumerate(self.visited):
                if v:
                    continue
                self.visited[i] = True
                li.append(nums[i])
                dfs(li)
                li.pop()
                self.visited[i] = False

        dfs([])
        return self.ans
