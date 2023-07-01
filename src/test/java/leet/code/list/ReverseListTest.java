package leet.code.list;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link ReverseList}.
 *
 * @author guangyi
 */
public class ReverseListTest {

    @Test
    public void reverseList() {
        ReverseList.ListNode head = new ReverseList.ListNode(1);
        ReverseList.ListNode node = head;
        node.next = new ReverseList.ListNode(2);
        node = node.next;
        node.next = new ReverseList.ListNode(3);
        node = node.next;
        node.next = new ReverseList.ListNode(4);
        node = node.next;
        node.next = new ReverseList.ListNode(5);

        ReverseList reverseList = new ReverseList();
        ReverseList.ListNode root = reverseList.reverseList(head);

        assertThat(root.val)
                .isEqualTo(5);
    }
}
