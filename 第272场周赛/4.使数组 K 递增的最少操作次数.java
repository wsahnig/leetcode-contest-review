package 第272场周赛;

/**
 * https://leetcode.cn/problems/minimum-operations-to-make-the-array-k-increasing/description/
 * 
 * 使数组 K 递增的最少操作次数(困难)
 * 
 * 给你一个下标从 0 开始包含 n 个正整数的数组 arr ，和一个正整数 k 。
 * 如果对于每个满足 k <= i <= n-1 的下标 i ，都有 arr[i-k] <= arr[i] ，那么我们称 arr 是 K 递增 的。
 * 比方说，arr = [4, 1, 5, 2, 6, 2] 对于 k = 2 是 K 递增的，因为：
 * arr[0] <= arr[2] (4 <= 5)
 * arr[1] <= arr[3] (1 <= 2)
 * arr[2] <= arr[4] (5 <= 6)
 * arr[3] <= arr[5] (2 <= 2)
 * 但是，相同的数组 arr 对于 k = 1 不是 K 递增的（因为 arr[0] > arr[1]），对于 k = 3 也不是 K 递增的（因为 arr[0] > arr[3] ）。
 * 每一次 操作 中，你可以选择一个下标 i 并将 arr[i] 改成任意 正整数。
 * 请你返回对于给定的 k ，使数组变成 K 递增的 最少操作次数 。
 * 
 * 示例：
 * 输入：arr = [5,4,3,2,1], k = 1
 * 输出：4
 * 
 * 输入：arr = [4,1,5,2,6,2], k = 2
 * 输出：0
 * 
 * 输入：arr = [4,1,5,2,6,2], k = 3
 * 输出：2
 * 
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i], k <= arr.length
 */

/**
 * N句话题解：
 * 求解最长非递减子序列的算法（动态规划、二分、贪心）：
 * 维护一个非递减的数组，len为有效长度;
 * 对于x，数组中查找大于x的最小索引，如果存在则替换为x，如果不存在则数组末尾添加x，len递增;
 * 最终len是最长非递减子序列的长度。
 * 
 * 将数组分为k组，每组包含步数为k的子序列，对于每个组，最少操作次数为组中元素个数减去每组的最长非递减子序列长度。
 */

class Solution {
    public int kIncreasing(int[] arr, int k) {
        int len = arr.length;
        int mod = len % k;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            int dpLen = len / k + (i < mod ? 1 : 0);
            int[] dp = new int[dpLen];
            int curLen = 0;
            for (int j = i; j < len; j += k) {
                int index = searchLeftBound(dp, 0, curLen, arr[j]);// 大于arr[j]最小的索引
                dp[index] = arr[j];
                if (index == curLen)
                    curLen++;
            }
            sum += (dpLen - curLen);
        }
        return sum;
    }

    private int searchLeftBound(int[] a, int fromIndex, int toIndex,
            int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (a[mid] > key)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return low;
    }
}