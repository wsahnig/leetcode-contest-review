package 第67场双周赛;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/find-subsequence-of-length-k-with-the-largest-sum/description/
 * 
 * 找到和最大的长度为 K 的子序列（简单）
 * 
 * 给你一个整数数组 nums 和一个整数 k 。你需要找到 nums 中长度为 k 的 子序列 ，且这个子序列的 和最大 。
 * 请你返回 任意 一个长度为 k 的整数子序列。
 * 子序列 定义为从一个数组里删除一些元素后，不改变剩下元素的顺序得到的数组。
 * 
 * 示例：
 * 输入：nums = [2,1,3,3], k = 2
 * 输出：[3,3]
 * 
 * 输入：nums = [-1,-2,3,4], k = 3
 * 输出：[-1,3,4]
 * 
 * 输入：nums = [3,4,3,3], k = 2
 * 输出：[3,4]
 * 
 * 1 <= nums.length <= 1000
 * -10^5 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 */

/**
 * 一句话题解：
 * 一维数组转化为二维数组，二维数组的每个元素为[nums[i], i];
 * 排序二维数组，按照数值从大到小;
 * 再次排序前k个元素，按照i从小到大
 */
 class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int[][] arr = new int[nums.length][2];
        for(int i=0; i<nums.length; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        Arrays.sort(arr, 0, k, (a, b) -> a[1] - b[1]);
        int[] ans = new int[k];
        for(int i=0; i<k; i++) {
            ans[i] = arr[i][0];
        }
        return ans;
    }
}

