/*
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
*/

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
 func removeNthFromEnd(head *ListNode, n int) *ListNode {
    p1,p2:=head,head
    for n>0{
        if p1.Next==nil{   //删除第一个节点的情况，不单独处理的话这步操作会超出内存
            return head.Next   
        }
        p1=p1.Next    //p1先向前走 n个节点
        n--
    }
    for p1.Next!=nil{
        p1=p1.Next   //p1,p2一起走，p1到终点时，p2到达操作位置
        p2=p2.Next
    }
    p2.Next=p2.Next.Next  //p2跳过下一个节点，连接下下个节点
    return head
}