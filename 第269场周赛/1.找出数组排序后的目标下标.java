package 第269场周赛;

/**
 * 给你一个下标从 0 开始的整数数组 nums 以及一个目标元素 target 。
 * 目标下标 是一个满足 nums[i] == target 的下标 i 。
 * 将 nums 按 非递减 顺序排序后，返回由 nums 中目标下标组成的列表。
 * 如果不存在目标下标，返回一个 空 列表。返回的列表必须按 递增 顺序排列。
 * 
 * 示例：
 * 输入：nums = [1,2,5,2,3], target = 2
 * 输出：[1,2]
 * 
 * 输入：nums = [1,2,5,2,3], target = 3
 * 输出：[3]
 * 
 * 输入：nums = [1,2,5,2,3], target = 5
 * 输出：[4]
 * 
 * 输入：nums = [1,2,5,2,3], target = 4
 * 输出：[]
 * 
 * 1 <= nums.length <= 100
 * 1 <= nums[i], target <= 100
 */

/**
 * 一句话题解：
 * 排序后遍历。
 * 官方题解有个时间复杂度O(n)方法，统计小于target的数个数，等于target的数个数，输出区间。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        int[] range = searchRange(nums, target);
        List<Integer> ans = new ArrayList<>();
        if(range[0] == -1) return ans;
        for(int i=range[0]; i<=range[1]; i++) ans.add(i);
        return ans;
    }
    private int extremeInsertionIndex(int[] nums, int target, boolean left){
        int lo = 0;
        int hi = nums.length - 1;
        while(lo <= hi){
            int mid = (lo + hi) / 2;
            if(nums[mid] > target || (left && target == nums[mid])) {
                hi = mid - 1;
            }
            else {
                lo = mid + 1;
            }
        }

        return lo;
    }
    private int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1,-1};
        
        int leftIdx = extremeInsertionIndex(nums, target, true);

        if(leftIdx == nums.length || nums[leftIdx] != target){
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false) -1;

        return targetRange;
    }
}
