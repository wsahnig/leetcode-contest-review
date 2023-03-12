package 第273场周赛;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/intervals-between-identical-elements/
 * 
 * 相同元素的间隔之和（中等）
 * 给你一个下标从 0 开始、由 n 个整数组成的数组 arr 。
 * arr 中两个元素的 间隔 定义为它们下标之间的 绝对差 。更正式地，arr[i] 和 arr[j] 之间的间隔是 |i - j| 。
 * 返回一个长度为 n 的数组 intervals ，其中 intervals[i] 是 arr[i] 和 arr 中每个相同元素（与 arr[i] 的值相同）的 间隔之和 。
 * 注意：|x| 是 x 的绝对值。
 * 
 * 示例：
 * 输入：arr = [2,1,3,1,2,3,3]
 * 输出：[4,2,7,2,4,4,5]
 * 
 * 输入：arr = [10,5,10,10]
 * 输出：[5,0,3,4]
 * 
 * n == arr.length
 * 1 <= n <= 10^5
 * 1 <= arr[i] <= 10^5
 */

/**
 * 一句话题解：
 * 保存元素出现所有索引;
 * 每个位置的间隔之和，可以从前一个位置的间隔之和推导得到：对于i（i>0），sum(i) = sum(i-1) + (index[i]-index[i-1]) * ((i - 1) - (len - i - 1));
 */

class Solution {
    public long[] getDistances(int[] arr) {
        int n = arr.length;

        Map<Integer, List<Integer>> hm = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            List<Integer> indexes = hm.get(num);
            if (indexes == null) {
                indexes = new ArrayList<>();
                hm.put(num, indexes);
            }
            indexes.add(i);
        }

        long[] ans = new long[n];
        for (Integer x : hm.keySet()) {
            List<Integer> indexes = hm.get(x);
            long sum = 0;
            for (int i = 1; i < indexes.size(); i++)
                sum += indexes.get(i) - indexes.get(0);
            ans[indexes.get(0)] = sum;
            for (int i = 1; i < indexes.size(); i++) {
                long diff = indexes.get(i) - indexes.get(i - 1);
                int index = indexes.get(i);
                sum += (2 * i - indexes.size()) * diff;
                ans[index] = sum;
            }
        }
        return ans;
    }
}