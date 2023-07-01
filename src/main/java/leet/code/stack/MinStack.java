package leet.code.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 155. 最小栈
 * <p></p>
 * https://leetcode-cn.com/problems/min-stack/
 * <pre>
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *     push(x) -- 将元素 x 推入栈中。
 *     pop() -- 删除栈顶的元素。
 *     top() -- 获取栈顶元素。
 *     getMin() -- 检索栈中的最小元素。
 *
 * 提示：
 * -2^31 <= val <= 2^31 - 1
 * pop、top 和 getMin 操作总是在 非空栈 上调用
 * push, pop, top, and getMin最多被调用 3 * 10^4 次
 * </pre>
 *
 * 面试题 03.02. 栈的最小值
 * <p></p>
 * https://leetcode-cn.com/problems/min-stack-lcci/
 * <pre>
 * 请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。
 *
 * 执行push、pop和min操作的时间复杂度必须为O(1)。
 * </pre>
 *
 * @author guangyi
 */
public class MinStack {

    private static class Element {
        /** 元素值 */
        private final int value;
        /** 最小值 */
        private final int min;

        public Element(int value, int min) {
            this.value = value;
            this.min = min;
        }
    }

    /**
     * 数据值和最小值的同步栈，入栈和出栈操作是原子性的。
     * <p></p>
     * 使用"双端队列"模拟"栈"
     */
    private final Deque<Element> deque;

    /** initialize your data structure here. */
    public MinStack() {
        deque = new LinkedList<>();
    }

    public void push(int x) {
        Element element;
        if (deque.isEmpty()) {
            element = new Element(x, x);
        } else {
            element = new Element(x, Math.min(x, deque.peekFirst().min));
        }
        deque.offerFirst(element);
    }

    public void pop() {
        if (deque.isEmpty()) {
            return;
        }
        deque.pollFirst();
    }

    public int top() {
        if (deque.isEmpty()) {
            return 0;
        }
        return deque.peekFirst().value;
    }

    public int getMin() {
        if (deque.isEmpty()) {
            return 0;
        }
        return deque.peekFirst().min;
    }
}
