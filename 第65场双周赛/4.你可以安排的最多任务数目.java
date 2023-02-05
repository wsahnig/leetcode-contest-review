/**
 * 
 * https://leetcode.cn/problems/maximum-number-of-tasks-you-can-assign/
 * 
 * 你可以安排的最多任务数目(困难)
 * 
 * 给你 n 个任务和 m 个工人。每个任务需要一定的力量值才能完成，需要的力量值保存在下标从 0 开始的整数数组 tasks 中，第 i 个任务需要 tasks[i] 的力量才能完成。
 * 每个工人的力量值保存在下标从 0 开始的整数数组 workers 中，第 j 个工人的力量值为 workers[j] 。
 * 每个工人只能完成 一个 任务，且力量值需要 大于等于 该任务的力量要求值（即 workers[j] >= tasks[i] ）。
 * 除此以外，你还有 pills 个神奇药丸，可以给 一个工人的力量值 增加 strength 。
 * 你可以决定给哪些工人使用药丸，但每个工人 最多 只能使用 一片 药丸。
 * 
 * 给你下标从 0 开始的整数数组tasks 和 workers 以及两个整数 pills 和 strength ，
 * 请你返回 最多 有多少个任务可以被完成。
 * 
 * 示例：
 * 输入：tasks = [3,2,1], workers = [0,3,3], pills = 1, strength = 1
 * 输出：3
 * 
 * 输入：tasks = [5,4], workers = [0,0,0], pills = 1, strength = 5
 * 输出：1
 * 
 * 输入：tasks = [10,15,30], workers = [0,10,10,10,10], pills = 3, strength = 10
 * 输出：2
 * 
 * 输入：tasks = [5,9,8,5,9], workers = [1,6,4,2,6], pills = 1, strength = 5
 * 输出：3
 * 
 * 提示：
 * n == tasks.length
 * m == workers.length
 * 1 <= n, m <= 5 * 10^4
 * 0 <= pills <= m
 * 0 <= tasks[i], workers[j], strength <= 10^9

 */

/**
 * 一句话题解：
 * 如果能完成k个任务，那么k-1个任务也一定能完成，因此可二分查找最大能完成任务数;
 * 判断能否完成k个任务：田忌赛马策略，考虑k个力量最小的任务，k个力量最大的工人；
 * 从力量值大到小枚举任务，维护了（在使用药丸的情况下）所有可以完成任务的工人，那么：
 * 如果有工人可以不使用药丸完成该任务，那么选择（删除）值最大的工人；
 * 如果所有工人都需要使用药丸才能完成该任务，那么选择（删除）值最小的工人，
 * 也就是尽量不用药丸的贪心思想……
 * 
 */
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int left = 0, right = Math.min(tasks.length, workers.length);
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(canComplete(tasks, workers, pills, strength, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    private boolean canComplete(int[] tasks, int[] workers, int pills, int strength, int k) {
        Deque<Integer> queue = new LinkedList<>();
        int i = workers.length - 1;
        for(int j = k-1; j >=0; j--) {//从大到小枚举最小的k个任务
            while(i >= workers.length - k && workers[i] + strength >= tasks[j]) {
                queue.addFirst(workers[i]);
                i--;
            }
            if(queue.isEmpty()) {
                return false;
            }
            else if(queue.peekLast() >= tasks[j]) {
                queue.pollLast();
            }
            else {
                if(pills == 0) {
                    return false;
                } 
                pills--;
                queue.pollFirst();
            }
        }
        return true;
    }
}
