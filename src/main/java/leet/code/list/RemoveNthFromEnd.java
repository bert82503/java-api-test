package leet.code.list;

import com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator;

/**
 * 19. 删除链表的倒数第 N 个结点
 * <p></p>
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * <pre>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 * </pre>
 * <pre>
 * 1.认识题目
 * 标签：链表
 * 难点：删除链表的倒数第 n 个结点
 * 想法：
 * 1.先反转链表，再正向删除第 n 个结点
 * 2.基于深度优先搜索递归回溯计算倒数第 n 个结点
 * </pre>
 *
 * @author guangyi
 */
public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }
        // 技巧：虚拟的前驱节点
        ListNode preHead = new ListNode(Integer.MIN_VALUE);
        preHead.next = head;
        recursiveRemoveNthFromEnd(preHead, n);
        return preHead.next;
    }

    /**
     * 基于深度优先搜索递归回溯计算倒数第 n 个结点，并删除这个结点。
     */
    private static int recursiveRemoveNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return 0;
        }
        int nthFromEnd = recursiveRemoveNthFromEnd(head.next, n);
        if (nthFromEnd == n) {
            ListNode next = head.next;
            head.next = head.next.next;
            next.next = null;
        }
        return nthFromEnd + 1;
    }

    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
