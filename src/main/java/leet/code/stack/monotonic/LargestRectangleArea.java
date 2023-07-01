package leet.code.stack.monotonic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 84. 柱状图中最大的矩形
 * <p></p>
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * <pre>
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。
 * 每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * </pre>
 *
 * @author guangyi
 */
public class LargestRectangleArea {

    /**
     * 柱状图中最大的矩形
     * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/zhu-zhuang-tu-zhong-zui-da-de-ju-xing-by-leetcode-/
     * <pre>
     * 方法一：单调栈
     *
     * 思路
     * 我们归纳一下枚举「高」的方法：
     * * 首先我们枚举某一根柱子 i 作为高 h = heights[i]；
     * * 随后我们需要进行向左右两边扩展，使得扩展到的柱子的高度均不小于 h。
     *   换句话说，我们需要找到左右两侧最近的高度小于 h 的柱子，
     *   这样这两根柱子之间（不包括其本身）的所有柱子高度均不小于 h，并且就是 i 能够扩展到的最远范围。
     *
     * 那么我们先来看看如何求出一根柱子的左侧且最近的小于其高度的柱子。
     * 我们可以通过如下的一个结论来深入地进行思考：
     * 对于两根柱子 j0 以及 j1，如果 j0 < j1 并且 heights[j0] ≥ heights[j1]，
     * 那么对于任意的在它们之后出现的柱子 i（j1 < i），j0 一定不会是 i 左侧且最近的小于其高度的柱子。
     *
     * 换句话说，如果有两根柱子 j0 和 j1，其中 j0 在 j1 的左侧，并且 j0 的高度大于等于 j1，
     * 那么在后面的柱子 i 向左找小于其高度的柱子时，j1 会「挡住」j0，j0 就不会作为答案了。
     *
     * 这样以来，我们可以对数组从左向右进行遍历，同时维护一个「可能作为答案」的数据结构，其中按照从小到大的顺序存放了一些 j 值。
     *
     * 递增栈
     * * 栈中存放了 j 值。从栈底到栈顶，j 的值严格单调递增，同时对应的高度值也严格单调递增；
     * * 当我们枚举到第 i 根柱子时，我们从栈顶不断地移除 height[j] ≥ height[i] 的 j 值。
     *   在移除完毕后，栈顶的 j 值就一定满足 height[j] < height[i]，此时 j 就是 i 左侧且最近的小于其高度的柱子。
     *   * 这里会有一种特殊情况。如果我们移除了栈中所有的 j 值，那就说明 i 左侧所有柱子的高度都大于 height[i]，
     *     那么我们可以认为 i 左侧且最近的小于其高度的柱子在位置 j = −1，它是一根「虚拟」的、高度无限低的柱子。
     *     这样的定义不会对我们的答案产生任何的影响，我们也称这根「虚拟」的柱子为「哨兵」。
     * * 我们再将 i 放入栈顶。
     *
     * 栈中存放的元素具有单调性，这就是经典的数据结构「单调栈」。
     * </pre>
     *
     * 暴力解法、栈（单调栈、哨兵技巧）
     * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/bao-li-jie-fa-zhan-by-liweiwei1419/
     * <pre>
     * 方法二：以空间换时间，可以使用的数据结构是栈
     *
     * 要搞清楚这个过程，请大家一定要在纸上画图，搞清楚一些细节，这样在编码的时候就不容易出错了。
     * 记录什么信息呢？记录高度是不是可以呢？其实是不够的，因为计算矩形还需要计算宽度，很容易知道宽度是由下标确定的，
     * 记录了下标其实对应的高度就可以直接从输入数组中得出。因此，应该记录的是下标。
     *
     * 这个时候，还需要考虑的一个细节是，在确定一个柱形的面积的时候，
     * 除了右边要比当前严格小，其实还蕴含了一个条件，那就是左边也要比当前高度严格小。
     *
     * 那如果是左边的高度和自己相等怎么办呢？
     *
     * 因此我们确定当前柱形对应的宽度的左边界的时候，往回头看的时候，一定要找到第一个严格小于我们要确定的那个柱形的高度的下标。
     * </pre>
     *
     * 84. 柱状图中最大的矩形 题解
     * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/84-zhu-zhuang-tu-zhong-zui-da-de-ju-xing-nk72/
     * <pre>
     * 解题思路
     * 一个非常关键的模型是，对于一个柱子，最大的面积是其向两边延展，直到遇到比它矮的为止。
     * </pre>
     */
    public static int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int[] left = new int[len];
        int[] right = new int[len];

        // 单调递增栈
        Deque<Integer> indexMonoStack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            int height = heights[i];
            // 判定条件
            // 区别：小于等于栈顶元素，出栈
            while (!indexMonoStack.isEmpty() && height <= heights[indexMonoStack.getFirst()]) {
                // 出栈
                indexMonoStack.removeFirst();
            }
            // 结果计算
            // 求出一根柱子的左侧且最近的小于其高度的柱子
            // 这里的 -1 是哨兵
            left[i] = indexMonoStack.isEmpty() ? -1 : indexMonoStack.getFirst();
            // 入栈
            indexMonoStack.addFirst(i);
        }

        indexMonoStack.clear();
        for (int i = len - 1; i >= 0; i--) {
            int height = heights[i];
            // 判定条件
            // 区别：小于等于栈顶元素，出栈
            while (!indexMonoStack.isEmpty() && height <= heights[indexMonoStack.getFirst()]) {
                // 出栈
                indexMonoStack.removeFirst();
            }
            // 结果计算
            // 求出一根柱子的左侧且最近的小于其高度的柱子
            // 这里的 len 是哨兵
            right[i] = indexMonoStack.isEmpty() ? len : indexMonoStack.getFirst();
            // 入栈
            indexMonoStack.push(i);
        }

        // 最大面积
        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            maxArea = Math.max(maxArea, (right[i] - left[i] - 1) * heights[i]);
        }
        return maxArea;
    }
}
