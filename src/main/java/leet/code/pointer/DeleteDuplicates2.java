package leet.code.pointer;

/**
 * 82. 删除排序链表中的重复元素 II
 * <p></p>
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 * <pre>
 * 给定一个已排序的链表的头 head ，删除原始链表中所有重复数字的节点，只留下不同的数字 。
 * 返回 已排序的链表 。
 *
 * 示例 1：
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 *
 * 示例 2：
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
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
public class DeleteDuplicates2 {

    /**
     * 方法一：有序 + 快慢指针
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 技巧：哑节点，虚拟的前驱节点
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        // 快慢指针
        ListNode slow = dummyHead;
        ListNode prev = head;
        ListNode fast = head.next;
        // 重复数字标识
        boolean dupNum = false;
        while (fast != null) {
            if (fast.val == prev.val) {
                // 出现重复数字，待删除
                dupNum = true;
            } else {
                if (dupNum) {
                    // 新数字与旧数字不相同，看到新的希望
                    dupNum = false;
                    // 忽略重复的旧数字
//                    prev = fast;
                } else {
                    // 发现不重复的数字
                    // 维护 list[0..slow] 无重复
                    slow.next = prev;
                    slow = slow.next;
                }
            }
            prev = fast;
            fast = fast.next;
        }
        // 尾节点数字不重复处理
        if (!dupNum) {
            slow.next = prev;
            slow = slow.next;
        }
        // 删除后序所有重复的元素
        slow.next = null;
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
