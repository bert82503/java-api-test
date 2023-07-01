package leet.code.string;

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
 *
 * @author guangyi
 */
public class ZigzagConversion {

    /**
     * Z 字形变换
     * https://leetcode-cn.com/problems/zigzag-conversion/solution/z-zi-xing-bian-huan-by-leetcode-solution-4n3u/
     * <pre>
     * 方法二：压缩矩阵空间
     * 方法一中的矩阵有大量的空间没有被使用，能否优化呢？
     * 注意到每次往矩阵的某一行添加字符时，都会添加到该行上一个字符的右侧，且最后组成答案时只会用到每行的非空字符。
     * 因此我们可以将矩阵的每行初始化为一个空列表，每次向某一行添加字符时，添加到该行的列表末尾即可。
     * </pre>
     *
     * Z 字形变换（清晰图解）
     * https://leetcode-cn.com/problems/zigzag-conversion/solution/zzi-xing-bian-huan-by-jyd/
     * <pre>
     * 解题思路：
     * 题目理解：
     * 字符串 s 是以 Z 字形为顺序存储的字符串，目标是按行打印。
     * 因此，解决方案为：模拟这个行索引的变化，在遍历 s 中把每个字符填到正确的行 res[i] 。
     *
     * 算法流程： 按顺序遍历字符串 s；
     * 1.res[i] += c： 把每个字符 c 填入对应行 si ；
     * 2.i += flag： 更新当前字符 c 对应的行索引；
     * 3.flag = -flag： 在达到 Z 字形转折点时，执行反向。
     *
     * 复杂度分析：
     * 时间复杂度 O(N) ：遍历一遍字符串 s；
     * 空间复杂度 O(N) ：各行字符串共占用 O(N) 额外空间。
     * </pre>
     *
     * 6. Z 字形变换
     * https://leetcode-cn.com/problems/zigzag-conversion/solution/6-z-zi-xing-bian-huan-c-c-by-bian-bian-xiong/
     * <pre>
     * 算法：
     * 如上图所示，我们可以发现：
     * 1.当前行 curRow 为 0 或 n-1 时，箭头发生反向转折。
     * 方法一： 从左到右按箭头方向迭代 s ，将每个字符添加到合适的行。之后从上到下遍历行即可。
     * 因为只需遍历一次，所以时间复杂度为 O(n)
     * </pre>
     *
     * 画解算法：6. Z 字形变换
     * https://leetcode-cn.com/problems/zigzag-conversion/solution/hua-jie-suan-fa-6-z-zi-xing-bian-huan-by-guanpengc/
     * <pre>
     * 思路
     * * 标签：字符串
     * * 整体的思路是遍历字符串，遍历过程中将每行都看成新的字符串构成字符串数组，最后再将该数组拼接起来即可
     * * 如果 numRows=1 则说明当前字符串即为结果，直接返回
     * * 否则整个字符串需要经历，向下向右，向下向右，这样的反复循环过程，设定 down 变量表示是否向下，loc 变量表示当前字符串数组的下标
     * * 如果 down 为 true，则 loc+=1，字符串数组下标向后移动，将当前字符加入当前字符串中
     * * 如果 down 为 false，则表示向右，则 loc−=1，字符串数组下标向前移动，将当前字符加入当前字符串中
     * * 时间复杂度：O(n)，n为字符串s的长度
     * </pre>
     */
    public static String convert(String str, int numRows) {
        int length = str.length();
        if (numRows == 1 || numRows >= length) {
            return str;
        }
        int capacity = length / 2;
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder(capacity);
        }

        int curRow = 0;
        // 巧妙：下标方向，向下(+1)or向右(-1)
        // 下标步长
        int down = -1;
        for (int i = 0; i < length; i++) {
            char ch = str.charAt(i);
            rows[curRow].append(ch);
            // 当前行curRow为0或numRows-1时，箭头发生反向转折
            if (curRow == 0 || curRow == numRows - 1) {
                // 神奇的方向转换
                down = -down;
            }
            curRow += down;
        }

        StringBuilder result = new StringBuilder(length + 1);
        // 从上到下遍历行
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }

    /**
     * Z 字形变换
     * https://leetcode-cn.com/problems/zigzag-conversion/solution/z-zi-xing-bian-huan-by-leetcode-solution-4n3u/
     * <pre>
     * 方法二：压缩矩阵空间
     * 方法一中的矩阵有大量的空间没有被使用，能否优化呢？
     * 注意到每次往矩阵的某一行添加字符时，都会添加到该行上一个字符的右侧，且最后组成答案时只会用到每行的非空字符。
     * 因此我们可以将矩阵的每行初始化为一个空列表，每次向某一行添加字符时，添加到该行的列表末尾即可。
     *
     * 方法三：直接构造
     * 我们来研究方法一中矩阵的每个非空字符会对应到 s 的哪个下标（记作 idx），从而直接构造出答案。
     * 由于 Z 字形变换的周期为 t = 2r−2，因此对于矩阵第一行的非空字符，其对应的 idx 均为 t 的倍数，即 idx ≡ 0 (mod t)；
     * 同理，对于矩阵最后一行的非空字符，应满足 idx ≡ r−1 (mod t)。
     * 对于矩阵的其余行（行号设为 i），每个周期内有两个字符，第一个字符满足 idx ≡ i (mod t)，
     * 第二个字符满足 idx ≡ t−i(mod t)。
     * > 代码可读性差
     * </pre>
     */
    public static String convert_DirectBuild(String str, int numRows) {
        int length = str.length();
        if (numRows == 1 || numRows >= length) {
            return str;
        }
        StringBuilder result = new StringBuilder(length + 1);
        int step = numRows * 2 - 2;
        // 枚举矩阵的行
        for (int i = 0; i < numRows; i++) {
            // 枚举每个周期的起始下标
            for (int j = 0; j + i < length; j += step) {
                // 当前周期的第一个字符
                result.append(str.charAt(j + i));
                if (0 < i && i < numRows - 1 && j + step - i < length) {
                    // 当前周期的第二个字符
                    result.append(str.charAt(j + step - i));
                }
            }
        }
        return result.toString();
    }
}
