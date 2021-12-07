package tree;

public class RBTree<Key extends Comparable<Key>, Value> {
    private Node root;
    private int size;

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node {
        public Key key;
        public Value val;
        public Node left, right;
        public boolean color;

        public Node(Key k, Value v) {
            this.key = k;
            this.val = v;
            this.left = null;
            this.right = null;
            this.color = RED;
        }
    }

    public RBTree() {
        root = null;
        size = 0;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        // 根节点保持黑色
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        return x;
    }
}
