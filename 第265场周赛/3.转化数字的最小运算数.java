package 第265场周赛;

import java.util.*;

/*
 * https://leetcode.cn/problems/minimum-operations-to-convert-number/
 * 
 * 转化数字的最小运算数(中等)
 * 
 * 给你一个下标从 0 开始的整数数组 nums ，该数组由 互不相同 的数字组成。另给你两个整数 start 和 goal 。
 * 整数 x 的值最开始设为 start ，你打算执行一些运算使 x 转化为 goal 。你可以对数字 x 重复执行下述运算：
 * 如果 0 <= x <= 1000 ，那么，对于数组中的任一下标 i（0 <= i < nums.length），可以将 x 设为下述任一值：
 * x + nums[i]
 * x - nums[i]
 * x ^ nums[i]（按位异或 XOR）
 * 注意，你可以按任意顺序使用每个 nums[i] 任意次。
 * 使 x 越过 0 <= x <= 1000 范围的运算同样可以生效，但该该运算执行后将不能执行其他运算。
 * 返回将 x = start 转化为 goal 的最小操作数；如果无法完成转化，则返回 -1 。
 * 
 * 示例：
 * 输入：nums = [2,4,12], start = 2, goal = 12
 * 输出：2
 *
 * 输入：nums = [3,5,7], start = 0, goal = -4
 * 输出：2 
 * 
 * 输入：nums = [2,8,16], start = 0, goal = 1
 * 输出：-1
 * 
 * 提示：
 * 1 <= nums.length <= 1000
 * -109 <= nums[i], goal <= 109
 * 0 <= start <= 1000
 * start != goal
 * nums 中的所有整数互不相同
 */

 /*
  * 一句话题解： 广度优先遍历，最小转换次数是BFS的层数，注意去重和排除无效值以免超时。
  */

class Solution {
    public int minimumOperations(int[] nums, int start, int goal) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> cache = new HashSet<>();
        int cnt = 0;
        
        q.offer(start);
        cache.add(start);
        
        while(!q.isEmpty())
        {
            int size = q.size();
            cnt++;
            for(int k=0; k<size; k++)
            {
                int x = q.poll();
                   
                for(int i=0; i<nums.length; i++)
                {
                    int ans = x + nums[i];
                    if(ans == goal) return cnt;
                    if(ans >= 0 && ans <= 1000 && cache.add(ans)) q.offer(ans);
                    
                    ans = x - nums[i];
                    if(ans == goal) return cnt;
                    if(ans >= 0 && ans <= 1000 && cache.add(ans)) q.offer(ans);
                    
                    ans = x ^ nums[i];
                    if(ans == goal) return cnt;
                    if(ans >= 0 && ans <= 1000 && cache.add(ans)) q.offer(ans);
                }   
            }
            
        }
        
        return -1;
    }
}