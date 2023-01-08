package 第266场周赛;

import java.util.*;

import javafx.util.Pair;

/**
 * https://leetcode.cn/problems/maximum-path-quality-of-a-graph/description/
 * 
 * 最大化一张图中的路径价值
 * 
 * 给你一张 无向 图，图中有 n 个节点，节点编号从 0 到 n - 1 （都包括）。
 * 同时给你一个下标从 0 开始的整数数组 values ，其中 values[i] 是第 i 个节点的 价值 。
 * 同时给你一个下标从 0 开始的二维整数数组 edges ，其中 edges[j] = [uj, vj, timej] 表示节点 uj 和 vj 之间有一条需要 timej 秒才能通过的无向边。
 * 最后，给你一个整数 maxTime 。
 * 合法路径 指的是图中任意一条从节点 0 开始，最终回到节点 0 ，且花费的总时间 不超过 maxTime 秒的一条路径。
 * 你可以访问一个节点任意次。
 * 一条合法路径的 价值 定义为路径中 不同节点 的价值 之和 （每个节点的价值 至多 算入价值总和中一次）。
 * 请你返回一条合法路径的 最大 价值。
 * 注意：每个节点 至多 有 四条 边与之相连。 
 * 
 * 
 * 示例：
 * 输入：values = [0,32,10,43], edges = [[0,1,10],[1,2,15],[0,3,10]], maxTime = 49
 * 输出：75
 * 
 * 输入：values = [5,10,15,20], edges = [[0,1,10],[1,2,10],[0,3,10]], maxTime = 30
 * 输出：25
 * 
 * 输入：values = [1,2,3,4], edges = [[0,1,10],[1,2,11],[2,3,12],[1,3,13]], maxTime = 50
 * 输出：7
 * 
 * 输入：values = [0,1,2], edges = [[1,2,10]], maxTime = 10
 * 输出：0
 * 
 * 提示：
 * n == values.length
 * 1 <= n <= 1000
 * 0 <= values[i] <= 10^8
 * 0 <= edges.length <= 2000
 * edges[j].length == 3 
 * 0 <= uj < vj <= n - 1
 * 10 <= timej, maxTime <= 100
 * [uj, vj] 所有节点对 互不相同 。
 * 每个节点 至多有四条 边。
 * 图可能不连通。
 */

/**
 * 一句话题解： dfs回溯暴力枚举所有路径
 * 
 */

class Solution {
    
    int maxQuality;
    
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) 
    {
        List<List<Pair<Integer, Integer>>> graph = new ArrayList<>();
        
        
        for(int i=0; i<values.length; i++) {
            graph.add(new ArrayList<>());
        }
        
        
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int time = edge[2];
            Pair<Integer, Integer> node1 = new Pair<>(u,time);
            Pair<Integer, Integer> node2 = new Pair<>(v,time);
            graph.get(u).add(node2);
            graph.get(v).add(node1);
        }
        maxQuality = 0;
        dfs(graph, 0, 0, maxTime, values[0], values);
        
        return maxQuality;
    }
    
    private void dfs(List<List<Pair<Integer, Integer>>> graph, int start, int curTime, int maxTime, int curValue, int[] values) {
        if(curTime > maxTime) return;
        if(curTime == maxTime && start != 0) return;
        
        if(start == 0) {
            maxQuality=Math.max(maxQuality, curValue);
        }
        int tmp = values[start];
        if(tmp != 0) values[start] = 0;
        for(Pair<Integer, Integer> node : graph.get(start)) {
            int v = node.getKey();
            int time = node.getValue();
            int value = values[v];
            if(value != 0) values[v] = 0;
            dfs(graph, v, curTime+time, maxTime, curValue+value, values);
            if(value != 0) values[v] = value;
        }
        if(tmp != 0) values[start] = tmp;
    }
}
