/**
 * https://leetcode.cn/problems/check-whether-two-strings-are-almost-equivalent/description/
 * 
 * 检查两个字符串是否几乎相等(简单)
 * 
 * 如果两个字符串 word1 和 word2 中从 'a' 到 'z' 每一个字母出现频率之差都 不超过 3 ，那么我们称这两个字符串 word1 和 word2 几乎相等 。
 * 给你两个长度都为 n 的字符串 word1 和 word2 ，如果 word1 和 word2 几乎相等 ，请你返回 true ，否则返回 false 。
 * 一个字母 x 的出现 频率 指的是它在字符串中出现的次数。
 * 
 * 提示：
 * n == word1.length == word2.length
 * 1 <= n <= 100
 * word1 和 word2 都只包含小写英文字母。
 * 
 */

/**
 * 
 * 一句话题解： 哈希表统计小写英文字母出现的次数。
 * 
 */
class Solution {
    public boolean checkAlmostEquivalent(String word1, String word2) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for(char c : word1.toCharArray()) {
            cnt1[c - 'a']++;
        }
        for(char c : word2.toCharArray()) {
            cnt2[c - 'a']++;
        }
        for(int i=0; i<26; i++) {
            if(Math.abs(cnt1[i] - cnt2[i]) > 3) {
                return false;
            }
        }
        return true;
    }
}