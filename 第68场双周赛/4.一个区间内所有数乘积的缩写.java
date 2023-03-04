package 第68场双周赛;

/**
 * https://leetcode.cn/problems/abbreviating-the-product-of-a-range/description/
 * 
 * 一个区间内所有数乘积的缩写（困难）
 * 给你两个正整数 left 和 right ，满足 left <= right 。请你计算 闭区间 [left, right] 中所有整数的 乘积 。
 * 由于乘积可能非常大，你需要将它按照以下步骤 缩写 ：
 * 统计乘积中 后缀 0 的数目，并 移除 这些 0 ，将这个数目记为 C 。
 * 比方说，1000 中有 3 个后缀 0 ，546 中没有后缀 0 。
 * 将乘积中剩余数字的位数记为 d 。
 * 如果 d > 10 ，那么将乘积表示为 <pre>...<suf> 的形式，其中 <pre> 表示乘积最 开始 的 5 个数位，<suf> 表示删除后缀 0 之后 结尾的 5 个数位。
 * 如果 d <= 10 ，我们不对它做修改。
 * 比方说，我们将 1234567654321 表示为 12345...54321 ，但是 1234567 仍然表示为 1234567 。
 * 最后，将乘积表示为 字符串 "<pre>...<suf>eC" 。
 * 比方说，12345678987600000 被表示为 "12345...89876e5" 。
 * 请你返回一个字符串，表示 闭区间 [left, right] 中所有整数 乘积 的 缩写 。
 * 
 * 示例：
 * 输入：left = 1, right = 4
 * 输出："24e0"
 * 
 * 输入：left = 2, right = 11
 * 输出："399168e2"
 * 
 * 输入：left = 371, right = 375
 * 输出："7219856259e3"
 * 
 * 1 <= left <= right <= 10^4
 */

/**
 * 一句话题解：
 * 尾0  质因数2或5个数较小值;
 * 前缀 避免精度损失，使用double，最后再取整;
 * 后缀 取模，用long即可
 * 
 * 对于计算产生的大数，取模会简化运算。
 */

 class Solution {
    private final long MULTI_LIMIT = (long)(1e10);
    private final int DIGIT_LIMIT = (int)(1e5);
    public String abbreviateProduct(int left, int right) {
        long multi = 1;
        boolean isExceedLimit = false;

        //计算尾0和后缀
        int cnt2 = 0;
        int cnt5 = 0;
        long multi2 = 1;
        for(int i=left; i<=right; i++) {
            int x = i;
            while(x % 2 == 0) {
                cnt2++;
                x /= 2;
            }
            while(x % 5 == 0) {
                cnt5++;
                x /= 5;
            }
            if(!isExceedLimit) {
                if(multi >= MULTI_LIMIT / x) {
                    isExceedLimit = true;
                }
                else {
                    multi *= x;
                }
            }
            multi2 *= x;
            multi2 %= DIGIT_LIMIT;
        }
        int cnt = Math.min(cnt2, cnt5);
        while(cnt2 > cnt) {
            multi2 *= 2;
            multi2 %= DIGIT_LIMIT;
            if(!isExceedLimit) {
                if(multi >= MULTI_LIMIT / 2) {
                    isExceedLimit = true;
                }
                else {
                    multi *= 2;
                }
            }
            cnt2--;
        }
        while(cnt5 > cnt) {
            multi2 *= 5;
            multi2 %= DIGIT_LIMIT;
            if(!isExceedLimit) {
                if(multi >= MULTI_LIMIT / 5) {
                    isExceedLimit = true;
                }
                else {
                    multi *= 5;
                }
            }
            cnt5--;
        }

        StringBuilder sb = new StringBuilder();
        if(!isExceedLimit) {
            return sb.append(multi).append("e").append(cnt).toString();
        }
        
        //计算前缀
        double pre = 1;
        for(int i=left; i<=right; i++) {
            pre *= i;
            while(pre > DIGIT_LIMIT) {
                pre /= 10;
            }
        }
        int len = String.valueOf(multi2).length();
        String suffix = "0".repeat(5-len) + String.valueOf(multi2);
        return sb.append(String.valueOf((int)pre).substring(0, 5)).append("...")
        .append(suffix).append("e").append(cnt).toString();
    }
}