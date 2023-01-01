package 第264场周赛;

import java.util.*;

/*
 * https://leetcode.cn/problems/parallel-courses-iii/
 * 
 * 
 * 并行课程 III
 * 
 * 给你一个整数 n ，表示有 n 节课，课程编号从 1 到 n 。
 * 同时给你一个二维整数数组 relations ，其中 relations[j] = [prevCoursej, nextCoursej] ，表示课程 prevCoursej 必须在课程 nextCoursej 之前 完成（先修课的关系）。
 * 同时给你一个下标从 0 开始的整数数组 time ，其中 time[i] 表示完成第 (i+1) 门课程需要花费的 月份 数。
 * 请你根据以下规则算出完成所有课程所需要的 最少 月份数：
 * 如果一门课的所有先修课都已经完成，你可以在 任意 时间开始这门课程。
 * 你可以 同时 上 任意门课程 。
 * 请你返回完成所有课程所需要的 最少 月份数。
 * 
 * 注意：测试数据保证一定可以完成所有课程（也就是先修课的关系构成一个有向无环图）。
 * 
 * 示例：
 * 输入：n = 3, relations = [[1,3],[2,3]], time = [3,2,5]
 * 输出：8
 * 
 * 输入：n = 5, relations = [[1,5],[2,5],[3,5],[3,4],[4,5]], time = [1,2,3,4,5]
 * 输出：12
 * 
 * 提示：
 * 1 <= n <= 5 * 10^4
 * 0 <= relations.length <= min(n * (n - 1) / 2, 5 * 10^4)
 * relations[j].length == 2
 * 1 <= prevCoursej, nextCoursej <= n
 * prevCoursej != nextCoursej
 * 所有的先修课程对 [prevCoursej, nextCoursej] 都是 互不相同 的。
 * time.length == n
 * 1 <= time[i] <= 10^4
 * 先修课程图是一个有向无环图。
 */

/*
 * 一句话题解： 拓扑排序遍历先修课程图，另外需要一个dp数组cost记录到达课程i的最大耗时;
 * 修完所有课程的最小耗时是，图中所有出度为0的节点的cost的最大值。
 * 
 * 这题很经典，因为它的背景很像管理学中进度管理关键路径这么一个概念，
 * 关键路径耗时是求完成整个工程的最短耗时，实际上它是图中最长路径的长度。
 * 如果题目要求你输出关键路径，那么可能还需要一个dp数组pre记录达到节点i的最长路径的前驱节点。最后逆推。
 * 
 * 类似的题：
 * LeetCode207.课程表 
 * https://leetcode.cn/problems/course-schedule/description/
 */

class Solution {
    List<List<Integer>> topoGraph;
    int[] indegree;
    int[] cost;

    public int minimumTime(int n, int[][] relations, int[] time) {
        init(n, relations, time);
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=n; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
            }
        }
        int maxTime = 0;
        while(!q.isEmpty()) {
            int k = q.size();
            while(k-- > 0) {
                int cur = q.poll();
                if(topoGraph.get(cur).size() == 0) {
                    maxTime = Math.max(maxTime, cost[cur]);
                    continue;
                }
                for(int next : topoGraph.get(cur)) {
                    cost[next] = Math.max(cost[next], cost[cur] + time[next-1]);
                    indegree[next]--;
                    if(indegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }
        }
        return maxTime;
    }

    private void init(int n, int[][] relations, int[] time) {
        topoGraph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            topoGraph.add(new ArrayList<>());
        }
        indegree = new int[n+1];
        cost = new int[n+1];
        for(int i=0; i<n; i++) {
            cost[i+1] = time[i];
        }
        for(int[] relation : relations) {
            topoGraph.get(relation[0]).add(relation[1]);
            indegree[relation[1]]++;
        }
    }
}
