/**
 * https://leetcode.cn/problems/most-beautiful-item-for-each-query/
 * 
 * 每一个查询的最大美丽值（中等）
 * 给你一个二维整数数组 items ，其中 items[i] = [pricei, beautyi] 分别表示每一个物品的 价格 和 美丽值 。
 * 同时给你一个下标从 0 开始的整数数组 queries 。
 * 对于每个查询 queries[j] ，你想求出价格小于等于 queries[j] 的物品中，最大的美丽值 是多少。
 * 如果不存在符合条件的物品，那么查询的结果为 0 。
 * 请你返回一个长度与 queries 相同的数组 answer，其中 answer[j]是第 j 个查询的答案。
 * 
 * 
 * 示例 ：
 * 输入：items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
 * 输出：[2,4,5,5,6,6]
 * 
 * 输入：items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
 * 输出：[4]
 * 
 * 输入：items = [[10,1000]], queries = [5]
 * 输出：[0]
 * 
 * 提示：
 * 1 <= items.length, queries.length <= 10^5
 * items[i].length == 2
 * 1 <= pricei, beautyi, queries[j] <= 10^9
 */

/**
 * 
 * 一句话题解：
 * 排序物品,按照价格从小到大,价格相同按照美丽值从小到大;
 * 将物品美丽值，更新为小于等于物品价格的最大美丽值;
 * 对于每个查询价格，二分搜索前两步处理后的物品数组。
 * 
 */

import java.util.Arrays;

class Solution {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, (a, b) -> {
            if(a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });
        int maxBeauty = 0;
        for(int[] item : items) {
            maxBeauty = Math.max(maxBeauty, item[1]);
            item[1] = maxBeauty;
        }
        int[] ans = new int[queries.length];
        for(int i=0; i < queries.length; i++) {
            ans[i] = binarySearch(items, queries[i]);
        }
        return ans;
    }

    private int binarySearch(int[][] items, int price) {
        if(items[0][0] > price) return 0;
        int left = 0, right = items.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(items[mid][0] <= price) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return items[right][1];
    }
}
