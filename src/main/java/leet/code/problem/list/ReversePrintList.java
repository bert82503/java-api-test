package leet.code.problem.list;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <a href="https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/">
 * 面试题06. 从尾到头打印链表</a>
 * <p>
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * <p>
 * 限制：0 <= 链表长度 <= 10000
 *
 * <pre>
 * 一、认识问题：
 * 从尾到头反过来，类似栈的后进先出特性
 * 用数组返回，转换链表，但链表节点个数不知，而数组初始化需要确定长度。可以遍历链表获取数组长度，看遍历成本
 * "限制：0 <= 链表长度 <= 10000"，数组最长为 10001
 * </pre>
 *
 * @author guangyi
 * @since 2020-06-13
 */
public class ReversePrintList {

    private static final int[] EMPTY_INT_ARRAY = new int[0];

    private int[] result;
    private int i = 0;

//    public int[] reversePrint(ListNode head) {
//        if (head == null) {
//            return EMPTY_INT_ARRAY;
//        }
//        result = new int[10001];
//        recursive(head);
//        // 数组拷贝耗时 1ms
//        return Arrays.copyOf(result, i);
//    }

    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值。
     *
     * <pre>
     * 执行用时 : 0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗 : 40.7 MB, 在所有 Java 提交中击败了 100.00% 的用户
     * </pre>
     *
     * @param head 链表的头节点
     */
    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return EMPTY_INT_ARRAY;
        }
        int size = size(head);
        result = new int[size];
        recursive(head);
        return result;
    }

    private int size(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    private void recursive(ListNode head) {
        if (head == null) {
            return;
        }
        recursive(head.next);
        result[i++] = head.val;
    }

    /**
     * Test cases.
     */
    public static void main(String[] args) {
        ReversePrintList reversePrintList = new ReversePrintList();

        assertThat(reversePrintList.reversePrint(
                apply(new int[]{1, 3, 2})))
                .isEqualTo(new int[]{2, 3, 1});
        assertThat(reversePrintList.reversePrint(
                apply(new int[]{})))
                .isEqualTo(new int[]{});
        assertThat(reversePrintList.reversePrint(
                apply(new int[]{0})))
                .isEqualTo(new int[]{0});
        assertThat(reversePrintList.reversePrint(
                apply(new int[]{-1, 1})))
                .isEqualTo(new int[]{1, -1});
        assertThat(reversePrintList.reversePrint(
                apply(new int[]{6, 5, 4, 3, 2, 1})))
                .isEqualTo(new int[]{1, 2, 3, 4, 5, 6});
        assertThat(reversePrintList.reversePrint(
                apply(new int[]{1, 2, 3, 4, 5, 6})))
                .isEqualTo(new int[]{6, 5, 4, 3, 2, 1});
        assertThat(reversePrintList.reversePrint(
                apply(new int[]{0, Integer.MAX_VALUE, -1, Integer.MIN_VALUE, 1})))
                .isEqualTo(new int[]{1, Integer.MIN_VALUE, -1, Integer.MAX_VALUE, 0});
    }

    private static ListNode apply(int[] array) {
        if (array == null || array.length <= 0) {
            return null;
        }
        ListNode head = null;
        ListNode cur = null;
        for (int value : array) {
            ListNode node = new ListNode(value);
            if (head == null) {
                head = node;
                cur = head;
            } else {
                cur.next = node;
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * Definition for singly-linked list.
     */
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
