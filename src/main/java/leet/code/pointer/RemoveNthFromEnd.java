package leet.code.pointer;

/**
 * 19. 删除链表的倒数第 N 个结点
 * <p></p>
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * <pre>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 * </pre>
 * <pre>
 * 1.认识题目
 * 标签：链表
 * 难点：删除链表的倒数第 n 个结点
 * 想法：
 * 1.先反转链表，再正向删除第 n 个结点
 * 2.基于深度优先搜索递归回溯计算倒数第 n 个结点
 * </pre>
 *
 * @author guangyi
 */
public class RemoveNthFromEnd {

    /**
     * 删除链表的倒数第N个节点
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-b-61/
     * <pre>
     * 方法2：快慢指针法
     * - 关键字：倒数第N个
     * - 模式识别：
     *   涉及链表的特殊位置，考虑快慢指针
     *   要删除链表节点，找到它的前驱结点
     *
     * 前言
     * 在对链表进行操作时，一种常用的技巧是添加一个哑节点（dummy node），它的 next 指针指向链表的头节点。
     * 这样一来，我们就不需要对头节点进行特殊的判断了。
     *
     * 特别地，在某些语言中，由于需要自行对内存进行管理。
     * 因此在实际的面试中，对于「是否需要释放被删除节点对应的空间」这一问题，我们需要和面试官进行积极的沟通以达成一致。
     * 下面的代码中默认不释放空间。
     *
     * 方法三：双指针
     * 思路与算法
     * 我们也可以在不预处理出链表的长度，以及使用常数空间的前提下解决本题。
     * 由于我们需要找到倒数第 n 个节点，因此我们可以使用两个指针 first 和 second 同时对链表进行遍历，
     * 并且 first 比 second 超前 n 个节点。当 first 遍历到链表的末尾时，second 就恰好处于倒数第 n 个节点。
     *
     * 具体地，初始时 first 和 second 均指向头节点。我们首先使用 first 对链表进行遍历，遍历的次数为 n。
     * 此时，first 和 second 之间间隔了 n−1 个节点，即 first 比 second 超前了 n 个节点。
     *
     * 在这之后，我们同时使用 first 和 second 对链表进行遍历。
     * 当 first 遍历到链表的末尾（即 first 为空指针）时，second 恰好指向倒数第 n 个节点。
     *
     * 根据方法一和方法二，如果我们能够得到的是倒数第 n 个节点的前驱节点的话，删除操作会更加方便。
     * 因此，我们可以考虑在初始时将 second 指向哑节点，其余的操作步骤不变。
     * 这样一来，当 first 遍历到链表的末尾时，second 的下一个节点就是我们需要删除的节点。
     * </pre>
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }
        // 技巧：哑节点，虚拟的前驱节点
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE, head);
        // 快慢指针
        ListNode fast = head;
        ListNode slow = dummyHead;
        // 1.首先使用 fast 对链表进行遍历，遍历的次数为 n
        // fast 和 slow 之间间隔了 n−1 个节点，即 fast 比 slow 超前了 n 个节点
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        // 2.同时使用 fast 和 slow 对链表进行遍历
        // 当 fast 遍历到链表的末尾（即 fast 为空指针）时，slow 恰好指向倒数第 n 个节点
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // 3.当 fast 遍历到链表的末尾时，slow 的下一个节点就是我们需要删除的节点
        ListNode next = slow.next;
        slow.next = slow.next.next;
        next.next = null;
        return dummyHead.next;
    }

    /**
     * 基于深度优先搜索递归回溯计算倒数第 n 个结点，并删除这个结点。
     */
    private static int recursiveRemoveNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return 0;
        }
        int nthFromEnd = recursiveRemoveNthFromEnd(head.next, n);
        if (nthFromEnd == n) {
            ListNode next = head.next;
            head.next = head.next.next;
            next.next = null;
        }
        return nthFromEnd + 1;
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
