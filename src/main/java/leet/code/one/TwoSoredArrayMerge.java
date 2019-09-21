package leet.code.one;

import static org.assertj.core.api.Assertions.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 88. 合并两个有序数组。
 * <pre>
 * 执行用时：0 ms，击败了 100.00% 的用户
 * 内存消耗：36.1 MB，击败了 84.99% 的用户
 * </pre>
 * https://leetcode-cn.com/problems/merge-sorted-array/
 *
 * @since 2019-09-22
 */
public class TwoSoredArrayMerge {
    /**
     * 考察点：
     * <pre>
     * 1.有序整数，升序 or 降序？
     * 2.有序数组合并技巧
     * </pre>
     * 时间复杂度：O(m + n)
     * 空间复杂度：O(m + n)
     */
    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n <= 0) {
            return;
        }
        if (m == 1 && n == 1) {
            nums1[1] = nums2[0];
        }
        int m1 = m - 1;
        int n2 = n - 1;
        // 升序
        boolean ascend = true;
        if (m >= 2) {
            ascend = nums1[m1] >= nums1[0];
        } else if (n >= 2) {
            ascend = nums2[n2] >= nums2[0];
        }
        int i = m + n - 1;
        for (; ; ) {
            if (m1 < 0 && n2 < 0) {
                // 无限循环的终止条件
                break;
            } else if (m1 >= 0 && n2 >= 0) {
                int num1 = nums1[m1];
                int num2 = nums2[n2];
                if (ascend) {
                    // 升序
                    if (num2 >= num1) {
                        nums1[i] = num2;
                        i--;
                        n2--;
                    } else {
                        nums1[i] = num1;
                        i--;
                        m1--;
                    }
                } else {
                    // 降序
                    if (num2 <= num1) {
                        nums1[i] = num2;
                        i--;
                        n2--;
                    } else {
                        nums1[i] = num1;
                        i--;
                        m1--;
                    }
                }
            } else if (n2 >= 0) {
                do {
                    // 将 nums2 数组复制到 nums1
                    nums1[i] = nums2[n2];
                    i--;
                    n2--;
                } while (n2 >= 0);
            } else {
                // 只剩 nums1 数组，无须复制
                m1 = -1;
            }
        }
    }

    @Test(dataProvider = "mergeTestData")
    public void mergeTest(int[] nums1, int m, int[] nums2, int n, int[] expected) {
        merge(nums1, m, nums2, n);
        assertThat(nums1).isEqualTo(expected);
    }

    /**
     * 测试用例【重要】
     */
    @DataProvider(name = "mergeTestData")
    public static Object[][] mergeTestData() {
        return new Object[][]{
                {new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3, new int[]{1, 2, 2, 3, 5, 6}},
                {new int[]{3, 2, 1, 0, 0, 0}, 3, new int[]{6, 5, 2}, 3, new int[]{6, 5, 3, 2, 2, 1}},
                {new int[]{1, 1, 0, 0, 0}, 2, new int[]{2, 2, 2}, 3, new int[]{1, 1, 2, 2, 2}},
                {new int[]{2, 2, 2, 0, 0}, 3, new int[]{1, 1}, 2, new int[]{1, 1, 2, 2, 2}},
                {new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3, new int[]{1, 1, 2, 2, 3, 3}},
                {new int[]{3, 2, 1, 0, 0, 0}, 3, new int[]{3, 2, 1}, 3, new int[]{3, 3, 2, 2, 1, 1}},
                {new int[]{1, 3, 0, 0, 0}, 2, new int[]{1, 2, 3}, 3, new int[]{1, 1, 2, 3, 3}},
                {new int[]{3, 2, 1, 0, 0}, 3, new int[]{3, 1}, 2, new int[]{3, 3, 2, 1, 1}},
                {new int[]{3, 2, 1, 0, 0}, 3, new int[]{2, 1}, 2, new int[]{3, 2, 2, 1, 1}},
                // 互为交叉
                {new int[]{1, 3, 5, 0, 0, 0}, 3, new int[]{2, 4, 6}, 3, new int[]{1, 2, 3, 4, 5, 6}},
        };
    }

}
