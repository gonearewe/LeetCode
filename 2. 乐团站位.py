# 某乐团的演出场地可视作 num * num 的二维矩阵 grid（左上角坐标为 [0,0])，每个位置站有一位成员。
# 乐团共有 9 种乐器，乐器编号为 1~9，每位成员持有 1 个乐器。
#
# 为保证声乐混合效果，成员站位规则为：自 grid 左上角开始顺时针螺旋形向内循环以 1，2，...，9 循环重复排列。
# 例如当 num = 5 时，站位如图所示
#
# 请返回位于场地坐标 [Xpos,Ypos] 的成员所持乐器编号。
#
# 示例 1：
# 输入：num = 3, Xpos = 0, Ypos = 2
#
# 输出：3
# 解释：
# image.png
#
# 示例 2：
# 输入：num = 4, Xpos = 1, Ypos = 2
# 输出：5
# 解释：
# image.png
#
# 提示：
# 1 <= num <= 10^9
# 0 <= Xpos, Ypos < num

class Solution:
    def orchestraLayout(self, num: int, xPos: int, yPos: int) -> int:
        margin = min(xPos, yPos, num - xPos - 1, num - yPos - 1)
        order, width = 0, num - 2 * margin
        if xPos == margin:
            order = yPos - margin
        elif yPos == margin:
            order = 4 * width - 4 - (xPos - margin)
        elif xPos == num - margin - 1:
            order = 3 * width - 3 - (yPos - margin)
        else:
            order = width + xPos - margin - 1
        print(margin, order)
        if margin == 0:
            return order % 9 + 1
        else:
            return (order + 4 * num * margin - 4 * margin ** 2) % 9 + 1
