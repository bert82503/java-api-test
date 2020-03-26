package leet.code.one;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <a href="最小栈">https://leetcode-cn.com/problems/min-stack/</a>
 *
 * @author EdwardLee03
 * @since 2020-03-25
 */
public class MinStack {
    private static class Element {
        /**
         * 值
         */
        int value;
        /**
         * 最小值
         */
        int min;
    }

    /** 值栈 */
    private Deque<Element> deque;

    /** initialize your data structure here. */
    public MinStack() {
        deque = new LinkedList<>();
    }

    public void push(int x) {
        Element e = new Element();
        e.value = x;
        if (deque.isEmpty() || deque.peekFirst().min >= x) {
            e.min = x;
        } else {
            e.min = deque.peekFirst().min;
        }
        deque.offerFirst(e);
    }

    public void pop() {
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
