package 第267场周赛;

import java.lang.String;
/**
 * https://leetcode.cn/problems/decode-the-slanted-ciphertext/
 * 
 * 解码斜向换位密码(中等)
 * 
 * 字符串 originalText 使用 斜向换位密码 ，经由 行数固定 为 rows 的矩阵辅助，加密得到一个字符串 encodedText 。
 * 
 * originalText 先按从左上到右下的方式放置到矩阵中。
 * 先填充蓝色单元格，接着是红色单元格，然后是黄色单元格，以此类推，直到到达 originalText 末尾。
 * 箭头指示顺序即为单元格填充顺序。所有空单元格用 ' ' 进行填充。
 * 矩阵的列数需满足：用 originalText 填充之后，最右侧列 不为空 。
 * 接着按行将字符附加到矩阵中，构造 encodedText 。
 * 先把蓝色单元格中的字符附加到 encodedText 中，接着是红色单元格，最后是黄色单元格。
 * 箭头指示单元格访问顺序。
 * 例如，如果 originalText = "cipher" 且 rows = 3 ，那么我们可以按下述方法将其编码：
 * 蓝色箭头标识 originalText 是如何放入矩阵中的，红色箭头标识形成 encodedText 的顺序。
 * 在上述例子中，encodedText = "ch   ie   pr" 。
 * 给你编码后的字符串 encodedText 和矩阵的行数 rows ，返回源字符串 originalText 。
 * 注意：originalText 不 含任何尾随空格 ' ' 。生成的测试用例满足 仅存在一个 可能的 originalText 。
 * 
 * 示例：
 * 输入：encodedText = "ch   ie   pr", rows = 3
 * 输出："cipher"
 * 
 * 输入：encodedText = "iveo    eed   l te   olc", rows = 4
 * 输出："i love leetcode"
 * 
 * 输入：encodedText = "coding", rows = 1
 * 输出："coding"
 * 
 * 输入：encodedText = " b  ac", rows = 2
 * 输出：" abc"
 */

/**
 * 一句话题解：
 * 找规律，要点：根据排列规律，行列位置计算索引，索引超出范围则遍历结束，去掉结尾的空格。
 * 
 * java11新增API：stripTrailing() 删除字符串末尾空格
 * 
 */

class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        char[] encodedArr = encodedText.toCharArray();
        int cols = encodedArr.length / rows;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<cols; i++) {
            for(int j=0; j<rows; j++) {
                int index = j * (cols+1) + i;
                if(index >= encodedArr.length) return sb.toString().stripTrailing();
                else sb.append(encodedArr[index]);
            }
        }
        return sb.toString().stripTrailing();
    }
}
