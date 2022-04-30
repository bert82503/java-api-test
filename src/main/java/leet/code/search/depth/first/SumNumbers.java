package leet.code.search.depth.first;

/**
 * 129. 求根节点到叶节点数字之和
 * <p></p>
 * https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
 * <pre>
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 *
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 叶节点 是指没有子节点的节点。
 *
 * 提示：
 * 树中节点的数目在范围 [1, 1000] 内
 * 0 <= Node.val <= 9
 * 树的深度不超过 10
 * </pre>
 *
 * @author guangyi
 */
public class SumNumbers {

    public int sumNumbers(TreeNode root) {
        StringBuilder num = new StringBuilder(11);
        return depthFirstSearch(root, num);
    }

    private static int depthFirstSearch(TreeNode node, StringBuilder num) {
        if (node == null) {
            return 0;
        }
        num = new StringBuilder(num).append(node.val);
        if (node.left == null && node.right == null) {
            // 叶节点
            return Integer.parseInt(num.toString());
        }
        return depthFirstSearch(node.left, num) +
                depthFirstSearch(node.right, num);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
