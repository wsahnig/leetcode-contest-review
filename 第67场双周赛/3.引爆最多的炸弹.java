package 第67场双周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/detonate-the-maximum-bombs/
 * 
 * 引爆最多的炸弹（中等）
 * 给你一个炸弹列表。一个炸弹的 爆炸范围 定义为以炸弹为圆心的一个圆。
 * 炸弹用一个下标从 0 开始的二维整数数组 bombs 表示，其中 bombs[i] = [xi, yi, ri] 。
 * xi 和 yi 表示第 i 个炸弹的 X 和 Y 坐标，ri 表示爆炸范围的 半径 。
 * 你需要选择引爆 一个 炸弹。
 * 当这个炸弹被引爆时，所有 在它爆炸范围内的炸弹都会被引爆，这些炸弹会进一步将它们爆炸范围内的其他炸弹引爆。
 * 给你数组 bombs ，请你返回在引爆 一个 炸弹的前提下，最多 能引爆的炸弹数目。
 * 
 * 示例：
 * 输入：bombs = [[2,1,3],[6,1,4]]
 * 输出：2
 * 
 * 输入：bombs = [[1,1,5],[10,10,5]]
 * 输出：1
 * 
 * 输入：bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
 * 输出：5
 * 
 * 1 <= bombs.length <= 100
 * bombs[i].length == 3
 * 1 <= xi, yi, ri <= 10^5
 */

/**
 * 一句话题解：
 * 建立炸弹爆破有向图，x -> y表示x到y有一条有向边;
 * 以每个炸弹作为起点DFS，得到能遍历到的节点数量，一共进行N次DFS，得到最大值。
 * 
 * 误区： 炸弹爆破是有向的， 因此不能使用并查集，找到最大连通分量的元素个数，比如
 * x -> y ， z -> y，最多能爆破2个，但用并查集得到的结果是3。
 */
class Solution {
    List<List<Integer>> bombGraph;
    boolean[] visited;

    public int maximumDetonation(int[][] bombs) {
        visited = new boolean[bombs.length];
        bombGraph = new ArrayList<>();
        for (int i = 0; i < bombs.length; i++) {
            bombGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < bombs.length; i++) {
            for (int j = 0; j < bombs.length; j++) {
                if (i == j)
                    continue;
                if (canBomb(bombs[i], bombs[j])) {
                    bombGraph.get(i).add(j);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < bombs.length; i++) {
            max = Math.max(max, bombDfs(i));
            Arrays.fill(visited, false);
        }
        return max;
    }

    private int bombDfs(int cur) {
        int cnt = 1;
        visited[cur] = true;
        for (int next : bombGraph.get(cur)) {
            if (!visited[next]) {
                visited[next] = true;
                cnt += bombDfs(next);
            }
        }
        return cnt;
    }

    private boolean canBomb(int[] a, int[] b) {
        double distance = Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2);
        return distance <= Math.pow(a[2], 2);
    }
}
