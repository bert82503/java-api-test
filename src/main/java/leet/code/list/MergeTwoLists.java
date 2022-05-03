package leet.code.list;

/**
 * 21. 合并两个有序链表
 * <p></p>
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 * <pre>
 * 将两个升序链表合并为一个新的 升序 链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *
 * 提示：
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 * </pre>
 * <pre>
 * 1.认识题目
 * 两个有序链表
 * 有序，升序，非递减顺序 排列
 * </pre>
 *
 * @author guangyi
 */
public class MergeTwoLists {

    private final ListNode preHead = new ListNode();

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode pre = preHead;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                pre.next = list2;
                break;
            } else if (list2 == null) {
                pre.next = list1;
                break;
            } else if (list2.val > list1.val) {
                pre.next = list1;
                pre = pre.next;
                list1 = list1.next;
            } else {
                pre.next = list2;
                pre = pre.next;
                list2 = list2.next;
            }
        }
        return preHead.next;
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
