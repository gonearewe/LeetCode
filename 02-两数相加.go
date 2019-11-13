/*
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
*/
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
 func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	sum:=&ListNode{}
		p:=sum
		var num int
		for l1!=nil || l2!=nil{
			sum.Next=new(ListNode)
			sum=sum.Next
			if l1!=nil{
				if l2!=nil{
					sum.Val=l1.Val+l2.Val+num
				}else{
					sum.Val=l1.Val+num
				}
			}else{
				sum.Val=l2.Val+num
			}
			num= sum.Val/10
			sum.Val=sum.Val%10
			if l1!=nil{
				l1=l1.Next
			}
			if l2!=nil{
				l2=l2.Next
			}
					
		}
		if num!=0{
			sum.Next=new(ListNode)
			sum=sum.Next
			sum.Val=num
		}
		return p.Next
	}