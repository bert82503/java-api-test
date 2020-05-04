package now.coder.linklist.kth.tail;

/**
 * <a href="https://www.nowcoder.com/questionTerminal/529d3ae5a407492994ad2a246518148a">
 *     链表中倒数第k个结点</a>
 *
 * @author EdwardLee03
 * @since 2020-05-05
 */
public class Solution {
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        // 链表结点数
        int nodeCount = 0;
        ListNode cur = head;
        while (cur != null) {
            nodeCount++;
            cur = cur.next;
        }
        if (nodeCount < k) {
            return null;
        }
        cur = head;
        // 核心思想："链表中倒数第k个结点"等价转换为"链表中第(nodeCount-k)个结点"
        int size = nodeCount - k;
        for (int i = 0; i < size; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
