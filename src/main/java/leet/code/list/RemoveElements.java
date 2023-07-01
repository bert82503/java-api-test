package leet.code.list;

/**
 * 203. 移除链表元素
 * <p></p>
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * <pre>
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 *
 * 示例 1：
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 *
 * 示例 2：
 * 输入：head = [], val = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 *
 * 提示：
 * 列表中的节点数目在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 * </pre>
 *
 * @author guangyi
 */
public class RemoveElements {

    /**
     * 遍历
     */
    public ListNode removeElements(ListNode head, int val) {
        // 特殊情况
        if (head == null) {
            // 空链表
            return null;
        }
        // 技巧：哑节点，虚拟的前驱节点
        ListNode dummyHead = new ListNode(Integer.MAX_VALUE, head);
        // 前驱节点，当前节点
        ListNode prev = dummyHead;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                // 发现目标
                prev.next = cur.next;
                // 释放节点
                cur.next = null;
                cur = prev.next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 方法二：递归
     */
    public ListNode removeElements_Recursive(ListNode head, int val) {
        // 特殊情况
        if (head == null) {
            // 空链表
            return null;
        }
        // 技巧：哑节点，虚拟的前驱节点
        ListNode dummyHead = new ListNode(Integer.MAX_VALUE, head);
        recursiveRemoveElements(dummyHead, val);
        return dummyHead.next;
    }

    public ListNode recursiveRemoveElements(ListNode head, int val) {
        if (head == null) {
            // 尾节点
            return null;
        }
        // 递归调用
        ListNode resNode = recursiveRemoveElements(head.next, val);
        // 先调整节点
        if (resNode == null && head.next != null) {
            // 发现目标
            ListNode temp = head.next;
            head.next = head.next.next;
            // 删除节点
            temp.next = null;
        }
        // 再判断当前节点
        if (head.val == val) {
            return null;
        }
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
