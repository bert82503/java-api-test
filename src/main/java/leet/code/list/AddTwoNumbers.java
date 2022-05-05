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
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
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

    /**
     * 两数相加
     * https://leetcode-cn.com/problems/add-two-numbers/solution/liang-shu-xiang-jia-by-leetcode-solution/
     * <pre>
     * 方法：初等数学
     * 使用变量来跟踪进位，并从包含最低有效位的表头开始模拟逐位相加的过程。
     *
     * 方法一：模拟
     * 思路与算法
     * 由于输入的两个链表都是逆序存储数字的位数的，因此两个链表中同一位置的数字可以直接相加。
     * </pre>
     *
     * 画解算法：2. 两数相加
     * https://leetcode-cn.com/problems/add-two-numbers/solution/hua-jie-suan-fa-2-liang-shu-xiang-jia-by-guanpengc/
     * <pre>
     * 思路
     * 小技巧：对于链表问题，返回结果为头结点时，通常需要先初始化一个预先指针 pre，该指针的下一个节点指向真正的头结点head。
     * 使用预先指针的目的在于链表初始化时无可用节点值，而且链表构造过程需要指针移动，进而会导致头指针丢失，无法返回结果。
     * </pre>
     *
     * 两数相加
     * https://leetcode-cn.com/problems/add-two-numbers/solution/liang-shu-xiang-jia-by-gpe3dbjds1/
     * <pre>
     * 整体思路：
     * 不对齐补零，若链表不为空则用sum(代表每个位的和的结果)加上，考虑进位。
     * </pre>
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 技巧：哑节点，虚拟的前驱节点
        ListNode dummyHead = new ListNode(-1);
        ListNode tail = dummyHead;
        // 两数之和的进位
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            int sum = x + y + carry;

            carry = sum / 10;
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 进位处理
        if (carry == 1) {
            tail.next = new ListNode(carry);
        }
        return dummyHead.next;
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
