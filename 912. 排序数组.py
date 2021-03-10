# 给你一个整数数组 nums，请你将该数组升序排列。
#
# 示例 1：
# 输入：nums = [5,2,3,1]
# 输出：[1,2,3,5]
#
# 示例 2：
# 输入：nums = [5,1,1,2,0,0]
# 输出：[0,0,1,1,2,5]
#
# 提示：
# 1 <= nums.length <= 50000
# -50000 <= nums[i] <= 50000

class Solution:
    def sortArray(self, nums: List[int]) -> List[int]:
        if len(nums) <= 1:
            return nums
        return self.sortArray([e for e in nums[1:] if e <= nums[0]]) + [nums[0]] + \
               self.sortArray([e for e in nums[1:] if e > nums[0]])


def quick_sort(array, left, right):
    if left >= right:
        return
    key, low, high = array[left], left, right
    while left < right:
        while left < right and array[right] >= key:
            right -= 1
        array[left] = array[right]
        while left < right and array[left] < key:
            left += 1
        array[right] = array[left]
    array[left] = key  # left == right
    quick_sort(array, low, left - 1)
    quick_sort(array, left + 1, high)
