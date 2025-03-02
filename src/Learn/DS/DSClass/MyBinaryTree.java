package Learn.DS.DSClass;

public class MyBinaryTree<T> {
    private class TreeNode {
        T val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
            this(null, null, null);
        }

        TreeNode(T val) {
            this(val, null, null);
        }

        TreeNode(T val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode root;
    private int size;
    private int level;

}
