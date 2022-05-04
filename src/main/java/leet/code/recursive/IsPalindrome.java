package leet.code.recursive;

/**
 * 234. 回文链表
 * <p></p>
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 * <pre>
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。
 * 如果是，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：head = [1,2,2,1]
 * 输出：true
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：false
 *
 * 提示：
 * 链表中节点数目在范围[1, 10^5] 内
 * 0 <= Node.val <= 9
 *
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * </pre>
 *
 * @author guangyi
 */
public class IsPalindrome {

    private final StringBuilder stringBuilder = new StringBuilder(4096);

    /**
     * 方法二：递归 + 双指针
     */
    public boolean isPalindrome(ListNode head) {
        recursive(head);
        int left = 0;
        int right = stringBuilder.length() - 1;
        while (left < right) {
            if (stringBuilder.charAt(left) != stringBuilder.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private void recursive(ListNode node) {
        if (node == null) {
            return;
        }
        stringBuilder.append(node.val);
        recursive(node.next);
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
