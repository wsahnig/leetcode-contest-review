package 第270场周赛;

/**
 * https://leetcode.cn/problems/delete-the-middle-node-of-a-linked-list/description/
 * 
 * 删除链表的中间节点（中等）
 * 
 * 给你一个链表的头节点 head 。删除 链表的 中间节点 ，并返回修改后的链表的头节点 head 。
 * 长度为 n 链表的中间节点是从头数起第 ⌊n / 2⌋ 个节点（下标从 0 开始），其中 ⌊x⌋ 表示小于或等于 x 的最大整数。
 * 对于 n = 1、2、3、4 和 5 的情况，中间节点的下标分别是 0、1、1、2 和 2 。
 * 
 * 示例：
 * 输入：head = [1,3,4,7,1,2,6]
 * 输出：[1,3,4,1,2,6]
 * 
 * 输入：head = [1,2,3,4]
 * 输出：[1,2,4]
 * 
 * 输入：head = [2,1]
 * 输出：[2]
 * 
 * 链表中节点的数目在范围 [1, 10^5] 内
 * 1 <= Node.val <= 10^5
 */

/**
 * 一句话题解：
 * 删除链表中间节点，需保存中间节点的前驱节点。
 * 
 */



class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode deleteMiddle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode p = head;
        ListNode midPrev = null;
        while (p != null && p.next != null) {
            midPrev = (midPrev == null) ? p : midPrev.next;
            p = p.next.next;
        }
        midPrev.next = midPrev.next.next;
        return head;
    }
}
