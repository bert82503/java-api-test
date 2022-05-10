package leet.code.tree.binary;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link PreorderTraversal}.
 *
 * @author guangyi
 */
public class PreorderTraversalTest {

    @Test
    public void preorderTraversal() {
        PreorderTraversal.TreeNode root = buildTreeNode();
        List<Integer> list = PreorderTraversal.preorderTraversal(root);
        assertThat(list).isEqualTo(Arrays.asList(1, 2, 3));
    }

    private static PreorderTraversal.TreeNode buildTreeNode() {
        PreorderTraversal.TreeNode root = new PreorderTraversal.TreeNode(1);
        PreorderTraversal.TreeNode right = new PreorderTraversal.TreeNode(2);
        root.right = right;
        right.left = new PreorderTraversal.TreeNode(3);
        return root;
    }
}
