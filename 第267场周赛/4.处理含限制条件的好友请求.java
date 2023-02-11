package 第267场周赛;

/**
 * https://leetcode.cn/problems/process-restricted-friend-requests/description/
 * 
 * 处理含限制条件的好友请求(困难)
 * 给你一个整数 n ，表示网络上的用户数目。每个用户按从 0 到 n - 1 进行编号。
 * 给你一个下标从 0 开始的二维整数数组 restrictions ，
 * 其中 restrictions[i] = [xi, yi] 意味着用户 xi 和用户 yi 不能 成为 朋友 ，不管是 直接 还是通过其他用户 间接 。
 * 最初，用户里没有人是其他用户的朋友。
 * 给你一个下标从 0 开始的二维整数数组 requests 表示好友请求的列表，
 * 其中 requests[j] = [uj, vj] 是用户 uj 和用户 vj 之间的一条好友请求。
 * 如果 uj 和 vj 可以成为 朋友 ，那么好友请求将会 成功 。
 * 每个好友请求都会按列表中给出的顺序进行处理（即，requests[j] 会在 requests[j + 1] 前）。
 * 一旦请求成功，那么对所有未来的好友请求而言， uj 和 vj 将会 成为直接朋友 。
 * 返回一个 布尔数组 result ，其中元素遵循此规则：如果第 j 个好友请求 成功 ，那么 result[j] 就是 true ；否则，为 false 。
 * 
 * 注意：如果 uj 和 vj 已经是直接朋友，那么他们之间的请求将仍然 成功 。
 * 
 * 示例：
 * 输入：n = 3, restrictions = [[0,1]], requests = [[0,2],[2,1]]
 * 输出：[true,false]
 * 
 * 输入：n = 3, restrictions = [[0,1]], requests = [[1,2],[0,2]]
 * 输出：[true,false]
 * 
 * 输入：n = 5, restrictions = [[0,1],[1,2],[2,3]], requests = [[0,4],[1,2],[3,1],[3,4]]
 * 输出：[true,false,true,false]
 * 
 * 提示：
 * 2 <= n <= 1000
 * 0 <= restrictions.length <= 1000
 * restrictions[i].length == 2
 * 0 <= xi, yi <= n - 1
 * xi != yi
 * 1 <= requests.length <= 1000
 * requests[j].length == 2
 * 0 <= uj, vj <= n - 1
 * uj != vj
 */

/**
 * 一句话题解： 并查集模板题;
 * 并查集存好友关系，对于每次添加好友，遍历限制中的每一对，如果无限制则能添加成功，加入并查集;
 * 某个用户在并查集中都存在一个父用户作为代表，即u = find(userId);
 * 任何一个限制都能转化为父节点r的限制，r1 == u1 && r2 == u2 || r1 == u2 && r2 == u1表示有限制;
 * 注意限制关系不能存入并查集。
 * 
 */

import java.util.Arrays;

class Solution {
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        UnionFind uf = new UnionFind(n);
        boolean[] result = new boolean[requests.length];
        Arrays.fill(result, true);
        for(int i = 0; i < requests.length; i++) {
            int[] request = requests[i];
            int u1 = uf.find(request[0]), u2 = uf.find(request[1]);
            for(int[] restriction : restrictions) {
                int r1 = uf.find(restriction[0]), r2 = uf.find(restriction[1]);
                if(r1 == u1 && r2 == u2 || r1 == u2 && r2 == u1) {
                    result[i] = false;
                    break;
                }
            }
            if(result[i]) {
                uf.union(u1, u2);
            }
        }
        return result;
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
    }
}
