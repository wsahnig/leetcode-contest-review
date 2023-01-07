package 第265场周赛;

/*
 * https://leetcode.cn/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/
 * 
 * 找出临界点之间的最小和最大距离(中等)
 * 
 * 链表中的 临界点 定义为一个 局部极大值点 或 局部极小值点 。
 * 如果当前节点的值 严格大于 前一个节点和后一个节点，那么这个节点就是一个  局部极大值点 。
 * 如果当前节点的值 严格小于 前一个节点和后一个节点，那么这个节点就是一个  局部极小值点 。
 * 注意：节点只有在同时存在前一个节点和后一个节点的情况下，才能成为一个 局部极大值点 / 极小值点 。
 * 给你一个链表 head ，返回一个长度为 2 的数组 [minDistance, maxDistance] ，
 * 其中 minDistance 是任意两个不同临界点之间的最小距离，
 * maxDistance 是任意两个不同临界点之间的最大距离。
 * 如果临界点少于两个，则返回 [-1，-1] 。
 * 
 * 示例：
 * 输入：head = [3,1]
 * 输出：[-1,-1]
 * 
 * 输入：head = [5,3,1,2,5,1,2]
 * 输出：[1,3]
 * 
 * 输入：head = [1,3,2,2,3,2,2,2,7]
 * 输出：[3,3]
 * 
 * 输入：head = [2,3,3,2]
 * 输出：[-1,-1]
 * 
 * 提示：
 * 链表中节点的数量在范围 [2, 105] 内
 * 1 <= Node.val <= 10^5
 * /

 /* 
 * 一句话题解：遍历链表，最小距离需要记录前一个临界点位置，最大距离是第一个临界点和最后一个临界点距离。
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    private final int DIS_BEYOND_LIMIT = 100002;
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode p = head;
        int index = 0;        
        int firstIndex = -1;
        int preIndex = -1;
        int min = DIS_BEYOND_LIMIT;
        int max = -1;
        
        ListNode pre = null;
        while(p != null)
        {
            if(pre != null && p.next != null) {
                int val = p.val;
                int preVal = pre.val;
                int nextVal = p.next.val;
                if((val > preVal && val > nextVal) || (val < preVal && val < nextVal)) {
                    if(firstIndex == -1) {
                        firstIndex = index;
                    }
                    else {
                        min = Math.min(index - preIndex, min);
                        max = index - firstIndex;
                    }
                    preIndex = index;
                }   
            }
            pre = p;
            p = p.next;
            index++;
        }
        
        if(min == DIS_BEYOND_LIMIT) {
            min = -1;
        }
        return new int[]{min, max};
    }
}
