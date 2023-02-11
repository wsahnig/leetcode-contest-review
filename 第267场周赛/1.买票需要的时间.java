package 第267场周赛;

/**
 * https://leetcode.cn/problems/time-needed-to-buy-tickets/
 * 
 * 买票需要的时间（简单）
 * 有 n 个人前来排队买票，其中第 0 人站在队伍 最前方 ，第 (n - 1) 人站在队伍 最后方 。
 * 给你一个下标从 0 开始的整数数组 tickets ，数组长度为 n ，其中第 i 人想要购买的票数为 tickets[i] 。
 * 每个人买票都需要用掉 恰好 1 秒 。
 * 一个人 一次只能买一张票 ，如果需要购买更多票，他必须走到  队尾 重新排队（瞬间 发生，不计时间）。
 * 如果一个人没有剩下需要买的票，那他将会 离开 队伍。
 * 返回位于位置 k（下标从 0 开始）的人完成买票需要的时间（以秒为单位）。
 * 
 * 示例 ：
 * 输入：tickets = [2,3,2], k = 2
 * 输出：6
 * 
 * 输入：tickets = [5,1,2,2], k = 2
 * 输出：6
 */

/**
 * 一句话题解：
 * 脑筋急转弯，k位置自身及之前的需要时间Math.min(tickets[k], tickets[i])，
 * k位置后的需要时间Math.min(tickets[k]-1, tickets[i])
 * 
 */
class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int sum = 0;
        for(int i=0; i<tickets.length; i++) {
            sum += Math.min(tickets[k]-1, tickets[i]);
        }
        for(int i=0; i<=k; i++) {
            if(tickets[i] >= tickets[k]) sum++;
        }
        return sum;
    }
}
