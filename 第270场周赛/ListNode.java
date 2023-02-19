package 第270场周赛;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class LinkListOperation {
    
    /**
     * 链表的中间节点
     * 长度为 n 链表的中间节点是从头数起第 ⌊n / 2⌋ 个节点（下标从 0 开始）。
     * @param head
     * @return the middle of the linklist
     */
    public ListNode partition(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 反转链表（非递归）
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode p = head;
        ListNode q;
        ListNode pre = null;
        if(p == null) return head;
        while(p!= null){
            q = p.next;
            p.next = pre;
            pre = p;
            p = q;
        } 
        return pre;
    }

    /**
     * 反转链表（递归）
     * @param head
     * @return
     */
    public ListNode reverseListRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseListRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
