package leet.code.problem.list;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * <p></p>
 * https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 * <pre>
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 限制：
 * 0 <= 链表长度 <= 10000
 * </pre>
 *
 * @author guangyi
 */
public class ReversePrint {

    public int[] reversePrint(ListNode head) {
        Deque<Integer> deque = new LinkedList<>();
        ListNode node = head;
        while (node != null) {
            deque.addFirst(node.val);
            node = node.next;
        }

        int[] result = new int[deque.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = deque.removeFirst();
        }
        return result;
//        return deque.stream()
//                .mapToInt(Integer::intValue)
//                .toArray();
    }

    public int[] reversePrint_Recursive(ListNode head) {
        List<Integer> result = new LinkedList<>();
        recursive(head, result);
        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static void recursive(ListNode head, List<Integer> result) {
        if (head == null) {
            return;
        }
        // 递归
        recursive(head.next, result);
        result.add(head.val);
    }

    public static final class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
}
