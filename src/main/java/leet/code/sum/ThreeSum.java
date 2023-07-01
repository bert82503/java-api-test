package leet.code.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 15. 三数之和
 * <p></p>
 * https://leetcode-cn.com/problems/3sum/
 * <pre>
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 * 请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 提示：
 * 0 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 * </pre>
 * <pre>
 * 规律/规则：
 * 1. 加减法等价转换，a + b + c = 0 => a + b = -c
 * 2. 不重复的三元组，等价于不重复的整数
 *
 * 特性：
 * 1. 本题的难点在于如何去除重复解
 * </pre>
 * <pre>
 * 「不重复」的本质是什么？我们保持三重循环的大框架不变，只需要保证：
 * 第二重循环枚举到的元素不小于当前第一重循环枚举到的元素；
 * 第三重循环枚举到的元素不小于当前第二重循环枚举到的元素。
 *
 * 也就是说，我们枚举的三元组 (a,b,c) 满足 a≤b≤c，保证了只有 (a,b,c) 这个顺序会被枚举到，而 (b,a,c)、(c,b,a) 等等这些不会，这样就减少了重复。
 * 要实现这一点，我们可以将数组中的元素从小到大进行排序，随后使用普通的三重循环就可以满足上面的要求。
 *
 * 同时，对于每一重循环而言，相邻两次枚举的元素不能相同，否则也会造成重复。
 *
 * 可以从小到大枚举 b，同时从大到小枚举 c，即第二重循环和第三重循环实际上是并列的关系。
 * 有了这样的发现，我们就可以保持第二重循环不变，而将第三重循环变成一个从数组最右端开始向左移动的指针。
 *
 * 这个方法就是我们常说的「双指针」，当我们需要枚举数组中的两个元素时，如果我们发现随着第一个元素的递增，第二个元素是递减的，那么就可以使用双指针的方法，
 * 将枚举的时间复杂度从 O(N^2) 减少至 O(N)。
 * </pre>
 *
 * @author guangyi
 */
public class ThreeSum {

    private static final int MIN_NUM_ARRAY_LEN = 3;

    // 方法一：排序 + 双指针

    /**
     * 排序 + 双指针
     *
     * 本题的难点在于如何去除重复解。
     * <pre>
     * 算法流程：
     * 1. 特判，对于数组长度 n，如果数组为 null 或者数组长度小于 3，返回 []。
     * 2. 对数组进行排序。
     * 3. 遍历排序后数组：
     *    * 若 nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 0，直接返回结果。
     *    * 对于重复元素：跳过，避免出现重复解
     *    * 令左指针 L=i+1，右指针 R=n-1，当 L<R 时，执行循环：
     *      * 当 nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,R 移到下一位置，寻找新的解
     *      * 若和大于 0，说明 nums[R] 太大，R 左移
     *      * 若和小于 0，说明 nums[L] 太小，L 右移
     *
     * 复杂度分析
     * * 时间复杂度：O(n^2)，数组排序 O(N logN)，遍历数组 O(n)，双指针遍历 O(n)，总体 O(N logN)+O(n)∗O(n)，O(n^2)
     * * 空间复杂度：O(1)
     * </pre>
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        // 1. 特判，对于数组长度 n，如果数组为 null 或者数组长度小于 3，返回 []
        if (nums == null || nums.length < MIN_NUM_ARRAY_LEN) {
            return Collections.emptyList();
        }
        // 2. 对数组进行排序
        Arrays.sort(nums);
        // 3. 遍历排序后数组：
        List<List<Integer>> result = new ArrayList<>(10);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > 0) {
                // 若 nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 0，直接返回结果
                return result;
            }
            if (i > 0 && num == nums[i - 1]) {
                // 对于重复元素：跳过，避免出现重复解
                continue;
            }
            // 令左指针 L=i+1，右指针 R=n-1，当 L<R 时，执行循环：
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = num + nums[left] + nums[right];
                if (sum == 0) {
                    // 当 nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,R 移到下一位置，寻找新的解
                    result.add(Arrays.asList(num, nums[left], nums[right]));
                    // 执行循环，判断左界和右界是否和下一位置重复，去除重复解
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    // 若和大于 0，说明 nums[R] 太大，R 左移
                    right--;
                } else {
                    // sum < 0
                    // 若和小于 0，说明 nums[L] 太小，L 右移
                    left++;
                }
            }
        }
        return result;
    }
}
