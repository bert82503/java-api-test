package leet.code.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 225. 用队列实现栈
 * <p></p>
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 * <pre>
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 *
 * 使用队列实现栈的下列操作：
 *     push(x) -- 将元素 x 压入栈顶
 *     pop() -- 移除并返回栈顶元素
 *     top() -- 返回栈顶元素
 *     empty() -- 如果栈是空的，返回 true ；否则，返回 false 。
 * </pre>
 *
 * @author edwardlee03
 */
@SuppressWarnings("unused")
public class MyStack {

    /**
     * deque（双端队列）来模拟一个队列或栈
     */
    private Deque<Integer> deque;

    /** Initialize your data structure here. */
    public MyStack() {
        deque = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        deque.addFirst(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (deque.isEmpty()) {
            return 0;
        }
        return deque.removeFirst();
    }

    /** Get the top element. */
    public int top() {
        if (deque.isEmpty()) {
            return 0;
        }
        return deque.getFirst();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return deque.isEmpty();
    }
}
