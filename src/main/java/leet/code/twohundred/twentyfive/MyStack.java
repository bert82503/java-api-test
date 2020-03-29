package leet.code.twohundred.twentyfive;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <a href="https://leetcode-cn.com/problems/implement-stack-using-queues/">
 *     用队列实现栈</a>
 *
 * <pre>
 * 使用队列实现栈的下列操作：
 *     push(x) -- 元素 x 入栈
 *     pop() -- 移除栈顶元素
 *     top() -- 获取栈顶元素
 *     empty() -- 返回栈是否为空
 * </pre>
 *
 * <pre>
 * 执行用时 : 0 ms, 在所有 Java 提交中击败了 100.00% 的用户
 * 内存消耗 : 37.3 MB, 在所有 Java 提交中击败了 5.40% 的用户
 * </pre>
 *
 * @author EdwardLee03
 * @since 2020-03-29
 */
@SuppressWarnings("unused")
public class MyStack {

    /**
     * 入栈操作，push
     */
    private Deque<Integer> in;
    /**
     * 出栈操作，先进先出(LIFO)，pop，top
     *
     * 当其为空时，才从"in"栈转移全部数据到"out"栈，从back到front，满足LIFO的特性。
     *
     * 队列FIFO ---(in:反向->out)---> 栈LIFO
     */
    private Deque<Integer> out;

    /** Initialize your data structure here. */
    public MyStack() {
        in = new LinkedList<>();
        out = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        if (in.isEmpty()) {
            out.offerLast(x);
        } else {
            in.offerFirst(x);
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.offerFirst(in.pollLast());
            }
        }
        if (out.isEmpty()) {
            return 0;
        }
        return out.pollLast();
    }

    /** Get the top element. */
    public int top() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.offerFirst(in.pollLast());
            }
        }
        if (out.isEmpty()) {
            return 0;
        }
        return out.peekLast();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return out.isEmpty() && in.isEmpty();
    }
}
