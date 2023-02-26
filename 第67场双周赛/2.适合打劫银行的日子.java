package 第67场双周赛;

/**
 * https://leetcode.cn/problems/find-good-days-to-rob-the-bank/description/
 * 
 * 适合打劫银行的日子（中等）
 * 你和一群强盗准备打劫银行。给你一个下标从 0 开始的整数数组 security ，其中 security[i] 是第 i 天执勤警卫的数量。
 * 日子从 0 开始编号。同时给你一个整数 time 。
 * 如果第 i 天满足以下所有条件，我们称它为一个适合打劫银行的日子：
 * 第 i 天前和后都分别至少有 time 天。
 * 第 i 天前连续 time 天警卫数目都是非递增的。
 * 第 i 天后连续 time 天警卫数目都是非递减的。
 * 更正式的，第 i 天是一个合适打劫银行的日子当且仅当：
 * security[i - time] >= security[i - time + 1] >= ... >= security[i] <= ... <= security[i + time - 1] <= security[i + time].
 * 请你返回一个数组，包含 所有 适合打劫银行的日子（下标从 0 开始）。返回的日子可以 任意 顺序排列。
 * 
 * 示例：
 * 输入：security = [5,3,3,3,5,6,2], time = 2
 * 输出：[2,3]
 * 
 * 输入：security = [1,1,1,1,1], time = 0
 * 输出：[0,1,2,3,4]
 * 
 * 输入：security = [1,2,3,4,5,6], time = 2
 * 输出：[]
 * 
 * 1 <= security.length <= 10^5
 * 0 <= security[i], time <= 10^5
 */

/**
 * 一句话题解：
 * 动态规划，left(i) i位置从左到右非递增序列长度，right(i) i位置从右到左非递减序列长度;
 * 遍历每一个位置，是否适合打劫银行;
 * 时间复杂度O(n),空间复杂度O(n)
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int len = security.length;

        int[] left = new int[len];
        int[] right = new int[len];

        for (int i = 1; i < len; i++) {
            left[i] = security[i - 1] >= security[i] ? left[i - 1] + 1 : 0;
        }

        for (int i = len - 2; i >= 0; i--) {
            right[i] = security[i] <= security[i + 1] ? right[i + 1] + 1 : 0;
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (left[i] >= time && right[i] >= time)
                ans.add(i);
        }

        return ans;
    }
}
