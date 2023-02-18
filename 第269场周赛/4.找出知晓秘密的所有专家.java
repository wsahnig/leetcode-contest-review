package 第269场周赛;

/**
 * https://leetcode.cn/problems/find-all-people-with-secret/description/
 * 
 * 找出知晓秘密的所有专家（困难）
 * 
 * 给你一个整数 n ，表示有 n 个专家从 0 到 n - 1 编号。
 * 另外给你一个下标从 0 开始的二维整数数组 meetings ，其中 meetings[i] = [xi, yi, timei] 表示专家 xi 和专家 yi 在时间 timei 要开一场会。
 * 一个专家可以同时参加 多场会议 。
 * 最后，给你一个整数 firstPerson 。
 * 专家 0 有一个 秘密 ，最初，他在时间 0 将这个秘密分享给了专家 firstPerson 。
 * 接着，这个秘密会在每次有知晓这个秘密的专家参加会议时进行传播。
 * 更正式的表达是，每次会议，如果专家 xi 在时间 timei 时知晓这个秘密，那么他将会与专家 yi 分享这个秘密，反之亦然。
 * 秘密共享是 瞬时发生 的。也就是说，在同一时间，一个专家不光可以接收到秘密，还能在其他会议上与其他专家分享。
 * 在所有会议都结束之后，返回所有知晓这个秘密的专家列表。你可以按 任何顺序 返回答案。
 * 
 * 示例：
 * 输入：n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
 * 输出：[0,1,2,3,5]
 * 
 * 输入：n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3
 * 输出：[0,1,3]
 * 
 * 输入：n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1
 * 输出：[0,1,2,3,4]
 * 
 * 2 <= n <= 10^5
 * 1 <= meetings.length <= 10^5
 * meetings[i].length == 3
 * 0 <= xi, yi <= n - 1
 * xi != yi
 * 1 <= timei <= 105
 * 1 <= firstPerson <= n - 1
 */

/**
 * 一句话题解：
 * 使用并查集；
 * 遍历在某个时间的会议，并记录这个时间会议的所有人，
 * 如果会议中有一人知道秘密(find(p)==0)，那么参会另一人也知道秘密了（与0连接），如果两人暂时都不知道秘密，那么先保存连接关系,
 * 某个时间的会议遍历完，对于不知道秘密的人，删除其连接关系（会议结束，之后即使其中一人知道秘密，也不能传递给另一人，除非两人又有新的会议）。
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
        Set<Integer> allPeopleKnowSecret = new HashSet<>();
        allPeopleKnowSecret.add(0);
        allPeopleKnowSecret.add(firstPerson);
        UnionFind uf = new UnionFind(n);
        uf.unionRoot(firstPerson);
        int i = 0;
        while(i < meetings.length) {
            int time = meetings[i][2];
            Set<Integer> curPeople = new HashSet<>();
            while(i < meetings.length && time == meetings[i][2]) {
                int expert1 = meetings[i][0];
                int expert2 = meetings[i][1];
                int p1 = uf.find(expert1);
                int p2 = uf.find(expert2);
                if(p1 == 0) {
                    uf.unionRoot(p2);
                }
                else if(p2 == 0) {
                    uf.unionRoot(p1);
                }
                else {
                    uf.union(p1, p2);
                }
                curPeople.add(expert1);
                curPeople.add(expert2);
                i++;
            }
            for(int people : curPeople) {
                if(uf.find(people) != 0) uf.ununion(people);
                else allPeopleKnowSecret.add(people);
            }
        }
        return new ArrayList<>(allPeopleKnowSecret);
    }
}

class UnionFind {
    private int[] parent;
    private int[] rank;
    
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for(int i=0; i<n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ) {
            return;
        }
        if(rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
            rank[rootP] += rank[rootQ];
        }
        else {
            parent[rootP] = rootQ;
            rank[rootQ] += rank[rootP];
        }
    }
    
    public int find(int x) {
        while(parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public void unionRoot(int p) {
        parent[p] = 0;
    }

    public void ununion(int p) {
        parent[p] = p;
    }
}
