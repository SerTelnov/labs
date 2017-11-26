package dataStructure;

/**
 * Created by Telnov Sergey on 17.11.2017.
 */

public class SplayTree {
    private class Node {
        public Node(final int name) {
            this.name = name;
        }
        public Node(final int name, final int key, Node left, Node right) {
            this.name = name;
            this.key = key;
            this.left = left;
            this.right = right;
        }

        public final int name;
        public int key;
        public Node parent;
        public Node left, right;

        public void updateParents() {
            if (left != null) {
                left.parent = this;
            }
            if (right != null) {
                right.parent = this;
            }
        }
    }

    private void setParent(Node parent, Node child) {
        if (child != null) {
            child.parent = parent;
        }
    }

    private class NodePair {
        public NodePair() {
            this(null, null);
        }
        public NodePair(Node left, Node right) {
            this.left = left;
            this.right = right;
        }

        public Node left, right;
    }

    private void rotate(Node parent, Node child) {
        Node gparent = parent.parent;
        if (gparent != null) {
            if (gparent.left == parent) {
                gparent.left = child;
            } else {
                gparent.right = child;
            }
        }
        if (parent.left == child) {
            parent.left = child.left;
            child.right = parent;
        } else {
            parent.right = child.left;
            child.left = parent;
        }
        child.updateParents();
        parent.updateParents();
        child.parent = gparent;
    }

    private Node splay(Node curr) {
        if (curr.parent == null) {
            return curr;
        }
        Node parent = curr.parent;
        Node gparent = parent.parent;
        if (gparent == null) {
            rotate(parent, curr);
            return curr;
        } else {
            if ((gparent.left == parent) == (parent.left == curr)) {
                rotate(gparent, parent);
                rotate(parent, curr);
            } else {
                rotate(parent, curr);
                rotate(gparent, curr);
            }
            return splay(curr);
        }
    }

    private Node find(Node v, final int key) {
        if (v == null) {
            return null;
        } else {
            if (key < v.key && v.left != null) {
                return find(v.left, key);
            } else if (key > v.key && v.right != null) {
                return find(v.right, key);
            }
            return splay(v);
        }
    }

    public NodePair split(Node root, final int key) {
        if (root == null) {
            return new NodePair();
        }
        root = find(root, key);
        if (root.key == key) {
            setParent(null, root.left);
            setParent(null, root.right);
            return new NodePair(root.left, root.right);
        } else if (root.key < key) {
            Node right = root.right;
            root.right = null;
            setParent(null, right);
            return new NodePair(root, right);
        } else {
            Node left = root.left;
            root.left = null;
            setParent(null, left);
            return new NodePair(left, root);
        }
    }

    public Node merge(Node left, Node right) {
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }
        right = find(right, left.key);
        right.left = left;
        left.parent = right;
        return right;
    }

    public Node insert(Node root, final int key) {
        NodePair sub = split(root, key);
        root = new Node(0, key, sub.left, sub.right);
        root.updateParents();
        return root;
    }
}
