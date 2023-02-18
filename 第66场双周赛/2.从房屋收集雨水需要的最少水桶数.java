package 第66场双周赛;

/**
 * https://leetcode.cn/problems/minimum-number-of-food-buckets-to-feed-the-hamsters/
 * 
 * 从房屋收集雨水需要的最少水桶数(中等)
 * 
 * 给你一个下标从 0 开始的字符串 street 。street 中每个字符要么是表示房屋的 'H' ，要么是表示空位的 '.' 。
 * 你可以在 空位 放置水桶，从相邻的房屋收集雨水。
 * 位置在 i - 1 或者 i + 1 的水桶可以收集位置为 i 处房屋的雨水。一个水桶如果相邻两个位置都有房屋，那么它可以收集 两个 房屋的雨水。
 * 在确保 每个 房屋旁边都 至少 有一个水桶的前提下，请你返回需要的 最少 水桶数。如果无解请返回 -1 。
 * 
 * 示例：
 * 输入：street = "H..H"
 * 输出：2
 * 
 * 输入：street = ".H.H."
 * 输出：1
 * 
 * 输入：street = ".HHH."
 * 输出：-1
 * 
 * 输入：street = "H"
 * 输出：-1
 * 
 * 输入：street = "."
 * 输出：0
 * 
 * 1 <= street.length <= 105
 * street[i] 要么是 'H' ，要么是 '.' 。
 */

/**
 * 一句话题解：
 * 贪心
 * 优先将水桶放在房屋的右侧第1个位置，那么右侧第2个位置无论是否有房屋，雨水都能接住;
 * 如果无法放右侧，放左侧;
 * 如果左右侧都没有空闲位置，无解。
 * 
 */
class Solution {
    public int minimumBuckets(String hamsters) {
        int cnt = 0;
        for(int i=0; i<hamsters.length(); i++) {
            if(hamsters.charAt(i) == 'H') {
                if(i < hamsters.length() - 1 && hamsters.charAt(i+1) == '.') {
                    cnt++;
                    i += 2;
                }
                else if(i > 0 && hamsters.charAt(i-1) == '.') {
                    cnt++;
                }
                else {
                    return -1;
                }
            }
        }
        return cnt;
    }
}