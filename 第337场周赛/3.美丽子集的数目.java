package 第337场周赛;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.cn/contest/weekly-contest-337/problems/the-number-of-beautiful-subsets/
 * 
 * 美丽子集的数目（中等）
 * 
 * 给你一个由正整数组成的数组 nums 和一个 正 整数 k 。
 * 
 * 如果 nums 的子集中，任意两个整数的绝对差均不等于 k ，则认为该子数组是一个 美丽 子集。
 * 
 * 返回数组 nums 中 非空 且 美丽 的子集数目。
 * 
 * nums 的子集定义为：可以经由 nums 删除某些元素（也可能不删除）得到的一个数组。
 * 只有在删除元素时选择的索引不同的情况下，两个子集才会被视作是不同的子集。
 * 
 * 示例：
 * 输入：nums = [2,4,6], k = 2
 * 输出：4
 * 
 * 输入：nums = [1], k = 1
 * 输出：1
 * 
 * 1 <= nums.length <= 20
 * 1 <= nums[i], k <= 1000
 * 
 */

/**
 * 一句话题解：
 * 状态压缩超时，数学统计没思路，数组长度20只能回溯了。
 * 要懂得状态压缩和回溯的区别。
 */

 class Solution {
    int ans = 0;
    
    public int beautifulSubsets(int[] nums, int k) {
        ans = 0;
        dfs(nums, 0, new ArrayList<>(), k);
        return ans;    
    }
    
    private void dfs(int[] nums, int cur, List<Integer> list, int k) {
        if(list.size() > 0) ans++;
        for(int i = cur; i < nums.length; i++) {
            boolean flag = true;
            for(int num : list) {
                if(num - nums[i] == k || nums[i] - num == k) {
                    flag = false;
                    break;
                }
            }
            if(flag){
                list.add(nums[i]);
                dfs(nums, i + 1, list, k);
                list.remove(list.size()-1);
            }
        }
    }
}

/**
 * 超时代码
 * 算法超时原因：会有很多重复判断比如：
 * 1 2 3 4、1 2 3的公共子集都是1 2，但是回溯如果1 2不是美丽的，则不会继续判断前两个子集。
 */
class Solution2 {
    public int beautifulSubsets(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for(int i = 1; i < (1 << n); i++) {
            Set<Integer> hs = new HashSet<>();
            boolean flag = true;
            for(int j = 0; j < n; j++) {
                if((1 & (i >> j)) == 1) {
                    hs.add(nums[j]);
                    if(hs.contains(nums[j] + k) || hs.contains(nums[j] - k)) {
                        flag = false;
                        break;
                    }
                }
            }
            if(flag) ans++;
        }
        return ans;
    }
}