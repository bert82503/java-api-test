package leet.code.pointer;

/**
 * 11. 盛最多水的容器
 * <p></p>
 * https://leetcode-cn.com/problems/container-with-most-water/
 * <pre>
 * 给定一个长度为 n 的整数数组 height 。
 * 有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 * </pre>
 * 特性：
 * 1. 全局最优解
 *
 * @author guangyi
 */
public class MaxArea {
    /**
     * 方法一：双指针
     * <p></p>
     * 盛最多水的容器
     * https://leetcode-cn.com/problems/container-with-most-water/solution/sheng-zui-duo-shui-de-rong-qi-by-leetcode-solution/
     * <pre>
     * 说明
     * 本题是一道经典的面试题，最优的做法是使用「双指针」。
     * 如果读者第一次看到这题，不一定能想出双指针的做法。
     * </pre>
     * <pre>
     * 分析
     * 此时我们需要移动一个指针。移动哪一个呢？直觉告诉我们，应该移动对应数字较小的那个指针。
     * 这是因为，由于容纳的水量是由
     *     两个指针指向的数字中较小值 * 指针之间的距离
     * 决定的。
     * 如果我们移动数字较大的那个指针，那么前者「两个指针指向的数字中较小值」不会增加，后者「指针之间的距离」会减小，那么这个乘积会减小。
     * 因此，我们移动数字较大的那个指针是不合理的。
     * 因此，我们移动 数字较小的那个指针。
     * </pre>
     * <pre>
     * 证明
     * 为什么双指针的做法是正确的？
     *
     * > 双指针代表了什么？
     * 双指针代表的是 可以作为容器边界的所有位置的范围。
     * 在一开始，双指针指向数组的左右边界，表示 数组中所有的位置都可以作为容器的边界，因为我们还没有进行过任何尝试。
     * 在这之后，我们每次将 对应的数字较小的那个指针 往 另一个指针 的方向移动一个位置，就表示我们认为 这个指针不可能再作为容器的边界了。
     *
     * > 为什么对应的数字较小的那个指针不可能再作为容器的边界了？
     * 在上面的分析部分，我们对这个问题有了一点初步的想法。这里我们定量地进行证明。
     *
     * 这样以来，我们将问题的规模减小了 11，被我们丢弃的那个位置就相当于消失了。
     * 此时的左右指针，就指向了一个新的、规模减少了的问题的数组的左右边界。
     * 因此，我们可以继续像之前 考虑第一步 那样考虑这个问题：
     * * 求出当前双指针对应的容器的容量；
     * * 对应数字较小的那个指针以后不可能作为容器的边界了，将其丢弃，并移动对应的指针。
     *
     * > 最后的答案是什么？
     * 答案就是我们每次以双指针为左右边界（也就是「数组」的左右边界）计算出的容量中的最大值。
     * </pre>
     * <pre>
     * 复杂度分析
     * 时间复杂度：O(N)，双指针总计最多遍历整个数组一次。
     * 空间复杂度：O(1)，只需要额外的常数级别的空间。
     * </pre>
     * 盛最多水的容器（双指针，清晰图解）
     * https://leetcode-cn.com/problems/container-with-most-water/solution/container-with-most-water-shuang-zhi-zhen-fa-yi-do/
     * <pre>
     * 设两指针 i , j ，指向的水槽板高度分别为 h[i] , h[j] ，此状态下水槽面积为 S(i, j) 。
     * 由于可容纳水的高度由两板中的 短板 决定，因此可得如下 面积公式：
     *     S(i, j) = min(h[i], h[j]) × (j−i)
     * 在每个状态下，无论长板或短板向中间收窄一格，都会导致水槽 底边宽度 -1​ 变短：
     * * 若向内 移动短板 ，水槽的短板 min(h[i], h[j]) 可能变大，因此下个水槽的面积 可能增大 。
     * * 若向内 移动长板 ，水槽的短板 min(h[i], h[j])​ 不变或变小，因此下个水槽的面积 一定变小 。
     * 因此，初始化双指针分列水槽左右两端，循环每轮将短板向内移动一格，并更新面积最大值，直到两指针相遇时跳出；即可获得最大面积。
     * </pre>
     *
     * 双指针解法：理解正确性、图解原理
     * https://leetcode-cn.com/problems/container-with-most-water/solution/on-shuang-zhi-zhen-jie-fa-li-jie-zheng-que-xing-tu/
     * <pre>
     * 为什么双指针往中间移动时，不会漏掉某些情况呢？
     * 如果没有真正理解题目，即使一次对着答案做出来了，再次遇到这个题目，还是可能做不出来。
     * 要理解这道题的正确性和原理，需要从背后的缩减搜索空间的思想去考虑题解。
     *
     * 用一句话概括双指针解法的要点：指针每一次移动，都意味着排除掉了一个柱子。
     *
     * 缩减搜索空间的思想
     * </pre>
     *
     * @param height 整数数组
     * @return 返回容器可以储存的最大水量
     */
    public static int maxArea(int[] height) {
        // 容器储存的最大水量
        int maxArea = Integer.MIN_VALUE;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int x = height[left];
            int y = height[right];
            // 容器储存的水量计算
            int area = Math.min(x, y) * (right - left);
            maxArea = Math.max(maxArea, area);
            // 移动短板
            if (x <= y) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
