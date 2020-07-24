// 编写一个函数来查找字符串数组中的最长公共前缀。
// 如果不存在公共前缀，返回空字符串 ""。

// 示例 1:
// 输入: ["flower","flow","flight"]
// 输出: "fl"

// 示例 2:
// 输入: ["dog","racecar","car"]
// 输出: ""
// 解释: 输入不存在公共前缀。

// 说明:
// 所有输入只包含小写字母 a-z 。
object Solution {
  def longestCommonPrefix(strs: Array[String]): String = {
    if (strs.isEmpty) { // `[]`
      return ""
    }

    var i = 0
    while (strs.forall(i < _.length) && strs.map(_ (i)).distinct.length == 1)
      i += 1
    strs(0).substring(0, i)
  }
}
