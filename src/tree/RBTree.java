package tree;
// package java.time.format;

import javax.swing.Spring;

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

        @Override
        public String toString() {
            return "{\"Key\":" + this.key + ",\"Value\":" + this.val + ",\"left\":"
                    + (this.left != null ? this.left.toString() : "null") + ",\"right\":"
                    + (this.right != null ? this.right.toString() : this.right) + ",\"color\":"
                    + (this.color ? "\"RED\"" : "\"BLACK\"") + "}";
        }
    }

    public RBTree() {
        root = null;
        size = 0;
    }

    private boolean isRed(Node node) {
        if (node == null)
            return BLACK;
        return node.color;
    }

    // 颜色翻转
    private void filpColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    //
    //     node                               x
    //    ↙    ↘        左旋转              ↙    ↘ 
    //  t1     x      -------->      node      t3
    //      ↙    ↘                  ↙    ↘ 
    //    t2      t3             t1     t2
    private Node leftRotate(Node node) {
        Node x = node.right;
        // 左旋转
        node.right = x.left;
        x.left = node;
        // 2-3数内的3节点
        // 2-3树3节点转普通节点定义：是左斜的
        x.color = node.color;
        node.color = RED;
        return x;
    }

    //
    //      node                               x
    //     ↙    ↘        右旋转              ↙    ↘ 
    //    x       t1     -------->        t3     node    t3 - node - 变成2节点 - 颜色变成黑色 x-可能需要向上合并-颜色变成红色
    //  ↙    ↘                                 ↙    ↘ 
    //t2      t3                             t1     t2
    private Node rightRotate(Node node) {
        Node x = node.left;
        // 左旋转
        node.left = x.right;
        x.right = node;
        // 2-3数内的3节点
        // 2-3树3节点转普通节点定义：是左斜的
        x.color = node.color;
        node.color = RED;
        return x;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        // 根节点保持黑色
        root.color = BLACK;
    }

    /**
     *     black     black        black   
     *   ↙         ↙             ↙         black                 red
     * red  -->  red   -->    red  -->  ↙        ↘     -->     ↙    ↘
     *             ↘        ↙         red         red       black   black
     *               red  red       
     * @param x
     * @param key
     * @param val
     * @return
     */
    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }

        if (isRed(x.right) && !isRed(x.left)) {
            x = leftRotate(x);
        }
        //         10                              8
        //       ↙        二三树                  ↙   ↘
        //     8          ===    6 - 8 - 10 === 6      10 
        //   ↙                                 
        // 6
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rightRotate(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            filpColors(x);
        }
        return x;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
