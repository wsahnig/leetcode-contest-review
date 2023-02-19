package 第270场周赛;

/**
 * https://leetcode.cn/problems/step-by-step-directions-from-a-binary-tree-node-to-another/description/
 * 
 * 从二叉树一个节点到另一个节点每一步的方向（中等）
 * 
 * 给你一棵 二叉树 的根节点 root ，这棵二叉树总共有 n 个节点。
 * 每个节点的值为 1 到 n 中的一个整数，且互不相同。
 * 给你一个整数 startValue ，表示起点节点 s 的值，和另一个不同的整数 destValue ，表示终点节点 t 的值。
 * 请找到从节点 s 到节点 t 的 最短路径 ，并以字符串的形式返回每一步的方向。
 * 每一步用 大写 字母 'L' ，'R' 和 'U' 分别表示一种方向：
 * 'L' 表示从一个节点前往它的 左孩子 节点。
 * 'R' 表示从一个节点前往它的 右孩子 节点。
 * 'U' 表示从一个节点前往它的 父 节点。
 * 请你返回从 s 到 t 最短路径 每一步的方向。
 * 
 * 示例：
 * 输入：root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
 * 输出："UURL"
 * 
 * 输入：root = [2,1], startValue = 2, destValue = 1
 * 输出："L"
 * 
 * 树中节点数目为 n 。
 * 2 <= n <= 10^5
 * 1 <= Node.val <= n
 * 树中所有节点的值 互不相同 。
 * 1 <= startValue, destValue <= n
 * startValue != destValue
 */

/**
 * 一句话题解:
 * 二叉树的递归操作;
 * 二叉树节点的最近公共祖先;
 * 分别求出祖先到两个节点的路径方向;
 * 拼接两条路径。
*/

import java.util.ArrayList;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        ArrayList<String> directions1 = new ArrayList<>();
        ArrayList<String> directions2 = new ArrayList<>();
        // 求出两个节点的最近公共祖先
        TreeNode ancestor = lowestCommonAncestor(root, startValue, destValue);
        // 分别求出公共祖先到两个节点的路经
        getPath(ancestor, startValue, directions1);
        getPath(ancestor, destValue, directions2);

        StringBuilder sb = new StringBuilder();
        sb.append("U".repeat(directions1.size()));
        for (int i = 0; i < directions2.size(); i++) {
            sb.append(directions2.get(i));
        }
        return sb.toString();
    }

    public TreeNode lowestCommonAncestor(TreeNode root, int node1, int node2) {
        if (root == null || root.val == node1 || root.val == node2) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, node1, node2);
        TreeNode right = lowestCommonAncestor(root.right, node1, node2);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    /**
     * 获取祖先节点到目标节点的路径方向
     */
    public boolean getPath(TreeNode root, int target, ArrayList<String> directions) {
        if (root.val == target) {
            return true;
        }
        boolean hasFound = false;
        if (root.left != null) {
            directions.add("L");
            hasFound = getPath(root.left, target, directions);
        }
        if (!hasFound && root.right != null) {
            directions.add("R");
            hasFound = getPath(root.right, target, directions);
        }
        if (!hasFound)
            directions.remove(directions.size() - 1);
        return hasFound;
    }
}