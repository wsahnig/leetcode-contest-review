package 第271场周赛;

/**
 * https://leetcode.cn/problems/maximum-fruits-harvested-after-at-most-k-steps/description/
 * 
 * 摘水果
 * 在一个无限的 x 坐标轴上，有许多水果分布在其中某些位置。
 * 给你一个二维整数数组 fruits ，其中 fruits[i] = [positioni, amounti] 表示共有 amounti 个水果放置在 positioni 上。
 * fruits 已经按 positioni 升序排列 ，每个 positioni 互不相同 。
 * 另给你两个整数 startPos 和 k 。
 * 最初，你位于 startPos 。从任何位置，你可以选择 向左或者向右 走。
 * 在 x 轴上每移动 一个单位 ，就记作 一步 。
 * 你总共可以走 最多 k 步。
 * 你每达到一个位置，都会摘掉全部的水果，水果也将从该位置消失（不会再生）。
 * 返回你可以摘到水果的 最大总数 。
 * 
 * 示例：
 * 输入：fruits = [[2,8],[6,3],[8,6]], startPos = 5, k = 4
 * 输出：9
 * 
 * 输入：fruits = [[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]], startPos = 5, k = 4
 * 输出：14
 * 
 * 输入：fruits = [[0,3],[6,4],[8,5]], startPos = 3, k = 2
 * 输出：0
 * 
 * 1 <= fruits.length <= 10^5
 * fruits[i].length == 2
 * 0 <= startPos, positioni <= 2 * 10^5
 * 对于任意 i > 0 ，positioni-1 < positioni 均成立（下标从 0 开始计数）
 * 1 <= amounti <= 10^4
 * 0 <= k <= 2 * 10^5
 */
/**
 * N句话题解:
 * 得到最大水果总数的路径只能是，一直左，一直右，先左后右，先右后左;
 * 滑动窗口 或者 前缀和+二分；
 * 滑动窗口代码更简洁，前缀和+二分，数组下标判定细节很多;
 * 对于滑动窗口：
 * left是水果的左边界,right是水果的右边界，区间路径代价大于k时，移动左边界。
 * 对于前缀和+二分：
 * 版本1：
 * 找到startPos左/右侧最近的水果位置（如果存在），左侧最远到达的位置，右侧最远到达的位置，接下来我们的讨论和查找都在这个区间进行。
 * a.先左后右或先右后左
 * 找到左侧能到达的最大区间，反向遍历区间中每个位置能到达的最右侧，如果不能到达startPos的右侧最近水果位置，结束遍历;右侧同理
 * b.一直左
 * c.一直右
 * 版本2：
 * 向一方向走x，向另一方向走k-2*x，在数组中二分查找两个端点，如果数组下标合法，则计算水果数。
 * 由于每次查找整个数组，即便是二分，效率也比较低。
 */

/**
 * 周赛中可以用前缀和+二分的题，一般也能用滑动窗口，只是得动脑仔细想滑动方法。
 */
/**
 * 前缀和+二分 版本1
 */
class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        for(int i=1; i<fruits.length; i++) {
            fruits[i][1] += fruits[i-1][1];
        }

        int left = getRightBound(fruits, startPos, 0, fruits.length-1);
        int right = getLeftBound(fruits, startPos, 0, fruits.length-1);
        int leftMost = getLeftBound(fruits, startPos - k, 0, left);
        int rightMost = getRightBound(fruits, startPos + k, right, fruits.length-1);

        int max = 0;
        if(left != -1 && right != fruits.length) {
            for(int i = left; i >= leftMost && fruits[right][0] + startPos - 2 * fruits[i][0] <= k; i--) {
                int r = getRightBound(fruits, 2 * fruits[i][0] + k - startPos, right, rightMost);
                max = Math.max(max, fruits[r][1] - (i == 0 ? 0 : fruits[i-1][1]));
            }

            for(int i = right; i <= rightMost && 2 * fruits[i][0] - startPos - fruits[left][0] <= k; i++) {
                int l = getLeftBound(fruits, 2 * fruits[i][0] - k - startPos, leftMost, left);
                max = Math.max(max, fruits[i][1] - (l == 0 ? 0 : fruits[l-1][1]));
            }
        }
        if(right <= rightMost) { //右
            max = Math.max(max, fruits[rightMost][1] - (right == 0 ? 0 : fruits[right-1][1]));
        }
        if(left >= leftMost) { //左
            max = Math.max(max, fruits[left][1] - (leftMost == 0 ? 0 : fruits[leftMost-1][1]));
        }
        return max;
    }

    private int getLeftBound(int[][] fruits, int s, int from, int to) {
        int left = from, right = to;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(fruits[mid][0] >= s) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int getRightBound(int[][] fruits, int s, int from, int to) {
        int left = from, right = to;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(fruits[mid][0] <= s) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return right;
    }
}

/**
 * 前缀和+二分  版本2
 */
class Solution2 {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        for(int i=1; i<fruits.length; i++) {
            fruits[i][1] += fruits[i-1][1];
        }

        int max = 0;
        for(int x = k; x >= 0; x--) {
            int y = k - x * 2;

            //向左走x，右走y
            int lx = getLeftBound(fruits, startPos - x);
            int ry = getRightBound(fruits, startPos + y);
            if(ry >= lx)
            max = Math.max(fruits[ry][1] - (lx == 0 ? 0 : fruits[lx-1][1]), max);

            //向右走x,再左走y
            int rx = getRightBound(fruits, startPos + x);
            int ly = getLeftBound(fruits, startPos - y);
            if(rx >= ly)
            max = Math.max(fruits[rx][1] - (ly == 0 ? 0 : fruits[ly-1][1]), max);
        }
        return max;
    }

    private int getLeftBound(int[][] fruits, int s) {
        int left = 0, right = fruits.length-1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(fruits[mid][0] >= s) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int getRightBound(int[][] fruits, int s) {
        int left = 0, right = fruits.length-1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(fruits[mid][0] <= s) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return right;
    }
}
/**
 * 滑动窗口
 */
class Solution3 {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int max = 0;
        while(right < fruits.length) {
            sum += fruits[right][1];
            while(left <= right 
            && Math.min(Math.abs(startPos - fruits[left][0]), Math.abs(startPos - fruits[right][0]))
            + fruits[right][0] - fruits[left][0] > k) {
                sum -= fruits[left][1];
                left++;
            }
            max = Math.max(max, sum);
            right++;
        }
        return max;
    }
}