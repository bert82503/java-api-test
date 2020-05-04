package now.coder.linklist.reverse;

/**
 * <a href="https://www.nowcoder.com/questionTerminal/75e878df47f24fdc9dc3e400ec6058ca">
 *     反转链表</a>
 *
 * @author EdwardLee03
 * @since 2020-05-05
 */
public class Solution {
    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        // 反转后的链表头结点
        ListNode reverseHead = null;
        // 前驱结点
        ListNode preNode = null;
        ListNode tmp = null;
        // 当前处理的结点
        ListNode currentNode = head;
        while (currentNode != null) {
            tmp = currentNode.next;
            // 反转结点
            currentNode.next = preNode;
            if (tmp == null) {
                // 抵达链表尾部
                reverseHead = currentNode;
            }
            // 整体往前移动一个结点
            preNode = currentNode;
            currentNode = tmp;
        }
        return reverseHead;
    }

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
