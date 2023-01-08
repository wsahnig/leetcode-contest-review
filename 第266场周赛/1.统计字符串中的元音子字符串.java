package 第266场周赛;

import java.util.Arrays;

/**
 * 统计字符串中的元音子字符串（简单）
 * 
 * 子字符串 是字符串中的一个连续（非空）的字符序列。
 * 元音子字符串 是 仅 由元音（'a'、'e'、'i'、'o' 和 'u'）组成的一个子字符串，且必须包含 全部五种 元音。
 * 给你一个字符串 word ，统计并返回 word 中 元音子字符串的数目 。
 * 
 * 示例：
 * 输入：word = "aeiouu"
 * 输出：2
 * 
 * 输入：word = "unicornarihan"
 * 输出：0
 * 
 * 输入：word = "cuaieuouac"
 * 输出：7
 * 
 * 输入：word = "bbaeixoubb"
 * 输出：0
 * 
 * 提示：
 * 1 <= word.length <= 100
 * word 仅由小写英文字母组成
 */

/**
 * 
 * 一句话题解：双指针
 * 移动右边界，当包含所有元音字母时，右移左边界，这时得到一个最小包含所有元音字母区间;
 * 对于这个最小区间，子串个数为 （区间左边连续元音字母数+1） * （区间右边连续元音字母数+1）;
 * 对于不是元音字母，窗口初始化;
 * 对于不包含所有元音字母，移动右边界。
 * 
 */

class Solution {
    private char[] vowel = {'a', 'e', 'i', 'o', 'u'};

    public int countVowelSubstrings(String word) {
        int[] cnt = new int[5];
        int len = word.length();
        int left = 0, right = 0;
        int sum = 0;
        while(right < len) {
            char c = word.charAt(right++);
            int index = isVowel(c);
            if(index == -1) {
                Arrays.fill(cnt, 0);
                left = right;
            }
            else {
                cnt[index]++;
            }
            boolean isVowelStr = isVowelSubstring(cnt);
            if(isVowelStr) {
                int i = left;
                while(isVowelSubstring(cnt)) {
                    c = word.charAt(left++);
                    cnt[isVowel(c)]--;
                }
                int j = right;
                while(j < word.length() && isVowel(word.charAt(j)) != -1) j++;

                sum += (left-i) * (j-right+1);
            }
        }
        return sum;
    }

    private int isVowel(char c) {
        for(int i=0; i<5; i++) if(vowel[i] == c) return i;
        return -1;
    }

    private boolean isVowelSubstring(int[] cnt) {
        for(int x : cnt) if(x == 0) return false;
        return true;
    }
}
