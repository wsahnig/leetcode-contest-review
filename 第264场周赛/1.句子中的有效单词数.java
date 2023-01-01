package 第264场周赛;

/*
 * https://leetcode.cn/problems/number-of-valid-words-in-a-sentence/
 * 
 * 句子中的有效单词数（简单）
 * 
 * 句子仅由小写字母（'a' 到 'z'）、数字（'0' 到 '9'）、连字符（'-'）、标点符号（'!'、'.' 和 ','）以及空格（' '）组成。
 * 每个句子可以根据空格分解成 一个或者多个 token ，这些 token 之间由一个或者多个空格 ' ' 分隔。
 * 如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：
 * 仅由小写字母、连字符和/或标点（不含数字）组成。
 * 至多一个 连字符 '-' 。如果存在，连字符两侧应当都存在小写字母（"a-b" 是一个有效单词，但 "-ab" 和 "ab-" 不是有效单词）。
 * 至多一个 标点符号。如果存在，标点符号应当位于 token 的 末尾 。
 * 这里给出几个有效单词的例子："a-b."、"afad"、"ba-c"、"a!" 和 "!" 。
 * 给你一个字符串 sentence ，请你找出并返回 sentence 中 有效单词的数目。
 * 
 * 
 * 示例：
 * 输入：sentence = "cat and  dog"
 * 输出：3
 * 
 * 输入：sentence = "!this  1-s b8d!"
 * 输出：0
 * 
 * 输入：sentence = "alice and  bob are playing stone-game10"
 * 输出：5
 * 
 * 提示：
 * 1 <= sentence.length <= 1000
 * sentence 由小写英文字母、数字（0-9）、以及字符（' '、'-'、'!'、'.' 和 ','）组成
 * 句子中至少有 1 个 token
 */

/*
 * 一句话题解： 字符串遍历
 */

class Solution {
    public int countValidWords(String sentence) {
        String[] words = sentence.trim().split(" +");
        int cnt = 0;
        for(String word : words)
        {
            if(isValid(word)) cnt++;
        }
        return cnt;
    }
    
    private boolean isValid(String s)
    {
        int len = s.length();
        int numOfHyphen = 0;
        for(int i=0; i<len; i++)
        {
            char c = s.charAt(i);
            if(Character.isDigit(c)) return false;
            else if(c == '-')
            {
                if(numOfHyphen != 0) return false;
                numOfHyphen++;
                if(i == 0 || i == len-1 || !Character.isLetter(s.charAt(i-1)) || !Character.isLetter(s.charAt(i+1))) return false;   
            }
            else if(".!,".indexOf(c) != -1)
            {
                if(i != len-1) return false;
            }
        }
        return true;
    }
}