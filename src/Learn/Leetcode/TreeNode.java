package Learn.Leetcode;

import java.util.*;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 98. 验证二叉搜索树
     *
     * <p>给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     *
     * <p>有效二叉搜索树定义如下：
     *
     * <p>节点的左子树只包含小于当前节点的数。
     *
     * <p>节点的右子树只包含大于当前节点的数。
     *
     * <p>所有左子树和右子树自身必须也是二叉搜索树。
     *
     * @return 返回判断结果
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    private boolean isValidBST(TreeNode root, Long upper, Long lower) {
        if (root == null) {
            return true;
        }
        if (root.val <= lower || root.val >= upper) {
            return false;
        } else {
            return isValidBST(root.left, (long) root.val, lower)
                    && isValidBST(root.right, upper, (long) root.val);
        }
    }

    // [101. 对称二叉树](https://leetcode.cn/problems/symmetric-tree/)
    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    // [102. 二叉树的层序遍历](https://leetcode.cn/problems/binary-tree-level-order-traversal/)
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        queue.add(root);
        int size = 1;
        while (size != 0) {
            int counter = size;
            List<Integer> level = new ArrayList<>();
            while (counter != 0) {
                TreeNode node = queue.removeFirst();
                size--;
                if (node.left != null) {
                    queue.add(node.left);
                    size++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    size++;
                }
                level.add(node.val);
                counter--;
            }
            result.add(level);
        }
        return result;
    }

    // [104. 二叉树的最大深度](https://leetcode.cn/problems/maximum-depth-of-binary-tree/)
    public int maxDepth(TreeNode root) {
        int depth = 0;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        if (root == null) {
            return depth;
        } else {
            int layer_num = 1;
            queue.add(root);
            while (!queue.isEmpty()) {
                depth++;
                int next_layer = 0;
                while (layer_num != 0) {
                    TreeNode temp = queue.remove();
                    layer_num--;
                    if (temp.left != null) {
                        queue.add(temp.left);
                        next_layer++;
                    }
                    if (temp.right != null) {
                        queue.add(temp.right);
                        next_layer++;
                    }
                }
                layer_num = next_layer;
            }
            return depth;
        }
    }

    // [107. 二叉树的层序遍历 II](https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/)
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        Deque<List<Integer>> temp = new LinkedList<>();
        queue.add(root);
        int size = 1;
        while (size != 0) {
            int counter = size;
            List<Integer> level = new ArrayList<>();
            while (counter != 0) {
                TreeNode node = queue.removeFirst();
                size--;
                if (node.left != null) {
                    queue.add(node.left);
                    size++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    size++;
                }
                level.add(node.val);
                counter--;
            }
            temp.push(level);
        }
        while (!temp.isEmpty()) {
            result.add(temp.pop());
        }
        return result;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size = 1;
        while (size != 0) {
            int counter = size;
            while (counter != 0) {
                TreeNode node = queue.removeFirst();
                size--;
                if (node.left != null) {
                    queue.add(node.left);
                    size++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    size++;
                }
                counter--;
                if (counter == 0) {
                    result.add(node.val);
                }
            }
        }
        return result;
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size = 1;
        while (size != 0) {
            double sum = 0;
            int number = size;
            int counter = size;
            while (counter != 0) {
                TreeNode node = queue.removeFirst();
                size--;
                if (node.left != null) {
                    queue.add(node.left);
                    size++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    size++;
                }
                counter--;
                sum += node.val;
            }
            result.add(sum / number);
        }
        return result;
    }

    // [111. 二叉树的最小深度](https://leetcode.cn/problems/minimum-depth-of-binary-tree/)
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size = 1;
        int level = 0;
        while (size != 0) {
            level++;
            int counter = size;
            while (counter != 0) {
                TreeNode node = queue.removeFirst();
                size--;
                if (node.left != null) {
                    queue.add(node.left);
                    size++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    size++;
                }
                if (node.left == null && node.right == null) {
                    return level;
                }
                counter--;
            }
        }
        return level;
    }

    // [226. 翻转二叉树](https://leetcode.cn/problems/invert-binary-tree/)
    private void swap(TreeNode node) {
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        swap(root);
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    // [513. 找树左下角的值](https://leetcode.cn/problems/find-bottom-left-tree-value/)
    public int findBottomLeftValue(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean haveChild = false;
        boolean getLeftDown = false;
        int LeftDown = root.val;
        int size = 1;
        while (size != 0) {
            haveChild = false;
            getLeftDown = false;
            LeftDown = root.val;
            int counter = size;
            while (counter != 0) {
                TreeNode node = queue.removeFirst();
                size--;
                if (node.left != null) {
                    queue.add(node.left);
                    size++;
                    haveChild = true;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    size++;
                    haveChild = true;
                }
                if (!getLeftDown && !haveChild) {
                    LeftDown = node.val;
                    getLeftDown = true;
                }
                counter--;
            }
            if (!haveChild) {
                break;
            }
        }
        return LeftDown;
    }
}
