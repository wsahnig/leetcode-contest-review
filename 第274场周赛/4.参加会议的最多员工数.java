package 第274场周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 一个公司准备组织一场会议，邀请名单上有 n 位员工。
 * 公司准备了一张 圆形 的桌子，可以坐下 任意数目 的员工。
 * 员工编号为 0 到 n - 1 。每位员工都有一位 喜欢 的员工，每位员工 当且仅当 他被安排在喜欢员工的旁边，他才会参加会议。
 * 每位员工喜欢的员工 不会 是他自己。
 * 给你一个下标从 0 开始的整数数组 favorite ，其中 favorite[i] 表示第 i 位员工喜欢的员工。
 * 请你返回参加会议的 最多员工数目 。
 * 
 * 示例：
 * 输入：favorite = [2,2,1,2]
 * 输出：3
 * 
 * 输入：favorite = [1,2,0]
 * 输出：3
 * 
 * 输入：favorite = [3,0,1,4,1]
 * 输出：4
 * 
 * n == favorite.length
 * 2 <= n <= 10^5
 * 0 <= favorite[i] <= n - 1
 * favorite[i] != i
 */

/**
 * N句话题解：
 * 求图中最大环;
 * 如果环节点大于2，记录最大环。
 * 如果环节点数为2，两个端点的最长链长度之和为可参会人员，所有大小为2的环+链条都可以组成一个会。
 * 取两者较大值。
 * 
 * 求环长度可用拓扑排序+DFS，拓扑排序时维护每个位置的最长路径长度的dp。
 */

 class Solution {
    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] indegree = new int[n];
        boolean[] vis = new boolean[n];
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i=0; i<n; i++) {
            indegree[favorite[i]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<n; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
                vis[i] = true;
            }
        }
        while(!q.isEmpty()) {
            int k = q.size();
            while(k-- > 0) {
                int emp = q.poll();
                int f = favorite[emp];
                dp[f] = Math.max(dp[f], dp[emp] + 1);
                indegree[f]--;
                if(indegree[f] == 0) {
                    q.offer(f);
                    vis[f] = true;
                }
            }
        }
        int maxPair = 0;
        int maxCycle = 0;
        for(int i=0; i<n; i++) {
            if(!vis[i]) {
                int len = dfsCycle(i, favorite, vis);
                if(len == 2) {
                    maxPair += dp[i] + dp[favorite[i]];
                }
                else maxCycle = Math.max(maxCycle, len);
            }
        }
        return Math.max(maxPair, maxCycle);
    }

    private int dfsCycle(int x, int[] favorite, boolean[] vis) {
        if(vis[x]) {
            return 0;
        }
        vis[x] = true;
        return 1+dfsCycle(favorite[x], favorite, vis);
    }
}

class Solution2 {
    int maxWithCircle;
    int maxWithPair;
    List<List<Integer>> graph;

    public int maximumInvitations(int[] favorite) {
        maxWithCircle = 0;
        maxWithPair = 0;
        int len = favorite.length;
        boolean[] visited = new boolean[len];
        graph = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < len; i++) {
            graph.get(favorite[i]).add(i);
        }

        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                findCirle(i, visited, favorite, new ArrayList<>());
            }
        }
        return Math.max(maxWithCircle, maxWithPair);
    }

    private void findCirle(int s, boolean[] visited, int[] favorite, List<Integer> path) {
        visited[s] = true;
        path.add(s);
        if (visited[favorite[s]]) {
            int index;
            for (index = path.size() - 1; index >= 0; index--) {
                if (path.get(index) == favorite[s]) {
                    break;
                }
            }
            if (index == -1)
                return;
            int len = (path.size() - index);

            if (len == 2) {
                graph.get(s).remove((Integer) favorite[s]);
                graph.get(favorite[s]).remove((Integer) s);
                maxWithPair += getLongestPath(s, visited) + getLongestPath(favorite[s], visited);
            } else
                maxWithCircle = Math.max(maxWithCircle, len);
        } else {
            findCirle(favorite[s], visited, favorite, path);
        }
    }

    private int getLongestPath(int start, boolean[] visited) {
        visited[start] = true;
        List<Integer> nodes = graph.get(start);
        int max = 0;
        if (nodes.size() > 0) {
            for (int node : nodes) {
                max = Math.max(max, getLongestPath(node, visited));
            }
        }
        return max + 1;
    }

}