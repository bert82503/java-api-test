package leet.code.tree.binary;

/**
 * 654. 最大二叉树
 * <p></p>
 * https://leetcode-cn.com/problems/maximum-binary-tree/
 * <pre>
 * 给定一个不重复的整数数组 nums 。
 * 最大二叉树 可以用下面的算法从 nums 递归地构建：
 * 1. 创建一个根节点，其值为 nums 中的最大值。
 * 2. 递归地在最大值 左边 的 子数组前缀上 构建左子树。
 * 3. 递归地在最大值 右边 的 子数组后缀上 构建右子树。
 *
 * 返回 nums 构建的 最大二叉树 。
 *
 * 输入：nums = [3,2,1,6,0,5]
 * 输出：[6,3,5,null,2,0,null,null,1]
 * 解释：递归调用如下所示：
 * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
 *     - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
 *         - 空数组，无子节点。
 *         - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
 *             - 空数组，无子节点。
 *             - 只有一个元素，所以子节点是一个值为 1 的节点。
 *     - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
 *         - 只有一个元素，所以子节点是一个值为 0 的节点。
 *         - 空数组，无子节点。
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 * nums 中的所有整数 互不相同
 * </pre>
 *
 * @author guangyi
 */
public class ConstructMaximumBinaryTree {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums, int low, int high) {
        // 递归终止条件
        if (low < 0 || low >= nums.length || high < 0 || high >= nums.length) {
            return null;
        }
        if (low > high) {
            // 空数组
            return null;
        } else if (low == high) {
            // 只有一个元素
            return new TreeNode(nums[high]);
        }
        int maxValue = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = low; i <= high; i++) {
            int num = nums[i];
            if (num > maxValue) {
                maxValue = num;
                maxIndex = i;
            }
        }
        TreeNode treeNode = new TreeNode(maxValue);
        treeNode.left = constructMaximumBinaryTree(nums, low, maxIndex - 1);
        treeNode.right = constructMaximumBinaryTree(nums, maxIndex + 1, high);
        return treeNode;
    }

    public static final class TreeNode {
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
