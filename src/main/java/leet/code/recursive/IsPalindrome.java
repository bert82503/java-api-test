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

    /**
     * <pre>
     * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     *
     * 方法三：一次遍历
     * </pre>
     */
    public boolean isPalindrome(ListNode head) {
        if (head.next == null) {
            return true;
        }
        // 正序数字
        int positiveNum = 0;
        // 逆序数字
        int reverseNum = 0;
        int pow = 1;
        ListNode node = head;
        while (node != null) {
            int digit = node.val;
            positiveNum = positiveNum * 10 + digit;
            reverseNum = reverseNum + digit * pow;
            pow *= 10;
            node = node.next;
        }
        return positiveNum == reverseNum;
    }

    /**
     * 方法二：递归 + 双指针
     */
    public boolean isPalindrome_Two(ListNode head) {
        if (head.next == null) {
            return true;
        }

        int[] values = new int[100001];
        int length = 0;
        ListNode node = head;
        while (node != null) {
            values[length++] = node.val;
            node = node.next;
        }

        // 使用双指针判断是否回文
        int left = 0;
        int right = length - 1;
        while (left < right) {
            if (values[left++] != values[right--]) {
                return false;
            }
        }
        return true;
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
