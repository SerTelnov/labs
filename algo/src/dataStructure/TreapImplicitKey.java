package dataStructure;

import javax.security.auth.login.LoginException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Telnov Sergey on 12.11.2017.
 */

public class TreapImplicitKey {
    public TreapImplicitKey() {
        this.root = null;
        this.size = 0;
    }

    private Node root;
    private int size;

    private class Node {
        public Node(char name) {
            this.name = name;
            this.size = 1;
            this.prior = random.nextInt();
        }

        public void update() {
            size = 1;
            if (left != null) {
                size += left.size;
            }
            if (right != null) {
                size += right.size;
            }
        }
        public int size, prior;
        public Node left, right, parent;
        char name;
    }
    private class NodePair {
        public NodePair() {
            this.left = this.right = null;
        }
        public NodePair(Node left, Node right) {
            this.left = left;
            this.right = right;
        }
        public void setParents(Node first, Node second) {
            if (left != null) {
                left.parent = first;
            }
            if (right != null) {
                right.parent = second;
            }
        }
        Node left;
        Node right;
    }

    private int getNodeSize(Node n) {
        return n == null ? 0 : n.size;
    }

    private Node getRoot(Node curVertex) {
        Node root = curVertex;
        while (root.parent != null) {
            root = root.parent;
        }
        return root;
    }

    private int getVertexIndex(Node curVertex) {
        int result = getNodeSize(curVertex.left) + 1;
        while (curVertex.parent != null) {
            Node nextVertex = curVertex.parent;
            if (nextVertex.right == curVertex) {
                result += getNodeSize(nextVertex.left) + 1;
            }
            curVertex = nextVertex;
        }
        return result;
    }

    public NodePair split(Node root, final int minRight) {
        if (root == null) {
            return new NodePair();
        }
        final int leftVertexSize = getNodeSize(root.left);
        if (leftVertexSize >= minRight) {
            NodePair sub = split(root.left, minRight);
            root.left = sub.right;
            root.update();
            sub.setParents(null, root);
            return new NodePair(sub.left, root);
        } else {
            NodePair sub = split(
                    root.right,
                    minRight - leftVertexSize - 1
            );
            root.right = sub.left;
            root.update();
            sub.setParents(root, null);
            return new NodePair(root, sub.right);
        }
    }

    public Node merge(Node left, Node right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        if (left.prior > right.prior) {
            Node newTree = merge(left.right, right);
            left.right = newTree;
            newTree.parent = left;
            left.update();
            return left;
        } else {
            Node newTree = merge(left, right.left);
            right.left = newTree;
            newTree.parent = right;
            right.update();
            return right;
        }
    }

    public Node insert(Node root, final int position, char name) {
        size++;
        NodePair temp = split(root, position);
        return merge(
                merge(temp.left, new Node(name)),
                temp.right
        );
    }

    public void insert(final int position, char name) {
        root = insert(root, position, name);
    }

    public Node remove(Node root, final int position) {
        size--;
        NodePair temp = split(root, position);
        return merge(
                temp.left,
                split(
                        temp.right,
                        position + 1 - getNodeSize(temp.left)
                ).right
        );
    }

    public void remove(final int position) {
        root = remove(root, position);
    }

    public void add(char name) {
        insert(size, name);
    }

    private Random random = new Random();

    private int checkTree(Node curr, Node prev) throws LoginException {
        if (curr == null) {
            return 0;
        }
        if (curr.parent != prev) {
            throw new LoginException("current node: '" + curr.name + "' don't have parent");
        }
        int subTreeSize = checkTree(curr.left, curr) + 1;
        subTreeSize += checkTree(curr.right, curr);
        if (subTreeSize != curr.size) {
            throw new LoginException("current node: '" + curr.name + "' has wrong sub tree size");
        }
        return subTreeSize;
    }

    private void checkGetSubTreeSize(Node currVertex, int leftSize) throws LoginException {
        if (currVertex == null) {
            return;
        }
        if (leftSize + 1 != getVertexIndex(currVertex)) {
            throw new LoginException("wrong getSubTreeSize value value at vertex: '" + currVertex.name + "'");
        }
        if (currVertex.left != null) {
            checkGetSubTreeSize(currVertex.left, leftSize - getNodeSize(currVertex.left.right) - 1);
        }
        if (currVertex.right != null) {
            checkGetSubTreeSize(currVertex.right, leftSize + getNodeSize(currVertex.right.left) + 1);
        }
    }

    private boolean run() {
        TreapImplicitKey tree = new TreapImplicitKey();
        Node a = new Node('a');
        Node b = new Node('b');
        b.size = 5;
        Node c = new Node('c');
        c.size = 3;
        Node d = new Node('d');
        Node e = new Node('e');
        tree.root = b;
        b.left = a;
        a.parent = b;
        b.right = c;
        c.parent = b;
        c.left = d;
        d.parent = c;
        c.right = e;
        e.parent = c;
        try {
            checkGetSubTreeSize(b, a.size);
        } catch (LoginException error) {
            System.out.println("test failed\n" + error.getMessage());
            return false;
        }
        return true;
    }

    private boolean runTest() {
        Scanner in = new Scanner(System.in);
        return runTest(in.next());
    }

    private boolean runTest(String eulerTour) {
        TreapImplicitKey tree = new TreapImplicitKey();
        for (int i = 0; i != eulerTour.length(); i++) {
            tree.add(eulerTour.charAt(i));
            try {
                checkTree(tree.root, null);
                checkGetSubTreeSize(tree.root, getNodeSize(tree.root.left));
            } catch (LoginException e) {
                System.out.println("random test failed\n" + e.getMessage());
                return false;
            }
        }
        return tree.root.size == eulerTour.length();
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i != 1000; i++) {
            if (!new TreapImplicitKey().runTest(Long.toString(random.nextLong()))) {
                throw new RuntimeException("test failed\n");
            }
        }
        System.out.println("Ok");
    }
}
