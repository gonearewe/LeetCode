func lengthOfLongestSubstring(s string) int {
max:=func (a int,b int) int{if a>b {return a} else {return b}}
	var m=map[rune]int{}
	var start,maxlen int
	for index,char:=range s{
		j,ok:=m[char]
        if ok {
			start=max(start,j)
			}
		maxlen=max(maxlen,index+1-start)
        m[char]=index+1

	}
    return maxlen
}
