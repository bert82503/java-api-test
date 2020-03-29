package leet.code.twohundred.thirtytwo;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <a href="https://leetcode-cn.com/problems/implement-queue-using-stacks/">
 *     用栈实现队列</a>
 *
 * <pre>
 * 使用栈实现队列的下列操作：
 *     push(x) -- 将一个元素放入队列的尾部。
 *     pop() -- 从队列首部移除元素。
 *     peek() -- 返回队列首部的元素。
 *     empty() -- 返回队列是否为空。
 * </pre>
 *
 * <pre>
 * 执行用时: 0 ms, 在所有 Java 提交中击败了 100.00% 的用户
 * 内存消耗: 37.4 MB, 在所有 Java 提交中击败了 5.64% 的用户
 * </pre>
 *
 * @author EdwardLee03
 * @since 2020-03-29
 */
@SuppressWarnings("unused")
public class MyQueue {

    /**
     * 入队列操作，push
     */
    private Deque<Integer> in;
    /**
     * 出队列操作，先进先出(FIFO)，pop，peek
     *
     * 当其为空时，才从"in"队列转移全部数据到"out"队列，满足FIFO的特性。
     *
     * 栈LIFO ---(in->out)---> 队列FIFO
     */
    private Deque<Integer> out;

    /** Initialize your data structure here. */
    public MyQueue() {
        in = new LinkedList<>();
        out = new LinkedList<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        in.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        if (out.isEmpty()) {
            return 0;
        }
        return out.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        if (out.isEmpty()) {
            return 0;
        }
        return out.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
}
