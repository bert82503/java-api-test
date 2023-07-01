package leet.code.list;

/**
 * 206. 反转链表
 * <p></p>
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * <pre>
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 *
 * 提示：
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 *
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 * </pre>
 *
 * @author guangyi
 */
public class ReverseList {

    /**
     * 【反转链表】：双指针，递归，妖魔化的双指针
     * https://leetcode-cn.com/problems/reverse-linked-list/solution/fan-zhuan-lian-biao-shuang-zhi-zhen-di-gui-yao-mo-/
     * <pre>
     * 写在前面：
     * 我清晰记得，以前在数据结构课上，老师和我们说：涉及到链表的操作，一定要在纸上把过程先画出来，再写程序。
     * 现在想想，这句话简直是真理啊！
     *
     * 好理解的双指针
     * 定义两个指针： pre 和 cur ；pre 在前 cur 在后。
     * 每次让 pre 的 next 指向 cur ，实现一次局部反转
     * 局部反转完成之后，pre 和 cur 同时往前移动一个位置
     * 循环上述过程，直至 pre 到达链表尾部
     *
     * 简洁的递归
     * 使用递归函数，一直递归到链表的最后一个结点，该结点就是反转后的头结点，记作 ret .
     * 此后，每次函数在返回的过程中，让当前结点的下一个结点的 next 指针指向当前节点。
     * 同时让当前结点的 next 指针指向 NULL ，从而实现从链表尾部开始的局部反转
     * 当递归函数全部出栈后，链表反转完成。
     *
     * 妖魔化的双指针
     * 原链表的头结点就是反转之后链表的尾结点，使用 head 标记 .
     * 定义指针 cur，初始化为 head .
     * 每次都让 head 下一个结点的 next 指向 cur ，实现一次局部反转
     * 局部反转完成之后，cur 和 head 的 next 指针同时 往前移动一个位置
     * 循环上述过程，直至 cur 到达链表的最后一个结点 .
     *
     * 最后
     * 希望以上讲解能帮助您理解链表的反转过程。
     * 但无论是文字描述，还是动图演示，都比不了自己在纸上对着代码画一遍来得深刻。
     * </pre>
     *
     * 动画演示+多种解法 206. 反转链表
     * https://leetcode-cn.com/problems/reverse-linked-list/solution/dong-hua-yan-shi-206-fan-zhuan-lian-biao-by-user74/
     * <pre>
     * 双指针迭代
     *
     * 递归解法
     * 这题有个很骚气的递归解法，递归解法很不好理解，这里最好配合代码和动画一起理解。
     * 递归的两个条件：
     * 1.终止条件是当前节点或者下一个节点==null
     * 2.在函数内部，改变节点的指向，也就是 head 的下一个节点指向 head 递归函数那句
     *   head.next.next = head
     * 很不好理解，其实就是 head 的下一个节点指向head。
     * 递归函数中每次返回的 cur 其实只最后一个节点，在递归函数内部，改变的是当前节点的指向。
     * </pre>
     */
    public ListNode reverseList_ForEach(ListNode head) {
        // 特殊边界
        if (head == null || head.next == null) {
            // 空链表，一个结点
            return head;
        }
        ListNode cur = null;
        ListNode prev = head;
        while (prev != null) {
            // 实现一次局部反转
            ListNode next = prev.next;
            prev.next = cur;
            // 同时往前移动一个位置
            cur = prev;
            prev = next;
        }
        return cur;
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
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
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
     * 递归解法
     */
    public ListNode reverseList_Recursive(ListNode head) {
        // 特殊边界
        if (head == null || head.next == null) {
            // 空链表，一个结点
            return head;
        }
        ListNode cur = reverseList_Recursive(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }

    /**
     * <pre>
     * 反转后的链表的头节点
     *
     * 技巧：哑节点，虚拟的前驱节点
     * </pre>
     */
    private final ListNode dummyHead = new ListNode();

    /**
     * <pre>
     * 3.测试用例
     * # 空链表
     * null
     * # 一个结点
     * node-1 -> null
     * # 多个结点
     * node-1 -> ... -> node-N -> null
     * </pre>
     */
    public ListNode reverseList_Recursive_Two(ListNode head) {
        // 特殊边界
        if (head == null || head.next == null) {
            // 空链表，一个结点
            return head;
        }
        // 技巧：prev = null
        reverseList_Recursive_Two(null, head);
        return dummyHead.next;
    }

    private void reverseList_Recursive_Two(ListNode cur, ListNode pre) {
        if (pre.next == null) {
            // 递归遍历到尾节点
            dummyHead.next = pre;
            pre.next = cur;
            return;
        }
        // 递归遍历
        reverseList_Recursive_Two(pre, pre.next);
        // 反转
        pre.next = cur;
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
