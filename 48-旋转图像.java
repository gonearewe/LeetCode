// 给定一个 n × n 的二维矩阵表示一个图像。
// 将图像顺时针旋转 90 度。

// 说明：
// 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

// 示例 1:
// 给定 matrix = 
// [
//   [1,2,3],
//   [4,5,6],
//   [7,8,9]
// ],
// 原地旋转输入矩阵，使其变为:
// [
//   [7,4,1],
//   [8,5,2],
//   [9,6,3]
// ]

// 示例 2:
// 给定 matrix =
// [
//   [ 5, 1, 9,11],
//   [ 2, 4, 8,10],
//   [13, 3, 6, 7],
//   [15,14,12,16]
// ], 
// 原地旋转输入矩阵，使其变为:
// [
//   [15,13, 2, 5],
//   [14, 3, 4, 1],
//   [12, 6, 8, 9],
//   [16, 7,10,11]
// ]

class Solution {
    public void rotate(int[][] matrix) {
        print(matrix);
        for (int p = 0; p < (matrix.length + 1) / 2; p++) { // 由最外圈向内圈层层递进
            // p 是 matrix 当前圈的左上角元素行索引（等于列索引）
            // q 则是当前圈的右下角元素行索引（等于列索引）
            int q = matrix.length - 1 - p;
            for (int i = p; i < q; i++) { // 每一圈需要翻转 [p,q) 共计 q-p 组元素
                // i-p + index-p == q-p => index=q+p-i
                int a1 = matrix[p][i], a2 = matrix[i][q], a3 = matrix[q][q + p - i], a4 = matrix[q + p - i][p];
                // 每一组有上下左右四边上的四个元素，各自顺时针挪一个坑
                matrix[p][i] = a4;
                matrix[i][q] = a1;
                matrix[q][q + p - i] = a2;
                matrix[q + p - i][p] = a3;
            }
        }
    }

    // 打印矩阵，辅助调试
    private void print(int[][] matrix) {
        for (var row : matrix) {
            for (var e : row) {
                System.out.print(e);
            }
            System.out.println();
        }
        System.out.println();
    }
}
