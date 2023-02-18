package 第66场双周赛;

/**
 * https://leetcode.cn/problems/count-fertile-pyramids-in-a-land/
 * 
 * 统计农场中肥沃金字塔的数目(困难)
 * 
 * 有一个 矩形网格 状的农场，划分为 m 行 n 列的单元格。
 * 每个格子要么是 肥沃的 （用 1 表示），要么是 贫瘠 的（用 0 表示）。
 * 网格图以外的所有与格子都视为贫瘠的。
 * 农场中的 金字塔 区域定义如下：
 * 区域内格子数目 大于 1 且所有格子都是 肥沃的 。
 * 金字塔 顶端 是这个金字塔 最上方 的格子。金字塔的高度是它所覆盖的行数。
 * 令 (r, c) 为金字塔的顶端且高度为 h ，那么金字塔区域内包含的任一格子 (i, j) 需满足 r <= i <= r + h - 1 且 c - (i - r) <= j <= c + (i - r) 。
 * 一个 倒金字塔 类似定义如下：
 * 区域内格子数目 大于 1 且所有格子都是 肥沃的 。
 * 倒金字塔的 顶端 是这个倒金字塔 最下方 的格子。
 * 倒金字塔的高度是它所覆盖的行数。
 * 令 (r, c) 为金字塔的顶端且高度为 h ，那么金字塔区域内包含的任一格子 (i, j) 需满足 r - h + 1 <= i <= r 且 c - (r - i) <= j <= c + (r - i) 。
 * 下图展示了部分符合定义和不符合定义的金字塔区域。黑色区域表示肥沃的格子。
 * https://assets.leetcode.com/uploads/2021/11/08/image.png
 * 
 * 给你一个下标从 0 开始且大小为 m x n 的二进制矩阵 grid ，它表示农场，
 * 请你返回 grid 中金字塔和倒金字塔的 总数目
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 1000
 * 1 <= m * n <= 10^5
 * grid[i][j] 要么是 0 ，要么是 1 。
 */

/**
 * 一句话题解：
 * dp[i][j]表示以(i, j)为顶端的金字塔的高度h
 * 考虑金字塔，如果(i, j)肥沃，且（i+1, j) 、 （i+1, j-1) 和（i+1, j+1)位置存在且都肥沃，那么,
 * dp[i][j] = 1 + min(dp[i - 1][j - 1], dp[i - 1][j], dp[i - 1][j + 1]),
 * 金字塔个数增加h。
 * 由于i由i-1推导，所以求金字塔时，要从最后一行遍历到第一行。
 * 倒金字塔同理相反。
 */

class Solution {
    public int countPyramids(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        int inversePyramidal = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1 && i - 1 >= 0 && j + 1 < grid[i].length && j - 1 >= 0 && grid[i - 1][j - 1] == 1
                        && grid[i - 1][j] == 1 && grid[i - 1][j + 1] == 1) {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j + 1]));
                    inversePyramidal += dp[i][j];
                }
            }
        }
        int pyramidal = 0;
        dp = new int[grid.length][grid[0].length];
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[i].length - 1; j >= 0; j--) {
                if (grid[i][j] == 1 && i + 1 < grid.length && j + 1 < grid[i].length && j - 1 >= 0
                        && grid[i + 1][j - 1] == 1 && grid[i + 1][j] == 1 && grid[i + 1][j + 1] == 1) {
                    dp[i][j] = 1 + Math.min(dp[i + 1][j - 1], Math.min(dp[i + 1][j], dp[i + 1][j + 1]));
                    pyramidal += dp[i][j];
                }
            }
        }

        return inversePyramidal + pyramidal;
    }
}