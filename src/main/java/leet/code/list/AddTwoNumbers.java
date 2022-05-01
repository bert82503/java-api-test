package leet.code.list;

/**
 * 2. 两数相加
 * <p></p>
 * https://leetcode-cn.com/problems/add-two-numbers/
 * <pre>
 * 给你两个 非空 的链表，表示两个非负的整数。
 * 它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 提示：
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 * </pre>
 *
 * @author guangyi
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode();
        ListNode node = root;
        // 两数之和的进位数
        int carryNumber = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carryNumber;
            node.next = new ListNode(sum % 10);
            carryNumber = sum / 10;

            l1 = l1.next;
            l2 = l2.next;
            node = node.next;
        }
        while (l1 != null) {
            int sum = l1.val + carryNumber;
            node.next = new ListNode(sum % 10);
            carryNumber = sum / 10;

            l1 = l1.next;
            node = node.next;
        }
        while (l2 != null) {
            int sum = l2.val + carryNumber;
            node.next = new ListNode(sum % 10);
            carryNumber = sum / 10;

            l2 = l2.next;
            node = node.next;
        }
        // 进位数处理
        if (carryNumber == 1) {
            node.next = new ListNode(carryNumber);
        }

        return root.next;
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
