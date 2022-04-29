package leet.code.stack.monotonic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1019. 链表中的下一个更大节点
 * <p></p>
 * https://leetcode-cn.com/problems/next-greater-node-in-linked-list/
 * <pre>
 * 给定一个长度为 n 的链表 head
 *
 * 对于列表中的每个节点，查找下一个 更大节点 的值。
 * 也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。
 *
 * 返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。
 * 如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0 。
 *
 * 提示：
 * 链表中节点数为 n
 * 1 <= n <= 10^4
 * 1 <= Node.val <= 10^9
 * </pre>
 *
 * @author guangyi
 */
public class NextLargerNodes {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public int[] nextLargerNodes(ListNode head) {
        int len = 0;
        ListNode tempNode = head;
        while (tempNode != null) {
            len++;
            tempNode = tempNode.next;
        }
        int[] values = new int[len];
        // 保存结果的数组
        int[] result = new int[len];
        // 递减栈
        Deque<Integer> indexMonoStack = new LinkedList<>();
        int i = 0;
        tempNode = head;
        while (tempNode != null) {
            int val = tempNode.val;
            values[i] = val;
            while (!indexMonoStack.isEmpty() && val > values[indexMonoStack.getFirst()]) {
                // 出栈
                int prevIndex = indexMonoStack.removeFirst();
                // 结果计算
                result[prevIndex] = val;
            }
            // 入栈
            indexMonoStack.addFirst(i);

            i++;
            tempNode = tempNode.next;
        }
        return result;
    }

    public static final class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) { this.val = val; }

        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
