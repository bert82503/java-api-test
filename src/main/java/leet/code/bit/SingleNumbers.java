package leet.code.bit;

/**
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 * <p></p>
 * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/
 * <pre>
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。
 *
 * 要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 * 限制：
 * 2 <= nums.length <= 10000
 * </pre>
 *
 * @author guangyi
 */
public class SingleNumbers {

    /**
     * 数组中数字出现的次数
     * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/solution/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-by-leetcode/
     * <pre>
     * 方法一：分组异或
     * 思路
     *
     * 让我们先来考虑一个比较简单的问题：
     * 如果除了一个数字以外，其他数字都出现了两次，那么如何找到出现一次的数字？
     *
     * 答案很简单：全员进行异或操作即可。
     * 考虑异或操作的性质：对于两个操作数的每一位，相同结果为 0，不同结果为 1。
     * 那么在计算过程中，成对出现的数字的所有位会两两抵消为 0，最终得到的结果就是那个出现了一次的数字。
     *
     * 那么这一方法如何扩展到找出两个出现一次的数字呢？
     * 如果我们可以把所有数字分成两组，使得：
     * 1. 两个只出现一次的数字在不同的组中；
     * 2. 相同的数字会被分到相同的组中。
     * 那么对两个组分别进行异或操作，即可得到答案的两个数字。这是解决这个问题的关键。
     *
     * 那么如何实现这样的分组呢？
     * 记这两个只出现了一次的数字为 a 和 b，那么所有数字异或的结果就等于 a 和 b 异或的结果，我们记为 x。
     *
     * 在实际操作的过程中，我们拿到序列的异或和 x 之后，对于这个「位」是可以任取的，只要它满足 xi = 1。
     * 但是为了方便，这里的代码选取的是「不为 0 的最低位」，当然你也可以选择其他不为 0 的位置。
     * 至此，答案已经呼之欲出了。
     *
     * 算法
     * 1. 先对所有数字进行一次异或，得到两个出现一次的数字的异或值。
     * 2. 在异或结果中找到任意为 1 的位。
     * 3. 根据这一位对所有的数字进行分组。
     * 4. 在每个组内进行异或操作，得到两个数字。
     * </pre>
     *
     * 剑指 Offer 56 - I. 数组中数字出现的次数（位运算，清晰图解）
     * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/solution/jian-zhi-offer-56-i-shu-zu-zhong-shu-zi-tykom/
     * <pre>
     * 本题难点： 数组 nums 有 两个 只出现一次的数字，因此无法通过异或直接得到这两个数字。
     * 设两个只出现一次的数字为 x , y ，由于 x = y ，则 x 和 y 二进制至少有一位不同（即分别为 0 和 1 ），
     * 根据此位可以将 nums 拆分为分别包含 x 和 y 的两个子数组。
     *
     * 易知两子数组都满足 「除一个数字之外，其他数字都出现了两次」。
     * 因此，仿照以上简化问题的思路，分别对两子数组遍历执行异或操作，即可得到两个只出现一次的数字 x, y 。
     *
     * 算法流程
     * 1. 遍历 nums 执行异或
     * 2. 循环左移计算 m
     * 3. 拆分 nums 为两个子数组
     * 4. 分别遍历两个子数组执行异或
     * 5. 返回值
     *    返回只出现一次的数字 x, y 即可。
     * </pre>
     */
    public static int[] singleNumbers(int[] nums) {
        // 1.先对所有数字进行一次异或，得到两个出现一次的数字的异或值
        int res = 0;
        for (int num : nums) {
            // 异或运算
            res ^= num;
        }

        // 2.在异或结果中找到任意为 1 的位
        int mask = 1;
        while ((res & mask) == 0) {
            // 循环左移
            mask <<= 1;
        }

        int x = 0;
        int y = 0;
        for (int num : nums) {
            // 3.根据这一位对所有的数字进行分组
            // 4.在每个组内进行异或操作，得到两个数字
            if ((num & mask) != 0) {
                x ^= num;
            } else {
                y ^= num;
            }
        }
        return new int[]{x, y};
    }
}
