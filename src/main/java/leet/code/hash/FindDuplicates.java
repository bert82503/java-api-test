package leet.code.hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 442. 数组中重复的数据
 * <p></p>
 * https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/
 * <pre>
 * 给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 一次 或 两次 。
 * 请你找出所有出现 两次 的整数，并以数组形式返回。
 *
 * 你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题。
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * nums 中的每个元素出现 一次 或 两次
 * </pre>
 *
 * @author guangyi
 */
public class FindDuplicates {

    public static List<Integer> findDuplicates(int[] nums) {
        int length = nums.length;
        // 对数组元素减1，将所有整数都在范围 [1, n] 内拉到范围 [0, n-1] 内
        for (int i = 0; i < length; i++) {
            nums[i] -= 1;
        }
        Set<Integer> result = new HashSet<>(length / 2 * 4 / 3 + 1);
        int i = 0;
        while (i < length) {
            int num = nums[i];
            if (num == i) {
                // 已交换
                i++;
                continue;
            }
            if (nums[num] == num) {
                // 发现一个重复的数字
                // 还原减1的整数
                result.add(num + 1);
                i++;
            } else {
                // 原地交换
                nums[i] = nums[num];
                nums[num] = num;
            }
        }
        return new ArrayList<>(result);
    }
}
