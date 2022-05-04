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
     * 回文链表
     * https://leetcode-cn.com/problems/palindrome-linked-list/solution/hui-wen-lian-biao-by-leetcode-solution/
     * <pre>
     * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     *
     * 方法三：一次遍历
     *
     * 方法三：快慢指针
     * 思路
     * 避免使用 O(n) 额外空间的方法就是改变输入。
     * 我们可以将链表的后半部分反转（修改链表结构），然后将前半部分和后半部分进行比较。
     * 比较完成后我们应该将链表恢复原样。虽然不需要恢复也能通过测试用例，但是使用该函数的人通常不希望链表结构被更改。
     * 该方法虽然可以将空间复杂度降到 O(1)，但是在并发环境下，该方法也有缺点。
     * 在并发环境下，函数运行时需要锁定其他线程或进程对链表的访问，因为在函数执行过程中链表会被修改。
     *
     * 算法
     * 整个流程可以分为以下五个步骤：
     * 1.找到前半部分链表的尾节点。
     * 2.反转后半部分链表。
     * 3.判断是否回文。
     * 4.恢复链表。
     * 5.返回结果。
     *
     * 我们也可以使用快慢指针在一次遍历中找到：慢指针一次走一步，快指针一次走两步，快慢指针同时出发。
     * 当快指针移动到链表的末尾时，慢指针恰好到链表的中间。通过慢指针将链表分为两部分。
     * 若链表有奇数个节点，则中间的节点应该看作是前半部分。
     * 步骤二可以使用「206. 反转链表」问题中的解决方法来反转链表的后半部分。
     * 步骤三比较两个部分的值，当后半部分到达末尾则比较完成，可以忽略计数情况中的中间节点。
     * 步骤四与步骤二使用的函数相同，再反转一次恢复链表本身。
     *
     * 复杂度分析
     * 时间复杂度：O(n)，其中 n 指的是链表的大小。
     * 空间复杂度：O(1)。我们只会修改原本链表中节点的指向，而在堆栈上的堆栈帧不超过 O(1)。
     * </pre>
     */
    public boolean isPalindrome(ListNode head) {
        if (head.next == null) {
            return true;
        }
        // 1.找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);
        // 2.判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        // 3.还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    /**
     * 使用快慢指针在一次遍历中找到：慢指针一次走一步，快指针一次走两步，快慢指针同时出发。
     * 当快指针移动到链表的末尾时，慢指针恰好到链表的中间。通过慢指针将链表分为两部分。
     * 若链表有奇数个节点，则中间的节点应该看作是前半部分。
     */
    private static ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * <pre>
     * 妖魔化的双指针
     * 原链表的头结点就是反转之后链表的尾结点，使用 head 标记 .
     * 定义指针 cur，初始化为 head .
     * 每次都让 head 下一个结点的 next 指向 cur ，实现一次局部反转
     * 局部反转完成之后，cur 和 head 的 next 指针同时 往前移动一个位置
     * 循环上述过程，直至 cur 到达链表的最后一个结点 .
     * </pre>
     */
    private static ListNode reverseList(ListNode head) {
        ListNode cur = head;
        while (head.next != null) {
            ListNode next = head.next.next;
            head.next.next = cur;
            cur = head.next;
            head.next = next;
        }
        return cur;
    }

    /**
     * 前序节点和当前节点
     */
    private static ListNode reverseList_One(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public boolean isPalindrome_One(ListNode head) {
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
