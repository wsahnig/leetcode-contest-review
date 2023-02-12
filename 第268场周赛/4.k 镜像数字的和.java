package 第268场周赛;

/**
 * https://leetcode.cn/problems/sum-of-k-mirror-numbers/description/
 * 
 * k 镜像数字的和（困难）
 * 一个 k 镜像数字 指的是一个在十进制和 k 进制下从前往后读和从后往前读都一样的 没有前导 0 的 正 整数。
 * 比方说，9 是一个 2 镜像数字。9 在十进制下为 9 ，二进制下为 1001 ，两者从前往后读和从后往前读都一样。
 * 相反地，4 不是一个 2 镜像数字。4 在二进制下为 100 ，从前往后和从后往前读不相同。
 * 给你进制 k 和一个数字 n ，请你返回 k 镜像数字中 最小 的 n 个数 之和 。
 * 
 * 示例：
 * 输入：k = 2, n = 5
 * 输出：25
 * 它们的和为 1 + 3 + 5 + 7 + 9 = 25 。
 * 
 * 输入：k = 3, n = 7
 * 输出：499
 * 它们的和为 1 + 2 + 4 + 8 + 121 + 151 + 212 = 499。
 * 
 * 输入：k = 7, n = 17
 * 输出：20379000
 * 
 * 2 <= k <= 9
 * 1 <= n <= 30
 */

/**
 * 一句话题解：
 * 对于d位数，生成2*d-1 和 2*d位数的回文数（10进制），判断回文数是否是k进制回文数;
 * 找出从小到大的n个k进制镜像数，即从小到大枚举d位数，d+1位数...，找出n个k镜像数。
 * 
 * 官方题解称此为折半搜索，这种折半生成回文数的思路，在周赛中很常见。
 */

class Solution {
    public long kMirror(int k, int n) {
        long sum = 0;
        long base = 1;
        while(true) {
            //d-digit Number
            long left = base, right = base*10-1;
            //2*d-1
            for(long i=left; i<=right; i++) {
                String num = String.valueOf(i);
                String newNum = num + reverse(num.substring(0, num.length()-1));
                long x = Long.parseLong(newNum);
                if(isKMirror(x, k)) {
                    //System.out.println(x);
                    sum += x;
                    if(--n == 0) return sum;
                }
            }
            //2*d
            for(long i=left; i<=right; i++) {
                String num = String.valueOf(i);
                String newNum = num + reverse(num);
                long x = Long.parseLong(newNum);
                if(isKMirror(x, k)) {
                    //System.out.println(x);
                    sum += x;
                    if(--n == 0) return sum;
                }
            }
            base *= 10;
        }
    }

    private boolean isKMirror(long x, int k) {
        StringBuilder sb = new StringBuilder();
        while(x != 0) {
            sb.append(x % k);
            x /= k;
        }
        char[] arr = sb.toString().toCharArray();
        for(int i=0; i<arr.length/2; i++) {
            if(arr[i] != arr[arr.length-1-i]) return false;
        }
        return true;
    }

    private String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}