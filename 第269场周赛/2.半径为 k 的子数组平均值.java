package 第269场周赛;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/k-radius-subarray-averages/
 * 
 * 半径为 k 的子数组平均值（中等）
 * 给你一个下标从 0 开始的数组 nums ，数组中有 n 个整数，另给你一个整数 k 。
 * 半径为 k 的子数组平均值 是指：nums 中一个以下标 i 为 中心 且 半径 为 k 的子数组中所有元素的平均值，
 * 即下标在 i - k 和 i + k 范围（含 i - k 和 i + k）内所有元素的平均值。
 * 如果在下标 i 前或后不足 k 个元素，那么 半径为 k 的子数组平均值 是 -1 。
 * 构建并返回一个长度为 n 的数组 avgs ，其中 avgs[i] 是以下标 i 为中心的子数组的 半径为 k 的子数组平均值 。
 * x 个元素的 平均值 是 x 个元素相加之和除以 x ，此时使用截断式 整数除法 ，即需要去掉结果的小数部分。
 * 例如，四个元素 2、3、1 和 5 的平均值是 (2 + 3 + 1 + 5) / 4 = 11 / 4 = 2.75，截断后得到 2 。
 * 
 * 示例:
 * 输入：nums = [7,4,3,9,1,8,5,2,6], k = 3
 * 输出：[-1,-1,-1,5,4,4,-1,-1,-1]
 * 
 * 输入：nums = [100000], k = 0
 * 输出：[100000]
 * 
 * 输入：nums = [8], k = 100000
 * 输出：[-1]
 * 
 * n == nums.length
 * 1 <= n <= 10^5
 * 0 <= nums[i], k <= 10^5
 */

/**
 * 一句话题解：
 * 数组题，注意数组索引;
 * 维护一个长为2*k+1的滑动窗口，快速计算区间和，区间和使用long型整数。
 */

 class Solution {
    public int[] getAverages(int[] nums, int k) {
        int len = nums.length;
        int[] ans = new int[len];
        Arrays.fill(ans, -1);
        int diameter = 2*k+1;
        long sum = 0;
        if(diameter > len) return ans;
        for(int i=0; i<diameter; i++)
        {
            sum += nums[i];
        }
        ans[k] = (int)(sum / diameter);
        
        for(int i=k+1; i<len-k; i++)
        {
            sum -= nums[i-k-1];
            sum += nums[i+k];
            ans[i] = (int)(sum / diameter);
        }
        return ans;
    }
}
