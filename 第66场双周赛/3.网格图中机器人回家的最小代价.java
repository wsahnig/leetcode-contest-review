package 第66场双周赛;

/**
 * https://leetcode.cn/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/
 * 
 * 网格图中机器人回家的最小代价（中等）
 * 
 * 给你一个 m x n 的网格图，其中 (0, 0) 是最左上角的格子，(m - 1, n - 1) 是最右下角的格子。
 * 给你一个整数数组 startPos ，startPos = [startrow, startcol] 表示 初始 有一个 机器人 在格子 (startrow, startcol) 处。
 * 同时给你一个整数数组 homePos ，homePos = [homerow, homecol] 表示机器人的 家 在格子 (homerow, homecol) 处。
 * 机器人需要回家。
 * 每一步它可以往四个方向移动：上，下，左，右，同时机器人不能移出边界。
 * 每一步移动都有一定代价。
 * 再给你两个下标从 0 开始的额整数数组：长度为 m 的数组 rowCosts  和长度为 n 的数组 colCosts 。
 * 如果机器人往 上 或者往 下 移动到第 r 行 的格子，那么代价为 rowCosts[r] 。
 * 如果机器人往 左 或者往 右 移动到第 c 列 的格子，那么代价为 colCosts[c] 。
 * 请你返回机器人回家需要的 最小总代价 。
 * 
 * 示例：
 * 输入：startPos = [1, 0], homePos = [2, 3], rowCosts = [5, 4, 3], colCosts = [8, 2, 6, 7]
 * 输出：18
 * 
 * 输入：startPos = [0, 0], homePos = [0, 0], rowCosts = [5], colCosts = [26]
 * 输出：0
 * 解释：机器人已经在家了，所以不需要移动。总代价为 0 。
 * 
 * m == rowCosts.length
 * n == colCosts.length
 * 1 <= m, n <= 10^5
 * 0 <= rowCosts[r], colCosts[c] <= 10^4
 * startPos.length == 2
 * homePos.length == 2
 * 0 <= startrow, homerow < m
 * 0 <= startcol, homecol < n
 */

/**
 * 一句话题解：
 * 脑筋急转弯
 * 最小代价 = 行移动距离代价 + 列移动距离代价。
 * 只要不绕弯路，与移动到行/列的顺序无关。
 */

class Solution {
    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int x0 = startPos[0];
        int y0 = startPos[1];
        int x1 = homePos[0];
        int y1 = homePos[1];
        int cost = 0;
        if (x0 < x1)
            for (int i = x0 + 1; i <= x1; i++) {
                cost += rowCosts[i];
            }
        else if (x0 > x1)
            for (int i = x0 - 1; i >= x1; i--) {
                cost += rowCosts[i];
            }

        if (y0 < y1)
            for (int i = y0 + 1; i <= y1; i++) {
                cost += colCosts[i];
            }
        else if (y0 > y1)
            for (int i = y0 - 1; i >= y1; i--) {
                cost += colCosts[i];
            }

        return cost;
    }
}