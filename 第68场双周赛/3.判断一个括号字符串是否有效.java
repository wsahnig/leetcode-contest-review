package 第68场双周赛;

/**
 * https://leetcode.cn/problems/check-if-a-parentheses-string-can-be-valid/description/
 * 
 * 判断一个括号字符串是否有效（中等）
 * 一个括号字符串是只由 '(' 和 ')' 组成的 非空 字符串。如果一个字符串满足下面 任意 一个条件，那么它就是有效的：
 * 字符串为 ().
 * 它可以表示为 AB（A 与 B 连接），其中A 和 B 都是有效括号字符串。
 * 它可以表示为 (A) ，其中 A 是一个有效括号字符串。
 * 给你一个括号字符串 s 和一个字符串 locked ，两者长度都为 n 。
 * locked 是一个二进制字符串，只包含 '0' 和 '1' 。对于 locked 中 每一个 下标 i ：
 * 如果 locked[i] 是 '1' ，你 不能 改变 s[i] 。
 * 如果 locked[i] 是 '0' ，你 可以 将 s[i] 变为 '(' 或者 ')' 。
 * 如果你可以将 s 变为有效括号字符串，请你返回 true ，否则返回 false 。
 * 
 * 示例：
 * 输入：s = "))()))", locked = "010100"
 * 输出：true
 * 
 * 输入：s = "()()", locked = "0000"
 * 输出：true
 * 
 * 输入：s = ")", locked = "0"
 * 输出：false
 * 
 * n == s.length == locked.length
 * 1 <= n <= 10^5
 * s[i] 要么是 '(' 要么是 ')' 。
 * locked[i] 要么是 '0' 要么是 '1' 。
 * 
 * 
 */

/**
 * 一句话题解：
 * 贪心;
 * 从左到右检查锁定的')'是否能匹配，优先匹配左边锁定的'('，
 * 从右到左检查锁定的'('是否能匹配，优先匹配右边锁定的')'
 */

class Solution {
    public boolean canBeValid(String s, String locked) {
        int len = s.length();
        if (len % 2 != 0)
            return false;
        int unlocked = 0;
        int left = 0;
        for (int i = 0; i < len; i++) {
            if (locked.charAt(i) == '1') {
                if (s.charAt(i) == ')') {
                    if (unlocked == 0 && left == 0)
                        return false;
                    else if (left > 0) {
                        left--;
                    } else {
                        unlocked--;
                    }
                } else {
                    left++;
                }
            } else {
                unlocked++;
            }
        }

        unlocked = 0;
        int right = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (locked.charAt(i) == '1') {
                if (s.charAt(i) == '(') {
                    if (unlocked == 0 && right == 0)
                        return false;
                    else if (right > 0) {
                        right--;
                    } else {
                        unlocked--;
                    }
                } else {
                    right++;
                }
            } else {
                unlocked++;
            }
        }

        return true;
    }
}