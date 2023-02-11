package 第267场周赛;

/**
 * https://leetcode.cn/problems/reverse-nodes-in-even-length-groups/
 * 
 * 反转偶数长度组的节点(中等)
 * 
 * 给你一个链表的头节点 head 。
 * 链表中的节点 按顺序 划分成若干 非空 组，这些非空组的长度构成一个自然数序列（1, 2, 3, 4, ...）。
 * 一个组的 长度 就是组中分配到的节点数目。换句话说：
 * 节点 1 分配给第一组
 * 节点 2 和 3 分配给第二组
 * 节点 4、5 和 6 分配给第三组，以此类推
 * 注意，最后一组的长度可能小于或者等于 1 + 倒数第二组的长度 。
 * 反转 每个 偶数 长度组中的节点，并返回修改后链表的头节点 head 。
 * 
 * 示例：
 * 输入：head = [5,2,6,3,9,1,7,3,8,4]
 * 输出：[5,6,2,3,9,1,4,8,3,7]
 * 
 * 输入：head = [1,1,0,6]
 * 输出：[1,0,1,6]
 * 
 * 输入：head = [2,1]
 * 输出：[2,1]
 * 
 * 链表中节点数目范围是 [1, 10^5]
 * 0 <= Node.val <= 10^5
 */

/**
 * 一句话题解：
 * 还是反转链表;
 * 链接反转后的组，链表操作需细心;
 * 还要先求出链表长度，注意读题意，倒数第二组长度为偶数，最后一组长度可能也为偶数，这时需要反转这一组。
 */
class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode reverseEvenLengthGroups(ListNode head) {
        int len = 0;
        ListNode p = head;
        while(p != null) {
            p = p.next;
            len++;
        }
        int groupIndex = 1;
        p = head;
        ListNode pre = null;
        while(p != null && p.next != null) {
            int groupL = Math.min(len, groupIndex);
            if(groupL % 2 != 0) {
                for(int i=0; i<groupL && p != null; i++) {
                    pre = p;
                    p = p.next;
                }
            } else {
                ListNode groupPre = pre;
                ListNode newGroupHead = reverse(p, groupL);
                pre = p;
                p = p.next;
                groupPre.next = newGroupHead;
            }
            len -= groupIndex;
            groupIndex++;
        }
        return head;
    }

    private ListNode reverse(ListNode head, int k) {
        ListNode pre = null, cur = head;
        while(k-- > 0 && cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head.next = cur;
        return pre;
    }
}
