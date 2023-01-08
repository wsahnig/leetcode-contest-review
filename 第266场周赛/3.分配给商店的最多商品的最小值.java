package 第266场周赛;

/**
 * https://leetcode.cn/problems/minimized-maximum-of-products-distributed-to-any-store/
 * 
 * 分配给商店的最多商品的最小值(中等)
 * 
 * 给你一个整数 n ，表示有 n 间零售商店。
 * 总共有 m 种产品，每种产品的数目用一个下标从 0 开始的整数数组 quantities 表示，其中 quantities[i] 表示第 i 种商品的数目。
 * 你需要将 所有商品 分配到零售商店，并遵守这些规则：
 * 一间商店 至多 只能有 一种商品 ，但一间商店拥有的商品数目可以为 任意 件。
 * 分配后，每间商店都会被分配一定数目的商品（可能为 0 件）。
 * 用 x 表示所有商店中分配商品数目的最大值，你希望 x 越小越好。也就是说，你想 最小化 分配给任意商店商品数目的 最大值 。
 * 请你返回最小的可能的 x 。
 * 
 * 示例：
 * 输入：n = 6, quantities = [11,6]
 * 输出：3
 * 
 * 输入：n = 7, quantities = [15,10,10]
 * 输出：5
 * 
 * 输入：n = 1, quantities = [100000]
 * 输出：100000
 * 
 * 提示：
 * m == quantities.length
 * 1 <= m <= n <= 10^5
 * 1 <= quantities[i] <= 10^5
 */

/**
 * 一句话题解： 二分应用题；
 * 假设总是分配最大数目的商品k，分配完所有的商品，需要分给N个商店，只要N小于等于实际商店数n，那么k满足要求；
 * 对于所有大于k，也一定满足要求；
 * 尽可能使得k最小，二分搜索。
 * 
 */

class Solution {
    public int minimizedMaximum(int n, int[] quantities) {
        int left = 100001,right = 0;
        for(int x: quantities) {
            if(x < left) left = x;
            if(x > right) right = x;
        }
        left = Math.max(1, left/n);
        
        while(left <= right) {
            int mid = (left + right) / 2;
            if(canDistribute(mid, n, quantities)) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private boolean canDistribute(int k, int n, int[] quantities) {
        int cnt = 0;
        for(int x: quantities) {
            cnt += (x + k - 1) / k;
        }
        return cnt <= n;
    }
}
