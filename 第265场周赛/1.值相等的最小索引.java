package 第265场周赛;

/*
 * https://leetcode.cn/problems/smallest-index-with-equal-value/
 * 
 * 值相等的最小索引(简单)
 * 
 * 给你一个下标从 0 开始的整数数组 nums ，返回 nums 中满足 i mod 10 == nums[i] 的最小下标 i ；
 * 如果不存在这样的下标，返回 -1 。
 * x mod y 表示 x 除以 y 的 余数 。
 * 
 * 
 * 示例：
 * 输入：nums = [0,1,2]
 * 输出：0
 * 
 * 输入：nums = [4,3,2,1]
 * 输出：2
 * 
 * 输入：nums = [1,2,3,4,5,6,7,8,9,0]
 * 输出：-1
 * 
 * 输入：nums = [2,1,3,5,2]
 * 输出：1
 * 
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 9
 * /

 /* 
 * 
 * 一句话题解：顺序遍历数组，第一个值相等的索引。
 */

class Solution {
    public int smallestEqual(int[] nums) {
        int len = nums.length;
        for(int i=0; i<len; i++)
        {
            if(i % 10 == nums[i]) return i;
        }
       return -1;
    }
}