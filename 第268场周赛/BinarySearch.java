package 第268场周赛;

import java.util.List;

class BinarySearch {
    /**
     * 查找小于等于key的最大值索引
     * @param arr
     * @param key
     * @return
     */
    public int searchRightBound(int[] arr, int key) {
        int left = 0, right = arr.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    /**
     * 查找大于等于key的最小值索引
     * @param arr
     * @param key
     * @return
     */
    public int searchLeftBound(int[] arr, int key) {
        int left = 0, right = arr.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 查找大于等于key的最小索引， 也可用于小于等于key的最大索引
     * 
     * @param list
     * @param key
     * @return
     * 
     * 使用举例：
     * int s = binarySearch(list, left);
     * int e = binarySearch(list, right);
     *
     * if (s < 0)
     * s = -s - 1; // 大于left的最小值索引
     * if (e < 0)
     * e = -e - 2; // 小于right的最大值索引
     */
    public int binarySearch(List<Integer> list, int key) {
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
        // 如果找不到，返回大于key的最小索引low。对这个索引进行处理，取相反数。
        // 为了区分0这种不知是否找到的情况，再减1。
        return -(low + 1);
    }
}
