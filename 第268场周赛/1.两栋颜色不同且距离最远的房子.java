package 第268场周赛;

/**
 * https://leetcode.cn/problems/two-furthest-houses-with-different-colors/description/
 * 
 * 两栋颜色不同且距离最远的房子(简单)
 * 
 * 街上有 n 栋房子整齐地排成一列，每栋房子都粉刷上了漂亮的颜色。
 * 给你一个下标从 0 开始且长度为 n 的整数数组 colors ，其中 colors[i] 表示第  i 栋房子的颜色。
 * 返回 两栋 颜色 不同 房子之间的 最大 距离。
 * 第 i 栋房子和第 j 栋房子之间的距离是 abs(i - j) ，其中 abs(x) 是 x 的绝对值。
 * 
 * 示例：
 * 输入：colors = [1,1,1,6,1,1,1]
 * 输出：3
 * 
 * 输入：colors = [1,8,3,8,3]
 * 输出：4
 * 
 * 输入：colors = [0,1]
 * 输出：1
 * 
 * n == colors.length
 * 2 <= n <= 100
 * 0 <= colors[i] <= 100
 * 生成的测试数据满足 至少 存在 2 栋颜色不同的房子
 */

/**
 * 一句话题解：
 * 数据量小，模拟暴力解法。
 */
class Solution {
    public int maxDistance(int[] colors) {
        int maxDistance = 0;
        int len = colors.length;
        for(int i = 0; i < len; i++)
        {
            for(int j = i+maxDistance+1; j < len; j++)
            {
                if(colors[i] != colors[j])
                {
                    maxDistance = Math.abs(j-i);
                }
            }
        }
        return maxDistance;
    }
}