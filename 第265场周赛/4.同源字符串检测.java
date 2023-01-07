package 第265场周赛;

import java.util.Arrays;

/*
 * https://leetcode.cn/problems/check-if-an-original-string-exists-given-two-encoded-strings/description/
 * 
 * 同源字符串检测（困难）
 * 
 * 原字符串由小写字母组成，可以按下述步骤编码：
 * 任意将其 分割 为由若干 非空 子字符串组成的一个 序列 。
 * 任意选择序列中的一些元素（也可能不选择），然后将这些元素替换为元素各自的长度（作为一个数字型的字符串）。
 * 重新 顺次连接 序列，得到编码后的字符串。
 *  例如，编码 "abcdefghijklmnop" 的一种方法可以描述为：
 * 
 * 将原字符串分割得到一个序列：["ab", "cdefghijklmn", "o", "p"] 。
 * 选出其中第二个和第三个元素并分别替换为它们自身的长度。序列变为 ["ab", "12", "1", "p"] 。
 * 重新顺次连接序列中的元素，得到编码后的字符串："ab121p" 。
 * 给你两个编码后的字符串 s1 和 s2 ，由小写英文字母和数字 1-9 组成。
 * 如果存在能够同时编码得到 s1 和 s2 原字符串，返回 true ；否则，返回 false。
 * 
 * 注意：生成的测试用例满足 s1 和 s2 中连续数字数不超过 3 
 * 
 * 示例：
 * 输入：s1 = "internationalization", s2 = "i18n"
 * 输出：true
 * 
 * 输入：s1 = "l123e", s2 = "44"
 * 输出：true
 * 
 * 输入：s1 = "a5b", s2 = "c5b"
 * 输出：false
 * 
 * 输入：s1 = "112s", s2 = "g841"
 * 输出：true
 * 
 * 输入：s1 = "ab", s2 = "a2"
 * 输出：false
 * 
 * 提示：
 * 1 <= s1.length, s2.length <= 40
 * s1 和 s2 仅由数字 1-9 和小写英文字母组成
 * s1 和 s2 中连续数字数不超过 3
 */

 /*
  * 一句话题解：难度超出能力范围，请欣赏官方题解：
  * https://leetcode.cn/problems/check-if-an-original-string-exists-given-two-encoded-strings/solutions/1077095/tong-yuan-zi-fu-chuan-jian-ce-by-leetcod-mwva/
  */

class Solution {
    int[][][][] dp;
    char[] chs1;
    char[] chs2;
    public boolean possiblyEquals(String s1, String s2) {
        dp = new int[41][41][2][1000];
        for(int i=0; i<41; i++) {
            for(int j=0; j<41; j++) {
                for(int k=0; k<2; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }
        chs1 = s1.toCharArray();
        chs2 = s2.toCharArray();
        return dfs(0, 0, 0, 0) == 1;
    }

    private int dfs(int i, int j, int which, int rest) {
        int m = chs1.length;
        int n = chs2.length;
        if(dp[i][j][which][rest] != -1) {
            return dp[i][j][which][rest];
        }
        if(which == 0) {
            if(j == n) {
                return dp[i][j][which][rest] = (i == m && rest == 0) ? 1 : 0;
            }
            else if(Character.isLetter(chs2[j])) {
                if(rest == 0 && i != m && Character.isLetter(chs1[i])) {
                    return dp[i][j][which][rest] = (chs1[i] == chs2[j] ?
                    dfs(i+1, j+1, 0, 0) : 0);
                }
                else {
                    return dp[i][j][which][rest] = (rest >= 1 ? dfs(i, j+1, 0, rest-1)
                    : dfs(i, j+1, 1, 1));
                }
            }
            else {
                int x = 0, k = j;
                while(k < n && Character.isDigit(chs2[k])) {
                    x = x * 10 + (chs2[k]-'0');
                    if((rest >= x && dfs(i, k+1, 0, rest-x) == 1) 
                    || (rest < x && dfs(i, k+1, 1, x-rest) == 1)) {
                        return dp[i][j][which][rest] = 1;
                    }
                    k++;
                }
                return dp[i][j][which][rest] = 0;
            }
        }
        else {
            if(i == m) {
                return dp[i][j][which][rest] = (j == n && rest == 0) ? 1 : 0;
            }
            else if(Character.isLetter(chs1[i])) {
                if(rest == 0 && j != n && Character.isLetter(chs2[j])) {
                    return dp[i][j][which][rest] = (chs1[i] == chs2[j] ?
                    dfs(i+1, j+1, 0, 0) : 0);
                }
                else {
                    return dp[i][j][which][rest] = (rest >= 1 ? dfs(i+1, j, 1, rest-1)
                    : dfs(i+1, j, 0, 1));
                }
            }
            else {
                int x = 0, k = i;
                while(k < m && Character.isDigit(chs1[k])) {
                    x = x * 10 + (chs1[k]-'0');
                    if((rest >= x && dfs(k+1, j, 1, rest-x) == 1) 
                    || (rest < x && dfs(k+1, j, 0, x-rest) == 1)) {
                        return dp[i][j][which][rest] = 1;
                    }
                    k++;
                }
                return dp[i][j][which][rest] = 0;
            }
        }
    }
}
