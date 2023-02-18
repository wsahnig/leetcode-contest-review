package 第66场双周赛;

/** 
 * https://leetcode.cn/problems/count-common-words-with-one-occurrence/description/
 * 
 * 统计出现过一次的公共字符串(简单)
 * 
 * 给你两个字符串数组 words1 和 words2 ，请你返回在两个字符串数组中 都恰好出现一次 的字符串的数目。
 * 
 * 示例：
 * 输入：words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
 * 输出：2
 * 
 * 输入：words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
 * 输出：0
 * 
 * 输入：words1 = ["a","ab"], words2 = ["a","a","a","ab"]
 * 输出：1
 * 
 * 1 <= words1.length, words2.length <= 1000
 * 1 <= words1[i].length, words2[j].length <= 30
 * words1[i] 和 words2[j] 都只包含小写英文字母。
 */

/**
 * 一句话题解：
 * 哈希表统计两个数组中字符串出现的次数;
 * 遍历哈希表，找出两个哈希表中均出现一次的公共字符串。
 *  
 * */ 
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> hm = new HashMap<>();
        Map<String, Integer> hm2 = new HashMap<>();
        
        for(String word : words1)
        {
            hm.put(word, hm.getOrDefault(word, 0)+1);
        }
        for(String word : words2)
        {
            hm2.put(word, hm2.getOrDefault(word, 0)+1);
        }
        int cnt = 0;
        for(String word : hm.keySet())
        {
            if(hm.get(word) == 1 && hm2.containsKey(word) && hm2.get(word) == 1) cnt++;
        }
        return cnt;
    }
}
