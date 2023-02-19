package 第270场周赛;

/**
 * https://leetcode.cn/problems/finding-3-digit-even-numbers/description/
 * 
 * 找出 3 位偶数(简单)
 * 
 * 给你一个整数数组 digits ，其中每个元素是一个数字（0 - 9）。数组中可能存在重复元素。
 * 你需要找出 所有 满足下述条件且 互不相同 的整数：
 * 该整数由 digits 中的三个元素按 任意 顺序 依次连接 组成。
 * 该整数不含 前导零
 * 该整数是一个 偶数
 * 例如，给定的 digits 是 [1, 2, 3] ，整数 132 和 312 满足上面列出的全部条件。
 * 将找出的所有互不相同的整数按 递增顺序 排列，并以数组形式返回。
 * 
 * 示例：
 * 输入：digits = [2,1,3,0]
 * 输出：[102,120,130,132,210,230,302,310,312,320]
 * 
 * 输入：digits = [2,2,8,8,2]
 * 输出：[222,228,282,288,822,828,882]
 * 
 * 输入：digits = [3,7,5]
 * 输出：[]
 */

/**
 * 一句话题解：
 * 排序数组，dfs
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private void dfs(int[] digits, boolean[] visited, String cur, List<String> ans) {
        if (cur.length() == 1) {
            if (cur.charAt(0) == '0')
                return;
        }
        if (cur.length() == 3) {
            if (((cur.charAt(2) - '0') & 1) == 0) {
                ans.add(cur);
            }
            return;
        }

        for (int i = 0; i < digits.length; i++) {
            if (visited[i])
                continue;
            if (i > 0 && digits[i] == digits[i - 1] && !visited[i - 1])
                continue;
            visited[i] = true;
            dfs(digits, visited, cur + digits[i], ans);
            visited[i] = false;
        }
    }

    public int[] findEvenNumbers(int[] digits) {
        Arrays.sort(digits);
        boolean[] visited = new boolean[digits.length];
        List<String> ans = new ArrayList<>();
        dfs(digits, visited, "", ans);

        int[] nums = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            nums[i] = Integer.parseInt(ans.get(i));
        }
        return nums;
    }
}