# 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
#
# 示例 1:
# 输入: [3,2,1,5,6,4] 和 k = 2
# 输出: 5
#
# 示例 2:
# 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
# 输出: 4
# 说明:
# 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

# import heapq as hp


# class Solution:
#     def findKthLargest(self, nums: List[int], k: int) -> int:
#         heap = nums[:k]
#         hp.heapify(heap)
#         for i in range(k, len(nums)):
#             if nums[i] <= heap[0]:
#                 continue
#             hp.heappop(heap)
#             hp.heappush(heap, nums[i])
#         return heap[0]


class MinHeap:
    def __init__(self):
        self.li = []

    def __len__(self):
        return len(self.li)

    def _swap(self, i, j):
        self.li[i], self.li[j] = self.li[j], self.li[i]

    def _sift_up(self, i):
        parent = (i - 1) // 2
        if parent < 0:
            return

        if self.li[parent] > self.li[i]:
            self._swap(parent, i)
            self._sift_up(parent)

    def _sift_down(self, i):
        left_child, right_child = i * 2 + 1, i * 2 + 2
        if left_child >= len(self) and right_child >= len(self):
            return

        smaller = left_child  # must swap with the smaller child
        if right_child < len(self) and self.li[right_child] < self.li[smaller]:
            smaller = right_child
        if self.li[smaller] < self.li[i]:
            self._swap(smaller, i)
            self._sift_down(smaller)

    def push(self, elem):
        self.li.append(elem)
        self._sift_up(len(self) - 1)

    def pop(self):
        self._swap(len(self) - 1, 0)
        ret = self.li.pop()
        self._sift_down(0)
        return ret

    def peek(self):
        return self.li[0]


class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        heap = MinHeap()
        for i in range(k):
            heap.push(nums[i])
        for i in range(k, len(nums)):
            if nums[i] <= heap.peek():
                continue
            heap.pop()
            heap.push(nums[i])
        return heap.peek()
