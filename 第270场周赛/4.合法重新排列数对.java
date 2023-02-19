package 第270场周赛;

/**
 * https://leetcode.cn/problems/valid-arrangement-of-pairs/
 * 
 * 合法重新排列数对（困难）
 * 
 * 给你一个下标从 0 开始的二维整数数组 pairs ，其中 pairs[i] = [starti, endi] 。
 * 如果 pairs 的一个重新排列，满足对每一个下标 i （ 1 <= i < pairs.length ）都有 endi-1 == starti ，那么我们就认为这个重新排列是 pairs 的一个 合法重新排列 。
 * 请你返回 任意一个 pairs 的合法重新排列。
 * 注意：数据保证至少存在一个 pairs 的合法重新排列。
 * 
 * 示例： 
 * 输入：pairs = [[5,1],[4,5],[11,9],[9,4]]
 * 输出：[[11,9],[9,4],[4,5],[5,1]]
 * 
 * 输入：pairs = [[1,3],[3,2],[2,1]]
 * 输出：[[1,3],[3,2],[2,1]]
 * 
 * 输入：pairs = [[1,2],[1,3],[2,1]]
 * 输出：[[1,2],[2,1],[1,3]]
 * 
 * 1 <= pairs.length <= 10^5
 * pairs[i].length == 2
 * 0 <= starti, endi <= 10^9
 * starti != endi
 * pairs 中不存在一模一样的数对。
 * 至少 存在 一个合法的 pairs 重新排列。
 */

/**
 * 一句话题解：
 * 欧拉通路;
 * 选择欧拉通路的起始节点，如果每个节点入度和出度相等，选择任意一个,如果有一个节点出度比入度大1,则作为起始节点;
 * Hierholzer算法求解欧拉通路。
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    Map<Integer, List<Integer>> graph;
    Map<Integer, Integer> degree;
    List<int[]> ans;

    public int[][] validArrangement(int[][] pairs) {
        graph = new HashMap<>();
        ans = new ArrayList<>();
        degree = new HashMap<>();

        // build graph
        buildGraph(pairs);

        // select a start
        int start = -1;
        for (Integer i : degree.keySet()) {
            if (degree.get(i) > 0) {
                start = i;
                break;
            }
        }

        if (start == -1)
            start = pairs[0][0];

        // dfs Hierholzer
        dfs(start);

        int size = ans.size();
        int[][] range = new int[size][2];
        for (int i = 0; i < size; i++) {
            range[i][0] = ans.get(size - 1 - i)[0];
            range[i][1] = ans.get(size - 1 - i)[1];
        }
        return range;
    }

    private void buildGraph(int[][] pairs) {
        for (int[] pair : pairs) {
            graph.putIfAbsent(pair[0], new ArrayList<>());
            List<Integer> edges = graph.get(pair[0]);
            edges.add(pair[1]);

            int cnt = degree.getOrDefault(pair[0], 0);
            degree.put(pair[0], cnt + 1);
            cnt = degree.getOrDefault(pair[1], 0);
            degree.put(pair[1], cnt - 1);
        }
    }

    private void dfs(int start) {
        List<Integer> edges = graph.get(start);
        if (edges == null)
            return;
        while (edges.size() > 0) {
            int next = edges.get(edges.size() - 1);
            edges.remove(edges.size() - 1);
            dfs(next);
            ans.add(new int[] { start, next });
        }
    }

}
