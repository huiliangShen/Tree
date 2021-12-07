package tree;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    public class Node {
        private Key key;
        private Value value;
        private int N;
        private Node left, right;

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.value = val;
            this.N = n;
        }
    }

    public int Size() {
        return size(this.root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.N;
        }
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.value;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    /**
     * 插入BST节点
     * @param x
     * @param key
     * @param val
     * @return
     */
    private Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val, 1);
        int cmp = key.compareTo(key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.value = val;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
}
