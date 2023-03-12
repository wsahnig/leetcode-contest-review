package 第274场周赛;

import java.util.Arrays;

/**
 * 给你一个整数 mass ，它表示一颗行星的初始质量。
 * 再给你一个整数数组 asteroids ，其中 asteroids[i] 是第 i 颗小行星的质量。
 * 你可以按 任意顺序 重新安排小行星的顺序，然后让行星跟它们发生碰撞。
 * 如果行星碰撞时的质量 大于等于 小行星的质量，那么小行星被 摧毁 ，并且行星会 获得 这颗小行星的质量。否则，行星将被摧毁。
 * 如果所有小行星 都 能被摧毁，请返回 true ，否则返回 false 。
 * 
 * 示例：
 * 输入：mass = 10, asteroids = [3,9,19,5,21]
 * 输出：true
 * 
 * 输入：mass = 5, asteroids = [4,9,23,4]
 * 输出：false
 * 
 * 1 <= mass <= 10^5
 * 1 <= asteroids.length <= 10^5
 * 1 <= asteroids[i] <= 10^5
 */

/**
 * 一句话题解:
 * 贪心，排序，总是与最小质量的行星碰撞，直到质量为0或摧毁所有行星。
 * 
 * 如果要一次就AC，需注意数据范围，把前缀和定义为long型。此题在周赛中常见。
 */

class Solution {

    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long sum = mass;
        for (int i = 0; i < asteroids.length; i++) {
            if (sum < asteroids[i])
                return false;
            sum += asteroids[i];
        }
        return true;
    }

}