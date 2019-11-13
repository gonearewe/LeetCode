/*
给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

 

示例:

给定 1->2->3->4, 你应该返回 2->1->4->3.

*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {   // 递归法
    public ListNode swapPairs(ListNode head) {
        if(head==null){  
            return null;
        }

        // 需要一个前置结点，因为head的位置会变，前置结点可以保存链表的头部位置
        ListNode root=new ListNode(-1);  
        root.next=head;

        swap(root);
        return root.next; // 返回root.next而不是head
    }

    // 给定结点pre，处理紧跟其后的两个结点
    private void swap(ListNode pre) {
        // 链表用next必定检查null
        if(pre.next==null||pre.next.next==null)
            return;
        
        ListNode cur=pre.next;
        ListNode next=cur.next;
        
        // 处理结点的先后顺序有影响，要想清楚
        cur.next=next.next;
        next.next=cur;
        pre.next=next;
        
        swap(cur);        
    }
}