package leet.code.deque;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 232. 用栈实现队列
 * <p></p>
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * <pre>
 * 使用栈实现队列的下列操作：
 *     push(x) -- 将元素 x 推到队列的末尾
 *     pop() -- 从队列的开头移除并返回元素
 *     peek() -- 返回队列开头的元素
 *     empty() -- 如果队列为空，返回 true ；否则，返回 false
 *
 * 提示：
 * 1 <= x <= 9
 * 最多调用 100 次 push、pop、peek 和 empty
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）
 *
 * 进阶：
 * 你能否实现每个操作均摊时间复杂度为 O(1) 的队列？
 * 换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。
 * </pre>
 *
 * @author edwardlee03
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
