package leet.code.pointer;

/**
 * 283. 移动零
 * <p></p>
 * https://leetcode-cn.com/problems/move-zeroes/
 * <pre>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 示例 2:
 * 输入: nums = [0]
 * 输出: [0]
 *
 * 提示:
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 * 进阶：你能尽量减少完成的操作次数吗？
 * </pre>
 * <pre>
 * 标签：数组
 * 整数数组
 * 将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。右指针需要从左侧开始寻找非零元素
 * </pre>
 *
 * @author guangyi
 */
public class MoveZeroes {

    public static void moveZeroes(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return;
        }
        // 双指针
        int left = 0;
        int right = 0;
        while (left < length && right < length) {
            // 寻找最左侧零元素
            while (left < length && nums[left] != 0) {
                left++;
            }
            // 寻找最左侧零元素后面的最左侧非零元素
            // 最左侧零元素前面都是非零元素
            right = left + 1;
            while (right < length && nums[right] == 0) {
                right++;
            }
            if (right < length) {
                // 互换元素
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;

                left++;
                right++;
            }
        }
    }
}
