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
        maxGain(root);
        return maxPathSum;
    }

    /**
     * 最大路径和
     */
    private int maxPathSum = Integer.MIN_VALUE;

    /**
     * 方法一：深度优先搜索
     * <p></p>
     * 后序遍历
     * <p></p>
     *
     * 「手画图解」别纠结递归的细节 | 124.二叉树中的最大路径和
     * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/solution/shou-hui-tu-jie-hen-you-ya-de-yi-dao-dfsti-by-hyj8/
     * <pre>
     * 思路
     * 路径每到一个节点，有 3 种选择：1. 停在当前节点。2. 走到左子节点。3. 走到右子节点。
     * 走到子节点，又面临这 3 种选择，递归适合处理这种规模不同的同一问题。
     * 注意，不能走进一个分支又掉头回来走另一个分支，路径会重叠，不符合题目要求。
     *
     * 定义递归函数
     * 对于一个父节点，它关心自己走入一个子树，从中捞取的最大收益，不关心具体怎么走。
     *
     * 子树中的内部路径要包含根节点
     *
     * 思路梳理
     *
     * 复盘总结
     * 1. 递归一个树，会对每个子树做同样的事（你写的处理逻辑），
     *    所以你需要思考要对每个子树做什么事，即思考子问题是什么，大问题怎么拆解成子问题。
     * 2. 通过求出每个子树对外提供的最大路径和（返回出来给父节点），从递归树底部向上，
     *    不断求出了每个子树内部的最大路径和，后者是求解的目标，它的求解需要前者，搞清楚二者的关系。
     * 3. 每个子树的内部最大路径和，都挑战一下最大纪录，递归结束时，最大纪录就有了。
     * 4. 思考递归问题，别纠结细节实现，内部细节是子递归帮你去做的，
     *    应结合求解的目标，自顶而下、屏蔽细节地思考，思考递归子问题的定义。
     *    随着递归出栈，子问题自下而上地解决，最后解决了整个问题。
     * 5. 要做的只是写好递归的处理逻辑，怎么处理当前子树？需要返回什么吗？怎么设置递归的出口？
     * 6. 没有思路的时候，试着画画递归树找思路。就算做对了，画图也能加深对递归算法的理解。
     *
     * 我经常读改本文，常读常新，力求准确清晰，毫无保留，这份真诚你应该感受的到。
     * </pre>
     *
     * 一篇文章解决所有二叉树路径问题（问题分析+分类模板+题目剖析）
     * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/solution/yi-pian-wen-zhang-jie-jue-suo-you-er-cha-kcb0/
     */
    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int pathSum = node.val + leftGain + rightGain;

        // 更新答案
        maxPathSum = Math.max(maxPathSum, pathSum);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
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
