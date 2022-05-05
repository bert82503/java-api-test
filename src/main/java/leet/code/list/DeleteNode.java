package leet.code.list;

/**
 * 237. 删除链表中的节点
 * <p></p>
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 * <pre>
 * 请编写一个函数，用于 删除单链表中某个特定节点 。
 * 在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问 要被删除的节点 。
 *
 * 题目数据保证需要删除的节点 不是末尾节点 。
 *
 * 示例 1：
 * 输入：head = [4,5,1,9], node = 5
 * 输出：[4,1,9]
 * 解释：指定链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9
 *
 * 示例 2：
 * 输入：head = [4,5,1,9], node = 1
 * 输出：[4,5,9]
 * 解释：指定链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9
 *
 * 提示：
 * 链表中节点的数目范围是 [2, 1000]
 * -1000 <= Node.val <= 1000
 * 链表中每个节点的值都是 唯一 的
 * 需要删除的节点 node 是 链表中的节点 ，且 不是末尾节点
 * </pre>
 *
 * @author guangyi
 */
public class DeleteNode {

    /**
     * 删除链表中的节点
     * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/solution/shan-chu-lian-biao-zhong-de-jie-dian-by-x656s/
     * <pre>
     * 方法一：和下一个节点交换
     *
     * 删除链表中的节点的常见的方法是定位到待删除节点的上一个节点，
     * 修改上一个节点的 next 指针，使其指向待删除节点的下一个节点，即可完成删除操作。
     *
     * 这道题中，传入的参数 node 为要被删除的节点，无法定位到该节点的上一个节点。
     * 注意到要被删除的节点不是链表的末尾节点，因此 node.next 不为空，
     * 可以通过对 node 和 node.next 进行操作实现删除节点。
     *
     * 在给定节点 node 的情况下，可以通过修改 node 的 next 指针的指向，删除 node 的下一个节点。
     * 但是题目要求删除 node，为了达到删除 node 的效果，只要在删除节点之前，将 node 的节点值修改为 node.next 的节点值即可。
     *
     * 先将后驱节点的值复制到当前节点，然后将后驱节点当作「待删除节点」来进行常规删除。
     * </pre>
     */
    public void deleteNode(ListNode node) {
        // 将后驱节点的值复制到当前节点
        node.val = node.next.val;
        // 删除后驱节点
        node.next = node.next.next;
    }

    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
}
