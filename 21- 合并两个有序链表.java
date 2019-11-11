/*
将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

示例：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4

*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution { // 迭代法
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 新建一个空链表作为结果链表，程序最后返回head.next，初始值不会被访问
        ListNode head=new ListNode(-1); 
        ListNode cur=head;
        // 两个游标，依次比较两个链表的元素，把较小的填入结果链表
        while(l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                cur.next=l1;
                l1=l1.next;  // 这个链表向结果链表填入一个元素，游标更新到下一个元素上
            }else{
                cur.next=l2;
                l2=l2.next;
            }
  
            cur=cur.next; // 结果链表游标更新
        }

        // 把多出来的拼接到结果链表的最后
        if(l1==null){
            cur.next=l2;
        }else{
            cur.next=l1;
        }

        // 返回结果链表的头结点的next
        return head.next;
    }
}