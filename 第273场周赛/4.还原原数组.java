package 第273场周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/recover-the-original-array/
 * 
 * 还原原数组（困难）
 * Alice 有一个下标从 0 开始的数组 arr ，由 n 个正整数组成。
 * 她会选择一个任意的 正整数 k 并按下述方式创建两个下标从 0 开始的新整数数组 lower 和 higher ：
 * 对每个满足 0 <= i < n 的下标 i ，lower[i] = arr[i] - k
 * 对每个满足 0 <= i < n 的下标 i ，higher[i] = arr[i] + k
 * 不幸地是，Alice 丢失了全部三个数组。
 * 但是，她记住了在数组 lower 和 higher 中出现的整数，但不知道每个整数属于哪个数组。请你帮助 Alice 还原原数组。
 * 给你一个由 2n 个整数组成的整数数组 nums ，其中 恰好 n 个整数出现在 lower ，剩下的出现在 higher ，还原并返回 原数组 arr 。如果出现答案不唯一的情况，返回 任一 有效数组。
 * 注意：生成的测试用例保证存在 至少一个 有效数组 arr 。
 * 
 * 示例 ：
 * 输入：nums = [2,10,6,4,8,12]
 * 输出：[3,7,11]
 * 
 * 输入：nums = [1,1,3,3]
 * 输出：[2,2]
 * 
 * 输入：nums = [5,435]
 * 输出：[220]
 * 
 * 2 * n == nums.length
 * 1 <= n <= 1000
 * 1 <= nums[i] <= 10^9
 * 生成的测试用例保证存在 至少一个 有效数组 arr
 */

/**
 * 一句话题解：
 * 由于知道lower和higher数组的所有元素，可以知道所有可能为k值的数;
 * 枚举每个可能为k的值，如果恰好能将数组分为lower和higher两个数组，则符合条件;
 * 用hashmap保存每个元素出现的次数。
 * 
 * 
 * Integer列表转int数组：
 * list.stream().mapToInt(Integer::intValue).toArray();
 */

class Solution {
    public int[] recoverArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<Integer> ks = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            int k = nums[i] - nums[0];
            if (k % 2 == 0 && k > 0)
                ks.add(k / 2);
        }

        Map<Integer, Integer> numCnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            numCnt.put(nums[i], numCnt.getOrDefault(nums[i], 0) + 1);
        }

        for (Integer k : ks) {
            Map<Integer, Integer> cache = new HashMap<>(numCnt);
            List<Integer> ans = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int low = nums[i];
                int high = low + 2 * k;
                if (cache.containsKey(low) && cache.containsKey(high)) {
                    ans.add(low + k);
                    int cnt = cache.get(low);
                    if (cnt == 1)
                        cache.remove(low);
                    else
                        cache.put(low, cnt - 1);
                    cnt = cache.get(high);
                    if (cnt == 1)
                        cache.remove(high);
                    else
                        cache.put(high, cnt - 1);
                }
            }
            if (ans.size() == n / 2) {
                return ans.stream().mapToInt(Integer::intValue).toArray();
            }
        }

        return new int[n];
    }
}