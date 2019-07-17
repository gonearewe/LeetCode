/*
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:

输入: "()"
输出: true
示例 2:

输入: "()[]{}"
输出: true
示例 3:

输入: "(]"
输出: false
示例 4:

输入: "([)]"
输出: false
示例 5:

输入: "{[]}"
输出: true
*/
func isValid(s string) bool {    //栈
	if len(s)%2!=0 {     //排除奇数个
		return false
	}
	match:=func (a rune ,b rune) bool{  
		switch{
		case a=='['&&b==']' : return true
		case a=='('&&b==')' : return true
		case a=='{'&&b=='}' : return true
		default : return false
		}
	}
	var i int
    stack:=make([]rune,len(s)+1)    //建立栈，不能用var stack []rune，否则会index out of range
	for _,char:=range s{
		if match(stack[i],char){
			i--        //pop
		}else{
			i++    
			stack[i]=char		//push char
		}
	}
	if i!=0{   //最后栈为空则括号完全匹配
		return false
	}else{
		return true
	}
}