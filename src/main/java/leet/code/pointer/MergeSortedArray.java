package leet.code.pointer;

/**
 * 88. 合并两个有序数组
 * <p></p>
 * https://leetcode-cn.com/problems/merge-sorted-array/
 * <pre>
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 *
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。
 * 为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。
 * nums2 的长度为 n 。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 *
 * 示例 2：
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * 解释：需要合并 [1] 和 [] 。
 * 合并结果是 [1] 。
 *
 * 示例 3：
 * 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
 * 输出：[1]
 * 解释：需要合并的数组是 [] 和 [1] 。
 * 合并结果是 [1] 。
 * 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
 *
 * 提示：
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -10^9 <= nums1[i], nums2[j] <= 10^9
 *
 * 进阶：你可以设计实现一个时间复杂度为 O(m + n) 的算法解决此问题吗？
 * </pre>
 * <pre>
 * 1.认识题目
 * 两个有序整数数组
 * 有序，非递减顺序
 * </pre>
 *
 * @author guangyi
 */
public class MergeSortedArray {

    /**
     * 合并两个有序数组
     * https://leetcode-cn.com/problems/merge-sorted-array/solution/he-bing-liang-ge-you-xu-shu-zu-by-leetco-rrb0/
     * <pre>
     * 方法二：双指针
     * 算法
     * 方法一没有利用数组 nums1 与 nums2 已经被排序的性质。
     * 为了利用这一性质，我们可以使用双指针方法。
     * 这一方法将两个数组看作队列，每次从两个数组头部取出比较小的数字放到结果中。
     *
     * 方法三：逆向双指针
     * 算法
     * 方法二中，之所以要使用临时变量，是因为如果直接合并到数组 nums1 中，nums1 中的元素可能会在取出之前被覆盖。
     * 那么如何直接避免覆盖 nums1 中的元素呢？观察可知，nums1 的后半部分是空的，可以直接覆盖而不会影响结果。
     * 因此，可以指针设置为从后向前遍历，每次取两者之中的较大者放进 nums1 的最后面。
     *
     * 永远成立，因此 p1 后面的位置永远足够容纳被插入的元素，不会产生 p1 的元素被覆盖的情况。
     * </pre>
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        // 从右向左遍历，避免数组复制移动元素
        int m1Index = m - 1;
        int n2Index = n - 1;
        int tailIndex = m + n - 1;
        int cur;
        while (m1Index >= 0 || n2Index >= 0) {
            if (m1Index == -1) {
                // nums1合并完成
                cur = nums2[n2Index--];
            } else if (n2Index == -1) {
                // nums2合并完成
                cur = nums1[m1Index--];
            } else if (nums2[n2Index] >= nums1[m1Index]) {
                cur = nums2[n2Index--];
            } else {
                cur = nums1[m1Index--];
            }
            nums1[tailIndex--] = cur;
        }
    }
}
