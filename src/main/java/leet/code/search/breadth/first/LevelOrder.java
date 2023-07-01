package leet.code.search.breadth.first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * 102. 二叉树的层序遍历
 * <p></p>
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * <pre>
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。
 * （即逐层地，从左到右访问所有节点）。
 *
 * 提示：
 * 树中节点数目在范围 [0, 2000] 内
 * -1000 <= Node.val <= 1000
 * </pre>
 *
 * @author guangyi
 */
public class LevelOrder {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        return levelOrder_Deque(root);
    }

    /**
     * 方法二：迭代
     * <pre>
     * 关键字：层次遍历
     * 模式识别：一旦出现树的层次遍历，都可以用队列作为辅助结构
     * </pre>
     */
    public List<List<Integer>> levelOrder_Deque(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>(10);
        // 节点队列
        Deque<TreeNode> deque = new ArrayDeque<>(16);
        // 首先根元素入队
        deque.addFirst(root);
        // 当队列不为空的时候
        while (!deque.isEmpty()) {
            List<Integer> level = new ArrayList<>(10);
            // 求当前队列的长度
            int curLevelSize = deque.size();
            // 依次从队列中取 si 个元素进行拓展，然后进入下一次迭代
            for (int i = 0; i < curLevelSize; i++) {
                TreeNode node = deque.removeLast();
                level.add(node.val);
                // 左节点
                if (node.left != null) {
                    deque.addFirst(node.left);
                }
                // 右节点
                if (node.right != null) {
                    deque.addFirst(node.right);
                }
            }
            levels.add(level);
        }
        return levels;
    }

    private final List<List<Integer>> levels = new ArrayList<>(10);

    /**
     * 方法一：递归实现
     */
    public List<List<Integer>> levelOrder_Recursive(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        breadthFirstSearch(root, 0);
        return levels;
    }

    /**
     * 方法一：递归实现
     * <pre>
     * 相同层次的节点归入同一个数组
     * 传入辅助的 level 参数决定层次
     * </pre>
     *
     * 二叉树的层序遍历
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/er-cha-shu-de-ceng-xu-bian-li-by-leetcode-solution/
     * <pre>
     * 方法一：广度优先搜索
     *
     * 思路和算法
     * 我们可以用广度优先搜索解决这个问题。
     * 我们可以想到最朴素的方法是用一个二元组 (node, level) 来表示状态，
     * 它表示某个节点和它所在的层数，每个新进队列的节点的 level 值都是父亲节点的 level 值加一。
     * 最后根据每个点的 level 对点进行分类，分类的时候我们可以利用哈希表，维护一个以 level 为键，对应节点值组成的数组为值，
     * 广度优先搜索结束以后按键 level 从小到大取出所有值，组成答案返回即可。
     *
     * 考虑如何优化空间开销：如何不用哈希映射，并且只用一个变量 node 表示状态，实现这个功能呢？
     * 我们可以用一种巧妙的方法修改广度优先搜索：
     * * 首先根元素入队
     * * 当队列不为空的时候
     *   * 求当前队列的长度 si
     *   * 依次从队列中取 si 个元素进行拓展，然后进入下一次迭代
     * ​它和普通广度优先搜索的区别在于，普通广度优先搜索每次只取一个元素拓展，而这里每次取 si 个元素。
     * 在上述过程中的第 i 次迭代就得到了二叉树的第 i 层的 si 个元素。
     *
     * 为什么这么做是对的呢？我们观察这个算法，可以归纳出这样的循环不变式：
     * 第 i 次迭代前，队列中的所有元素就是第 i 层的所有元素，并且按照从左向右的顺序排列。
     * 证明它的三条性质（你也可以把它理解成数学归纳法）：
     * * 初始化：i = 1 的时候，队列里面只有 root，是唯一的层数为 1 的元素，因为只有一个元素，所以也显然满足「从左向右排列」；
     * * 保持：因为队列的先进先出（FIFO）特性，既然第 k 层的点的出队顺序是从左向右，那么第 k + 1 层也一定是从左向右。
     *   至此，我们已经可以通过数学归纳法证明循环不变式的正确性。
     * * 终止：因为该循环不变式是正确的，所以按照这个方法迭代之后每次迭代得到的也就是当前层的层次遍历结果。至此，我们证明了算法是正确的。
     * </pre>
     *
     * BFS 的使用场景总结：层序遍历、最短路径问题
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/bfs-de-shi-yong-chang-jing-zong-jie-ceng-xu-bian-l/
     * <pre>
     * 解题思路
     * 本文将会讲解为什么这道题适合用广度优先搜索（BFS），以及 BFS 适用于什么样的场景。
     * 本文要介绍的两个场景：「层序遍历」、「最短路径」。
     * 本文包括以下内容：
     * DFS 与 BFS 的特点比较
     * BFS 的适用场景
     * 如何用 BFS 进行层序遍历
     * 如何用 BFS 求解最短路径问题
     *
     * DFS 与 BFS
     * 让我们先看看在二叉树上进行 DFS 遍历和 BFS 遍历的代码比较。
     * DFS 遍历使用递归：
     * BFS 遍历使用队列数据结构：
     *
     * 只是比较两段代码的话，最直观的感受就是：DFS 遍历的代码比 BFS 简洁太多了！
     * 这是因为递归的方式隐含地使用了系统的 栈，我们不需要自己维护一个数据结构。
     * 虽然 DFS 与 BFS 都是将二叉树的所有结点遍历了一遍，但它们遍历结点的顺序不同。
     * 这个遍历顺序也是 BFS 能够用来解「层序遍历」、「最短路径」问题的根本原因。
     * 下面，我们结合几道例题来讲讲 BFS 是如何求解层序遍历和最短路径问题的。
     *
     * BFS 的应用一：层序遍历
     * 什么是层序遍历呢？简单来说，层序遍历就是把二叉树分层，然后每一层从左到右遍历：
     *
     * BFS 的应用二：最短路径
     * 在一棵树中，一个结点到另一个结点的路径是唯一的，
     * 但在图中，结点之间可能有多条路径，其中哪条路最近呢？
     * 这一类问题称为最短路径问题。
     * 最短路径问题也是 BFS 的典型应用，而且其方法与层序遍历关系密切。
     *
     * 小贴士：
     * 很多同学一看到「最短路径」，就条件反射地想到「Dijkstra 算法」。
     * 为什么 BFS 遍历也能找到最短路径呢？
     * 这是因为，Dijkstra 算法解决的是带权最短路径问题，而我们这里关注的是无权最短路径问题。
     * 也可以看成每条边的权重都是 1。这样的最短路径问题，用 BFS 求解就行了。
     * 在面试中，你可能更希望写 BFS 而不是 Dijkstra。毕竟，敢保证自己能写对 Dijkstra 算法的人不多。
     *
     * 最短路径问题属于图算法。由于图的表示和描述比较复杂，本文用比较简单的网格结构代替。
     * 网格结构是一种特殊的图，它的表示和遍历都比较简单，适合作为练习题。
     * 在 LeetCode 中，最短路径问题也以网格结构为主。
     *
     * 最短路径例题讲解
     * 这道题就是一个在网格结构中求最短路径的问题。同时，它也是一个「岛屿问题」，即用网格中的 1 和 0 表示陆地和海洋，模拟出若干个岛屿。
     * 在上一篇文章中，我们介绍了网格结构的基本概念，以及网格结构中的 DFS 遍历。
     * 其中一些概念和技巧也可以用在 BFS 遍历中：
     * * 格子 (r, c) 的相邻四个格子为：(r-1, c)、(r+1, c)、(r, c-1) 和 (r, c+1)；
     * * 使用函数 inArea 判断当前格子的坐标是否在网格范围内；
     * * 将遍历过的格子标记为 2，避免重复遍历。
     *
     * 以上的层序遍历代码有几个注意点：
     * * 队列中的元素类型是 int[] 数组，每个数组的长度为 2，包含格子的行坐标和列坐标。
     * * 为了避免重复遍历，这里使用到了和 DFS 遍历一样的技巧：把已遍历的格子标记为 2。
     *   注意：我们在将格子放入队列之前就将其标记为 2。想一想，这是为什么？(先锁定这个格子，避免同一个格子只会在同一层格子中出现一次，避免重复搜索)
     * * 在将格子放入队列之前就检查其坐标是否在网格范围内，避免将「不存在」的格子放入队列。
     *
     * 写好了层序遍历的代码，接下来我们看看如何来解决本题中的最短路径问题。
     * 这道题要找的是距离陆地最远的海洋格子。
     * 假设网格中只有一个陆地格子，我们可以从这个陆地格子出发做层序遍历，直到所有格子都遍历完。
     * 最终遍历了几层，海洋格子的最远距离就是几。
     *
     * 那么有多个陆地格子的时候怎么办呢？一种方法是将每个陆地格子都作为起点做一次层序遍历，但是这样的时间开销太大。
     * BFS 完全可以以多个格子同时作为起点。我们可以把所有的陆地格子同时放入初始队列，然后开始层序遍历，这样遍历的效果如下图所示：
     * 这种遍历方法实际上叫做「多源 BFS」。
     * 多源 BFS 的定义不是今天讨论的重点，你只需要记住多源 BFS 很方便，只需要把多个源点同时放入初始队列即可。
     *
     * 需要注意的是，虽然上面的图示用 1、2、3、4 表示层序遍历的层数，
     * 但是在代码中，我们不需要给每个遍历到的格子标记层数，只需要用一个 distance 变量记录当前的遍历的层数（也就是到陆地格子的距离）即可。
     *
     * 总结
     * 可以看到，「BFS 遍历」、「层序遍历」、「最短路径」实际上是递进的关系。
     * 在 BFS 遍历的基础上区分遍历的每一层，就得到了层序遍历。
     * 在层序遍历的基础上记录层数，就得到了最短路径。
     *
     * BFS 遍历是一类很值得反复体会和练习的题目。
     * 一方面，BFS 遍历是一个经典的基础算法，需要重点掌握。
     * 另一方面，我们需要能根据题意分析出题目是要求最短路径，知道是要做 BFS 遍历。
     *
     * 本文讲解的只是两道非常典型的例题。
     * </pre>
     */
    private void breadthFirstSearch(TreeNode node, int level) {
        if (levels.size() == level) {
            levels.add(new ArrayList<>(10));
        }
        levels.get(level).add(node.val);

        // 递归地遍历左子树
        if (node.left != null) {
            breadthFirstSearch(node.left, level + 1);
        }
        // 递归地遍历右子树
        if (node.right != null) {
            breadthFirstSearch(node.right, level + 1);
        }
    }

    /**
     * Definition for a binary tree node.
     */
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
