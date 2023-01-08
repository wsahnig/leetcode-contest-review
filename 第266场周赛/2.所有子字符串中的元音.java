package 第266场周赛;

/**
 * https://leetcode.cn/problems/vowels-of-all-substrings/
 * 
 * 所有子字符串中的元音(中等)
 * 
 * 给你一个字符串 word ，返回 word 的所有子字符串中 元音的总数 ，元音是指 'a'、'e'、'i'、'o' 和 'u' 。
 * 子字符串 是字符串中一个连续（非空）的字符序列。
 * 注意：由于对 word 长度的限制比较宽松，答案可能超过有符号 32 位整数的范围。计算时需当心。
 * 
 * 示例：
 * 输入：word = "aba"
 * 输出：6
 * 
 * 输入：word = "abc"
 * 输出：3
 * 
 * 输入：word = "ltcd"
 * 输出：0
 * 
 * 输入：word = "noosabasboosa"
 * 输出：237
 * 
 * 提示：
 * 1 <= word.length <= 10^5
 * word 由小写英文字母组成
 */

/**
 * 一句话题解： 每个元音字母的贡献值，元音字母（左侧元素数+1） * （右侧元素数+1）
 * 
 * 如果遍历所有子串，然后对子串中元音字母数求和，势必会超时。
 * 这题在周赛中比较常见，换个思维考虑每个元音字母在所有子串中的贡献度，有多少个子串会包含该字符。
 */

class Solution {
    public long countVowels(String word) {
        long ans = 0;
        int len = word.length();
        
        for(int i = 0; i < len; i++)
        {
           char c = word.charAt(i);
           if(isVowel(c))
           {
               ans += (long)(len-i) * (i+1);
           }
        }
        
        return ans;
    }
    
    private boolean isVowel(char c)
    {
        return  c == 'a' ||  c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
