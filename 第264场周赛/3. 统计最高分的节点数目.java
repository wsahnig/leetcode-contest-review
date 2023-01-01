package 第264场周赛;

import java.util.*;

/*
 *
 * https://leetcode.cn/problems/count-nodes-with-the-highest-score/description/
 *  
 * 统计最高分的节点数目(中等)
 * 
 * 
 * 给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。
 * 同时给你一个下标从 0 开始的整数数组 parents 表示这棵树，其中 parents[i] 是节点 i 的父节点。
 * 由于节点 0 是根，所以 parents[0] == -1 。
 * 一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。
 * 求出某个节点分数的方法是，
 * 将这个节点和与它相连的边全部 删除 ，剩余部分是若干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。
 * 请你返回有 最高得分 节点的 数目 。
 * 
 * 示例：
 * 输入：parents = [-1,2,0,2,0]
 * 输出：3
 * 
 * 输入：parents = [-1,2,0]
 * 输出：2
 * 
 * 提示：
 * n == parents.length 
 * 2 <= n <= 10^5 
 * parents[0] == -1 
 * 对于 i != 0 ，有 0 <= parents[i] <= n - 1 
 * parents 表示一棵二叉树
 */

/*
 * 一句话题解： 
 * 首先构造这棵树；
 * 然后深度优先遍历，求树中任意节点为根的子树节点个数，同时能求出该节点分数，左、右子树（如果存在）节点数、剩余部分节点数三者乘积；
 * 节点分数定义为long。
 * 
 * 这种题在周赛中比较常见。
 */

class Solution {
    long maxScore;
    int maxScoreNodes;
    List<List<Integer>> tree;
    
    public int countHighestScoreNodes(int[] parents) {
        tree = new ArrayList<>();
        
        for(int i=0; i<parents.length; i++)
        {
            tree.add(new ArrayList<>());
        }
        
        for(int i=1; i<parents.length; i++)
        {
            tree.get(parents[i]).add(i);
        }
        
        maxScore = 0;
        maxScoreNodes = 0;
        dfs(0, parents.length);
        
        return maxScoreNodes;
    }
    
    private int dfs(int node, int sumOfNodes)
    {
        //以node为根的子树的节点个数
        int total = 1;
        //node节点的权值
        long product = 1;
        
        for(int child : tree.get(node))
        {
            int nodeSumOfSubtree = dfs(child, sumOfNodes);
            product *= nodeSumOfSubtree;
            total += nodeSumOfSubtree;
        }
        long remaining = sumOfNodes - total;
        if(remaining > 0) product *= remaining;
    
        if(product > maxScore) {
            maxScore = product;
            maxScoreNodes = 1;
        }
        else if(product == maxScore) {
            maxScoreNodes++;
        }
        return total;
    }
}
