# 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的
# 两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
#
# 说明：你不能倾斜容器，且 n 的值至少为 2。
#
# 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
#
# 示例:
# 输入: [1,8,6,2,5,4,8,3,7]
# 输出: 49

# 最初我们考虑由最外围两条线段构成的区域。现在，为了使面积最大化，我们需要考虑更长的两条线段之间的区域。如果我们试图将
# 指向较长线段的指针向内侧移动，矩形区域的面积将受限于较短的线段而不会获得任何增加。但是，在同样的条件下，
# 移动指向较短线段的指针尽管造成了矩形宽度的减小，但却可能会有助于面积的增大。因为移动较短线段的指针会得到一条相对较长的线段，
# 这可以克服由宽度减小而引起的面积减小。

from typing import List


class Solution:  # 双指针（一头一尾）
    def maxArea(self, height: List[int]) -> int:
        left, right, res = 0, len(height) - 1, 0
        while left < right:
            if height[left] < height[right]:
                res = max(height[left] * (right - left), res)
                left += 1
            else:
                res = max(height[right] * (right - left), res)
                right -= 1
        return res


if __name__ == "__main__":
    result = Solution().maxArea([1, 8, 6, 2, 5, 4, 8, 3, 7])
    if result != 49:
        print("fail: expect 49: got %s" % result)
