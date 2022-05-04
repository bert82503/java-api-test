package leet.code.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 6. Z 字形变换
 * <p></p>
 * https://leetcode-cn.com/problems/zigzag-conversion/
 * <pre>
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 *
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * 示例 1：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 *
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * 示例 3：
 * 输入：s = "A", numRows = 1
 * 输出："A"
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 * </pre>
 * <pre>
 * 找规律
 *
 * 对于n行的, s中的第i个字符：
 * 对余数进行判断
 * i%(2n-2) == 0 ----> row0
 * i%(2n-2) == 1 & 2n-2-1 ----> row1
 * i%(2n-2) == 2 & 2n-2-2 ----> row2
 * ...
 * i%(2n-2) == n-1 ----> row(n-1)
 * ==>
 * 对 k = i%(2n-2)进行判断
 * * k<=n-1时候，s[i]就属于第k行
 * * k>n-1时候，s[i]就属于2n-2-k行
 *
 * 最后将rows拼接起来就行了
 * </pre>
 *
 * @author guangyi
 */
public class ZigzagConversion {

    public static String convert(String str, int numRows) {
        if (numRows == 1) {
            return str;
        }
        int length = str.length();
        int capacity = length / 2;
        List<StringBuilder> result = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            result.add(new StringBuilder(capacity));
        }

        int mod = 2 * numRows - 2;
        for (int i = 0; i < length; i++) {
            char ch = str.charAt(i);
            int k = i % mod;
            if (k < numRows) {
                result.get(k).append(ch);
            } else {
                result.get(mod - k).append(ch);
            }
        }

        StringBuilder ans = new StringBuilder(length + 1);
        for (StringBuilder sb : result) {
            ans.append(sb);
        }
        return ans.toString();
    }
}
