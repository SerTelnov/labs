package TreeDecomposition;

import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by @author Telnov Sergey on 11.11.2017.
 */

public class C {
    protected static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String fileName) throws IOException {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileName))
            );
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public int nextVertex() throws IOException {
            return nextInt() - 1;
        }

        public boolean hasNext() throws IOException {
            if (st == null) {
                st = new StringTokenizer(br.readLine());
            } else if (!st.hasMoreElements()) {
                String nextString = br.readLine();
                if (nextString == null) {
                    return false;
                }
                st = new StringTokenizer(nextString);
            }
            return st.hasMoreElements();
        }
    }
    private static class FastWriter {
        public FastWriter() {
            bw = new BufferedWriter(
                    new OutputStreamWriter(System.out)
            );
            sb = new StringBuilder();
        }

        public FastWriter(String fileName) throws IOException {
            bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName))
            );
            sb = new StringBuilder();
        }

        public void println(final int i) {
            sb.append(i).append("\n");
        }

        public void print(final int i) {
            sb.append(i).append(" ");
        }

        public void close() throws IOException {
            bw.write(sb.toString());
            bw.flush();
            bw.close();
        }

        private BufferedWriter bw;
        private StringBuilder  sb;
    }

    protected static class EulerTourTree {

        public EulerTourTree(final int n) {
            this.vertexes = new Node[n];
            for (int i = 0; i != n; i++) {
                vertexes[i] = new Node(i);
            }
            this.edges = new Node[n][n];
        }

        public void link(final int u, final int v) {
            Node first = vertexes[u];
            Node second = vertexes[v];
            NodePair A = Treap.split(
                    Treap.getRoot(first),
                    Treap.getVertexIndex(first) - 1
            );
            first = new Node(first.name);
            A.left = Treap.merge(A.left, first);

            NodePair B = Treap.split(
                    Treap.getRoot(second),
                    Treap.getVertexIndex(second) - 1
            );
            Node newBVertex = new Node(second.name);
            if (B.left != null) {
                B.left = Treap.split(
                        Treap.merge(B.left, newBVertex)
                        , 1
                ).right;
                Node lastInBRight = Treap.getLastNode(B.right);
                if (lastInBRight != null) {
                    vertexes[lastInBRight.name] = lastInBRight;
                    edges[lastInBRight.name][Treap.getFirstNode(B.left).name] = lastInBRight;
                }
                edges[v][u] = newBVertex;
            } else {
                edges[v][u] = Treap.getLastNode(B.right);
            }
            edges[u][v] = first;
            A.left = Treap.merge(A.left, B.right);
            A.left = Treap.merge(A.left, B.left);
            A.left = Treap.merge(A.left, A.right);
        }

        public void cut(final int u, final int v) {
            Node first = edges[u][v];
            Node second = edges[v][u];
            edges[u][v] = edges[v][u] = null;

            int firstVertexIndex = Treap.getVertexIndex(first);
            int secondVertexIndex = Treap.getVertexIndex(second);
            if (firstVertexIndex > secondVertexIndex) {
                Node temp = first;
                first = second;
                second = temp;
                firstVertexIndex = secondVertexIndex;
            }
            NodePair subFirst = Treap.split(
                    Treap.getRoot(first),
                    firstVertexIndex
            );
            NodePair subSecond = Treap.split(
                    subFirst.right,
                    Treap.getVertexIndex(second)
            );
            NodePair subThird = Treap.split(
                    subSecond.right, 1);
            subSecond.right = subThird.right;
            if (subThird.left != null) {
                vertexes[subThird.left.name] = first;
                if (subThird.right != null) {
                    Node vertex = Treap.getFirstNode(subThird.right);
                    edges[subThird.left.name][vertex.name] = first;
                }
            }
            Treap.merge(subFirst.left, subSecond.right);
        }

        public boolean isConnected(final int u, final int v) {
            return Treap.getRoot(vertexes[u]) == Treap.getRoot(vertexes[v]);
        }

        private Node[] vertexes;
        private Node[][] edges;

        private Random random = new Random();

        private static class NodePair {
            public NodePair(Node left, Node right) {
                this.left = left;
                this.right = right;
            }
            public NodePair() {
                this(null, null);
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
        public class Node {
            public Node(int name) {
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
            public int size, prior, name;
            public Node left, right, parent;
        }

        private static class Treap {

            private static int getNodeSize(Node n) {
                return n == null ? 0 : n.size;
            }

            public static Node getRoot(Node curVertex) {
                Node root = curVertex;
                while (root.parent != null) {
                    root = root.parent;
                }
                return root;
            }

            public static int getVertexIndex(Node cur) {
                int result = getNodeSize(cur.left) + 1;
                while (cur.parent != null) {
                    Node parent = cur.parent;
                    if (parent.right != null && parent.right == cur) {
                        result += getNodeSize(parent.left) + 1;
                    }
                    cur = parent;
                }
                return result;
            }

            public static Node getLastNode(Node v) {
                while (v.right != null) {
                    v = v.right;
                }
                return v;
            }

            public static Node getFirstNode(Node v) {
                while (v.left != null) {
                    v = v.left;
                }
                return v;
            }

            public static NodePair split(Node root, final int minRight) {
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

            public static Node merge(Node left, Node right) {
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
        }
    }

    protected FastReader getScanner() {
        return new FastReader();
    }

    protected void printResult(final int v) {
        out.println(v);
    }

    private FastWriter out;

    public void run() throws IOException {
        FastReader in = getScanner();
//        FastReader in = new FastReader();
        out = new FastWriter();
        final int n = in.nextInt();
        final int m = in.nextInt();
        EulerTourTree tree = new EulerTourTree(n);
        for (int i = 0; i != m; i++) {
            String command = in.next();
            final int u = in.nextVertex(),
                    v = in.nextVertex();
            switch (command) {
                case "link":
                    tree.link(u, v);
                    break;
                case "cut":
                    tree.cut(u, v);
                    break;
                default:
                    printResult(tree.isConnected(u, v) ? 1 : 0);
//                if (tree.isConnected(u, v)) {
//                    out.println(1);
//                } else {
//                    out.println(0);
//                }
            }
        }
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new C().run();
    }
}
