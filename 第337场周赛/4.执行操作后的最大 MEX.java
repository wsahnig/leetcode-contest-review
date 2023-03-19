package 第337场周赛;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/contest/weekly-contest-337/problems/smallest-missing-non-negative-integer-after-operations/
 * 
 * 执行操作后的最大 MEX(中等)
 * 
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 value 。
 * 
 * 在一步操作中，你可以对 nums 中的任一元素加上或减去 value 。
 * 例如，如果 nums = [1,2,3] 且 value = 2 ，你可以选择 nums[0] 减去 value ，得到 nums = [-1,2,3] 。
 * 数组的 MEX (minimum excluded) 是指其中数组中缺失的最小非负整数。
 * 
 * 例如，[-1,2,3] 的 MEX 是 0 ，而 [1,0,3] 的 MEX 是 2 。
 * 返回在执行上述操作 任意次 后，nums 的最大 MEX 。
 * 
 * 示例：
 * 输入：nums = [1,-10,7,13,6,8], value = 5
 * 输出：4
 * 
 * 输入：nums = [1,-10,7,13,6,8], value = 7
 * 输出：2
 * 
 * 1 <= nums.length, value <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */

/**
 * 一句话题解：
 * 按余数分类，能表示的范围是 余数 -> 余数 + value的倍数， value的倍数是 （余数的个数-1）
 * 从0开始遍历到100000，找缺失的最小非负整数。
 */

class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        Map<Integer, Integer> hm = new HashMap<>();
        for(int num : nums) {
            int yushu = (num % value + value) % value;
            hm.put(yushu, hm.getOrDefault(yushu, 0) + 1);
        }
        int i = 0;
        while(i < 100000) {
            int yushu = (i % value + value) % value;
            int cnt = hm.getOrDefault(yushu, 0);
            if((cnt-1) * value + yushu < i) return i;
            i++;
        }
        return i;
    }
}