package leet.code.search.depth.first;

/**
 * 124. 二叉树中的最大路径和
 * <p></p>
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 * <pre>
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
 * 同一个节点在一条路径序列中 至多出现一次 。
 * 该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * 提示：
 * 树中节点数目范围是 [1, 3 * 10^4]
 * -1000 <= Node.val <= 1000
 * </pre>
 * 特性：
 * 1. 节点值可能为负数
 *
 * @author guangyi
 */
public class MaxPathSum {

    public int maxPathSum(TreeNode root) {
        depthFirstSearch(root);
        return maxPathSum;
    }

    private int maxPathSum = Integer.MIN_VALUE;

    /**
     * 方法一：深度优先搜索
     * <p></p>
     * 后序遍历
     */
    private int depthFirstSearch(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 递归地遍历左右子树
        // 路径和为负数时，则丢弃
        int leftPathSum = Math.max(depthFirstSearch(node.left), 0);
        int rightPathSum = Math.max(depthFirstSearch(node.right), 0);
        // 路径和
        int pathSum = node.val + leftPathSum + rightPathSum;
        // 含当前根节点的最大路径
        maxPathSum = Math.max(maxPathSum, pathSum);

        // 只返回包含当前根节点和左子树或者右子树的路径
        // 需要考虑Math.max(left, right)为负数的情况
        int maxPath = Math.max(leftPathSum, rightPathSum);
        if (maxPath <= 0) {
            // 左子树或者右子树的路径和都为负数或0
            return node.val;
        } else {
            return node.val + maxPath;
        }
    }

    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
