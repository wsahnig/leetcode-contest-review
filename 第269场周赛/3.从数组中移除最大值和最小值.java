package 第269场周赛;

/**
 * https://leetcode.cn/problems/removing-minimum-and-maximum-from-array/
 * 
 * 从数组中移除最大值和最小值(中等)
 * 
 * 给你一个下标从 0 开始的数组 nums ，数组由若干 互不相同 的整数组成。
 * nums 中有一个值最小的元素和一个值最大的元素。分别称为 最小值 和 最大值 。你的目标是从数组中移除这两个元素。
 * 一次 删除 操作定义为从数组的 前面 移除一个元素或从数组的 后面 移除一个元素。
 * 返回将数组中最小值和最大值 都 移除需要的最小删除次数。
 * 
 * 示例：
 * 输入：nums = [2,10,7,5,4,1,8,6]
 * 输出：5
 * 
 * 输入：nums = [0,-4,19,1,8,-2,-3,5]
 * 输出：3
 * 
 * 输入：nums = [101]
 * 输出：1
 * 
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 * nums 中的整数 互不相同
 */

/**
 * 一句话题解：
 * 找到最小值和最大值并记录其索引;
 * 有三种贪心删除策略：从后边删除两个值，从前边删除两个值，从前删除一个、从后删除另一个;
 * 取三者最小。
 */

 class Solution {
    public int minimumDeletions(int[] nums) {
        int min = 100001;
        int max = -100001;
        int minIdx = -1;
        int maxIdx = -1;
        int len = nums.length;
        for(int i=0; i<len; i++)
        {
            if(nums[i] < min)
            {
                 min = nums[i];
                 minIdx = i;
            }
            if(nums[i] > max)
            {
                max = nums[i];
                maxIdx = i;
            }
        }
        
        int small = Math.min(minIdx, maxIdx);
        int big = Math.max(minIdx, maxIdx);
        
        int fromBothFront = big+1;
        int fromBothBack = len - small;
        int fromOneFrontOneBack = small + 1 + len - big;
        
        return Math.min(fromBothFront, Math.min(fromBothBack, fromOneFrontOneBack));
    }
}
