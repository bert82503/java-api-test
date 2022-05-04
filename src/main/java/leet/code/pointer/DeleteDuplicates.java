package leet.code.pointer;

/**
 * 83. 删除排序链表中的重复元素
 * <p></p>
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 * <pre>
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。
 * 返回 已排序的链表 。
 *
 * 示例 1：
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 *
 * 示例 2：
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 *
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 * </pre>
 * <pre>
 * 1.认识题目
 * 标签：链表
 * 链表已经按升序 排列
 * </pre>
 *
 * @author guangyi
 */
public class DeleteDuplicates {

    /**
     * 方法一：有序 + 双指针
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 双指针
        ListNode left = head;
        ListNode prev = head;
        ListNode right = head.next;
        while (right != null) {
            // 发现不重复的元素
            if (right.val != prev.val) {
                left.next = right;
                left = left.next;
            }
            prev = right;
            right = right.next;
        }
        // 删除所有重复的元素
        left.next = null;
        return head;
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
