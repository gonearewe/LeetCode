/*
给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。

示例 1:

输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
*/
func lengthOfLongestSubstring(s string) int {    //滑动窗口法
max:=func (a int,b int) int{if a>b {return a} else {return b}}  //匿名函数
	var m=map[rune]int{}    //用map加快查找
	var start,maxlen int
	for index,char:=range s{
		j,ok:=m[char]
        if ok {
			start=max(start,j)    //滑动窗口的左边界
			}
		maxlen=max(maxlen,index+1-start)   //对每一个index找一个最大长度并比较
        m[char]=index+1     //记录字符上一次出现的位置

	}
    return maxlen
}
