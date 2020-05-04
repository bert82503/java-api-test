package now.coder.stack.min;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

/**
 * <a href="https://www.nowcoder.com/questionTerminal/a4d17eb2e9884359839f8ec559043761">
 *     最小栈</a>
 *
 * @author EdwardLee03
 * @since 2020-03-22
 */
public class Main {
    private static class Element {
        /**
         * 元素值
         */
        int value;
        /**
         * 最小值
         */
        int min;
    }

    /**
     * 使用"双端队列"模拟"栈"（基本类型与包装类型存在自动解包装）
     */
    private Deque<Element> deque;

    public Main() {
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

    public int pop() {
        if (deque.isEmpty()) {
            return  0;
        }
        return deque.pollFirst().value;
    }

    public int min() {
        if (deque.isEmpty()) {
            return 0;
        }
        return deque.peekFirst().min;
    }

    public static void main(String[] args) throws IOException {
        Main minStack = new Main();

        StreamTokenizer streamTokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        streamTokenizer.nextToken();
        int lineNum = (int) streamTokenizer.nval;
        // 通过缓冲器优化IO输出打印
        StringBuilder sb = new StringBuilder(lineNum * 8);
//        StringBuilder sb = new StringBuilder(4096);
        while (lineNum-- > 0) {
            streamTokenizer.nextToken();
            int op = (int) streamTokenizer.nval;
            switch (op) {
                case 1:
                    streamTokenizer.nextToken();
                    int e = (int) streamTokenizer.nval;
                    minStack.push(e);
                    break;
                case 0:
                    sb.append(minStack.min()).append('\n');
                    break;
                case 2:
                    sb.append(minStack.pop()).append('\n');
                    break;
            }
        }
        System.out.println(sb.toString().trim());
    }
}
