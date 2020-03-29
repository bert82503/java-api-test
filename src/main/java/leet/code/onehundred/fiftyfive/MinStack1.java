package leet.code.onehundred.fiftyfive;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <a href="最小栈">https://leetcode-cn.com/problems/min-stack/</a>
 *
 * <pre>
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *     push(x) -- 将元素 x 推入栈中。
 *     pop() -- 删除栈顶的元素。
 *     top() -- 获取栈顶元素。
 *     getMin() -- 检索栈中的最小元素。
 * </pre>
 *
 * <pre>
 * 执行用时: 7 ms, 在所有 Java 提交中击败了 88.42% 的用户
 * 内存消耗: 41.2 MB, 在所有 Java 提交中击败了 16.60% 的用户
 * </pre>
 *
 * @author EdwardLee03
 * @since 2020-03-25
 */
public class MinStack1 {

    /**
     * 数据栈和最小栈同步，存在入栈和出栈操作非原子性。
     *
     * 使用"双端队列"模拟"栈"（基本类型与包装类型存在自动解包装）
     */
    private Deque<Integer> data;
    private Deque<Integer> min;

    /** initialize your data structure here. */
    public MinStack1() {
        data = new LinkedList<>();
        min = new LinkedList<>();
    }

    public void push(int x) {
        // 非原子性操作
        data.offerFirst(x);
        if (min.isEmpty() || min.peekFirst() >= x) {
            min.offerFirst(x);
        } else {
            min.offerFirst(min.peekFirst());
        }
    }

    public void pop() {
        if (data.isEmpty()) {
            return;
        }
        // 非原子性操作
        data.pollFirst();
        min.pollFirst();
    }

    public int top() {
        if (data.isEmpty()) {
            return 0;
        }
        return data.peekFirst();
    }

    public int getMin() {
        if (min.isEmpty()) {
            return 0;
        }
        return min.peekFirst();
    }
}
