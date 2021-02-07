class Solution:
    def check(self, nums: List[int]) -> bool:
        def isSorted(l):
            return all(l[i] <= l[i + 1] for i in range(len(l) - 1))

        for i in range(len(nums) - 1):
            if nums[i + 1] < nums[i]:
                return isSorted(nums[:i + 1]) and isSorted(nums[i + 1:]) and nums[0] >= nums[-1]
        return True
