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

    /**
     * 移动零
     * https://leetcode-cn.com/problems/move-zeroes/solution/yi-dong-ling-by-leetcode-solution/
     * <pre>
     * 方法一：双指针
     * 思路及解法
     * 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
     * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
     *
     * 注意到以下性质：
     * 1.左指针左边均为非零数；
     * 2.右指针左边直到左指针处均为零。
     *
     * 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
     * </pre>
     *
     * 动画演示 283.移动零
     * https://leetcode-cn.com/problems/move-zeroes/solution/dong-hua-yan-shi-283yi-dong-ling-by-wang_ni_ma/
     * <pre>
     * 一次遍历
     * 这里参考了快速排序的思想，快速排序首先要确定一个待分割的元素做中间点x，
     * 然后把所有小于等于x的元素放到x的左边，大于x的元素放到其右边。
     * 这里我们可以用0当做这个中间点，把不等于0(注意题目没说不能有负数)的放到中间点的左边，等于0的放到其右边。
     * 这个中间点就是0本身，所以实现起来比快速排序简单很多，我们使用两个指针i和j，只要nums[i]!=0，我们就交换nums[i]和nums[j]。
     * </pre>
     */
    public static void moveZeroes(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return;
        }
        // 双指针
        // 左指针指向当前已经处理好的序列的尾部
        int left = 0;
        // 右指针指向待处理序列的头部
        int right = 0;
        while (right < length) {
            if (nums[right] != 0) {
                if (left != right) {
                    swap(nums, left, right);
                }
                left++;
            }
            right++;
        }
    }

    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
