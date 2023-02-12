package 第268场周赛;

/**
 * https://leetcode.cn/problems/range-frequency-queries/description/
 * 
 * 区间内查询数字的频率（中等）
 * 
 * 请你设计一个数据结构，它能求出给定子数组内一个给定值的 频率 。
 * 子数组中一个值的 频率 指的是这个子数组中这个值的出现次数。
 * 请你实现 RangeFreqQuery 类：
 * RangeFreqQuery(int[] arr) 用下标从 0 开始的整数数组 arr 构造一个类的实例。
 * int query(int left, int right, int value) 返回子数组 arr[left...right] 中 value 的 频率 。
 * 一个 子数组 指的是数组中一段连续的元素。
 * arr[left...right] 指的是 nums 中包含下标 left 和 right 在内 的中间一段连续元素。
 * 
 * 示例：
 * 输入：
 * ["RangeFreqQuery", "query", "query"]
 * [[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
 * 输出：
 * [null, 1, 2]
 * 
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i], value <= 10^4
 * 0 <= left <= right < arr.length
 * 调用 query 不超过 10^5 次。
 */

/**
 * 一句话题解：
 * 每次查询，暴力搜索区间O(n)时间复杂度提交到leetcode会超时;
 * 尝试二分搜索;
 * 构造一个数出现位置索引的有序列表，搜索left/大于left的最小值和right/小于right的最大值在列表的位置;
 * 区间范围元素个数等于pos(right) - pos(left) + 1
 * 
 * 可以分开求大于等于key的最小值 ， 小于等于key的最大值 ， 这题给出了将两者合一的模板。
 * 合一的原理：
 * 小于key的最大值索引 = 大于key的最小值索引 - 1 （不存在等于key的值）
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RangeFreqQuery {
    Map<Integer, List<Integer>> map;

    public RangeFreqQuery(int[] arr) {
        map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i]))
                map.put(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }
    }

    public int query(int left, int right, int value) {

        if (!map.containsKey(value))
            return 0;
        List<Integer> list = map.get(value);
        int s = binarySearch(list, left);
        int e = binarySearch(list, right);

        if (s < 0)
            s = -s - 1; // 大于left的最小值索引
        if (e < 0)
            e = -e - 2; // 小于right的最大值索引
        return e - s + 1;
    }

    private int binarySearch(List<Integer> list, int key) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int cmp = list.get(mid).compareTo(key);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else
                return mid;
        }
        return -(low + 1);// 如果找不到，返回大于key的最小索引low。对这个索引进行处理，取相反数。但为了区分0这种不知是否找到的情况，再减1。
    }
}
