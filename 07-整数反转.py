# 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
#
# 示例 1:
# 输入: 123
# 输出: 321
#
# 示例 2:
# 输入: -123
# 输出: -321
#
# 示例 3:
# 输入: 120
# 输出: 21
#
# 注意:
# 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，
# 如果反转后整数溢出那么就返回 0。
from functools import reduce


class Solution:
    def reverse(self, x: int) -> int:
        negative = False
        if x < 0:
            negative = True
            x = abs(x)

        li = []
        while x != 0:
            li.append(x % 10)
            x //= 10

        res = reduce(lambda a, b: a * 10 + b, li, 0)
        if negative:
            res = -res

        if -2 ** 31 - 1 <= res <= 2 ** 31:
            return res
        else:
            return 0


if __name__ == "__main__":
    inputs = [123, -123, 120]
    expects = [321, -321, 21]
    for i in range(len(inputs)):
        result = Solution().reverse(inputs[i])
        if expects[i] != result:
            print("fail: expect %s: got %s" % (expects[i], result))
