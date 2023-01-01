package 第264场周赛;

/*
 * 
 * https://leetcode.cn/problems/next-greater-numerically-balanced-number/description/
 * 
 * 下一个更大的数值平衡数（中等）
 * 
 * 如果整数x满足：对于每个数位d，这个数位恰好在x中出现d次。那么整数 x 就是一个数值平衡数 。
 * 
 * 给你一个整数n，请你返回严格大于n的最小数值平衡数。
 * 
 * 示例 :
 * 输入：n = 1
 * 输出：22
 * 
 * 输入：n = 1000
 * 输出：1333
 * 
 * 输入：n = 3000
 * 输出：3133
 * 
 * 提示：
 * 0 <= n <= 10^6
 */

/*
 * 一句话题解： 看数据范围，这道中等题枚举暴力即可。子问题是判断一个数是否是数值平衡数。
 * 
 * 当然，也可以打表【1-1224444】范围内所有的数字平衡数，但不要自己去推导每一个数值平衡数，让程序帮你算。
 * 你只需用这张表二分查找严格大于n的平衡数。
 */

class Solution {
    public int nextBeautifulNumber(int n) {
        int next = n+1;
        while(!isBeautifulNumber(next)) next++;
        return next;
    }

    private boolean isBeautifulNumber(int x) {
        int[] cnt = new int[10];
        while(x != 0) {
            int remain = x % 10;
            cnt[remain]++;
            x /= 10;
            if(cnt[remain] > remain) return false;
        }
        for(int i=0; i<10; i++) {
            if(cnt[i] != 0 && i != cnt[i]) return false;
        }
        return true;
    }
}
