package dataStructure;

import java.util.HashSet;
import java.util.Random;

/**
 * Created by Telnov Sergey on 12.11.2017.
 */

public class Treap {

    public Treap() {
        this.root = null;
    }

    private Random random = new Random();
    private Node root;

    private class NodePair {
        public NodePair(Node left, Node right) {
            this.left = left;
            this.right = right;
        }
        public Node left, right;
    }

    private class Node {
        int key, prior, cnt;
        Node left, right;

        Node(final int value) {
            key = value;
            prior = random.nextInt();
            cnt = 1;
            left = right = null;
        }

        void update() {
            cnt = 1;
            if (left != null) {
                cnt += left.cnt;
            }
            if (right != null) {
                cnt += right.cnt;
            }
        }
    }

    private NodePair split(Node root, int minRight) {
        if (root == null)
            return new NodePair(null, null);
        if (root.key >= minRight) {
            NodePair leftSplit = split(root.left, minRight);
            root.left = leftSplit.right;
            root.update();
            leftSplit.right = root;
            return leftSplit;
        } else {
            NodePair rightSplit = split(root.right, minRight);
            root.right = rightSplit.left;
            root.update();
            rightSplit.left = root;
            return rightSplit;
        }
    }

    private Node merge(Node left, Node right) {
        if (left == null)
            return right;
        if (right == null)
            return left;
        if (left.prior > right.prior) {
            left.right = merge(left.right, right);
            left.update();
            return left;
        } else {
            right.left = merge(left, right.left);
            right.update();
            return right;
        }
    }

    private Node insert(Node root, int key) {
        NodePair t = split(root, key);
        return merge(merge(t.left, new Node(key)), t.right);
    }

    public void insert(final int key) {
        root = insert(root, key);
    }

    private Node remove(Node root, int key) {
        NodePair t = split(root, key);
        return merge(t.left, split(t.right, key + 1).right);
    }

    public void remove(final int key) {
        remove(root, key);
    }

    private boolean exists(Node t, long x) {
        if (root == null)
            return false;
        if (t.key == x)
            return true;
        if (t.key < x)
            return exists(t.right, x);
        return exists(t.left, x);
    }

    public boolean exist(final int key) {
        return exists(root, key);
    }

    public void runTest() {
        Treap treap = new Treap();
        HashSet<Integer> values = new HashSet<>(1000);
        Random random = new Random();
        for (int i = 0; i != 100; i++) {
            int curValue = random.nextInt(100);
            treap.insert(curValue);
            values.add(curValue);
        }
        for (Integer value : values) {
            if (!treap.exist(value)) {
                throw new RuntimeException("no value");
            }
        }
        for (int i = 0; i != 20; i++) {
            treap.remove(i);
            values.remove(i);
        }
        for (Integer value : values) {
            if (!treap.exist(value)) {
                throw new RuntimeException("no value");
            }
        }
    }

    public static void main(String[] args) {
        new Treap().runTest();
    }
}
