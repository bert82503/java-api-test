package leet.code.recursive;

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

    /**
     * 合并两个有序链表
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/he-bing-liang-ge-you-xu-lian-biao-by-leetcode-solu/
     * <pre>
     * 方法一：递归
     * 思路
     * 我们可以如下递归地定义两个链表里的 merge 操作（忽略边界情况，比如空链表等）：
     *     list1[0]+merge(list1[1:],list2)  list1[0]<list2[0]
     *     list2[0]+merge(list1,list2[1:])  otherwise
     * 也就是说，两个链表头部值较小的一个节点与剩下元素的 merge 操作结果合并。
     *
     * 算法
     * 我们直接将以上递归过程建模，同时需要考虑边界情况。
     * 如果 l1 或者 l2 一开始就是空链表 ，那么没有任何操作需要合并，所以我们只需要返回非空链表。
     * 否则，我们要判断 l1 和 l2 哪一个链表的头节点的值更小，然后递归地决定下一个添加到结果里的节点。
     * 如果两个链表有一个为空，递归结束。
     *
     * 复杂度分析
     * 时间复杂度：O(n + m)，其中 n 和 m 分别为两个链表的长度。
     * 空间复杂度：O(n + m)，其中 n 和 m 分别为两个链表的长度。
     * </pre>
     *
     * 一看就会，一写就废？详解递归
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/yi-kan-jiu-hui-yi-xie-jiu-fei-xiang-jie-di-gui-by-/
     * <pre>
     * 1. 前言
     * 递归解法总是给人一种“只可意会不可言传”的感觉，代码一看就懂，自己动手一写就呆住了，很难受。
     * 究其原因，一是我们练习不够，二是理解不够。
     *
     * 2. 什么是递归？
     * 什么是递归呢？函数在运行时调用自己，这个函数就叫递归函数，调用的过程叫做递归。
     * 比如定义函数 f(x)=x+f(x−1)
     *
     * 我们从中总结两个规律：
     * 递归函数必须要有终止条件，否则会出错；
     * 递归函数先不断调用自身，直到遇到终止条件后进行回溯，最终返回答案。
     *
     * 3. 递归解法
     * 根据以上规律考虑本题目：
     * 终止条件：当两个链表都为空时，表示我们对链表已合并完成。
     * 如何递归：我们判断 l1 和 l2 头结点哪个更小，然后较小结点的 next 指针指向其余结点的合并结果。（调用递归）
     * </pre>
     *
     * 画解算法：21. 合并两个有序链表
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/hua-jie-suan-fa-21-he-bing-liang-ge-you-xu-lian-bi/
     * <pre>
     * 思路
     * 标签：链表、递归
     * 这道题可以使用递归实现，新链表也不需要构造新节点，我们下面列举递归三个要素
     * 终止条件：两条链表分别名为 l1 和 l2，当 l1 为空或 l2 为空时结束
     * 返回值：每一层调用都返回排序好的链表头
     * 本级递归内容：如果 l1 的 val 值更小，则将 l1.next 与排序好的链表头相接，l2 同理
     * O(m+n)，m 为 l1的长度，n 为 l2 的长度
     * </pre>
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 每一层调用都返回排序好的链表头
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            // 每一层调用都返回排序好的链表头
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            // 每一层调用都返回排序好的链表头
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    /**
     * <pre>
     * 方法二：迭代
     * 思路
     * 我们可以用迭代的方法来实现上述算法。
     * 当 l1 和 l2 都不是空链表时，判断 l1 和 l2 哪一个链表的头节点的值更小，将较小值的节点添加到结果里，
     * 当一个节点被添加到结果里之后，将对应链表中的节点向后移一位。
     *
     * 算法
     *
     * 复杂度分析
     * 时间复杂度：O(n + m)，其中 n 和 m 分别为两个链表的长度。
     * 空间复杂度：O(1)。我们只需要常数的空间存放若干变量。
     * </pre>
     */
    public ListNode mergeTwoLists_ForEach(ListNode list1, ListNode list2) {
        // 技巧：哑节点，虚拟的前驱节点
        ListNode dummy = new ListNode(-1);

        ListNode prev = dummy;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                prev.next = list2;
                break;
            } else if (list2 == null) {
                prev.next = list1;
                break;
            } else if (list2.val > list1.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }

        return dummy.next;
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
