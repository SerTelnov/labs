package TreeDecomposition;

import java.io.*;
import java.util.*;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

/**
 * Created by @author Telnov Sergey on 06.11.2017.
 */

public class A {

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
    static class FastWriter {
        public FastWriter() {
            bw = new BufferedWriter(
                    new OutputStreamWriter(System.out)
            );
            sb = new StringBuilder();
        }

        public void println(final int i) {
            sb.append(i).append("\n");
        }

        public void close() throws IOException {
            bw.write(sb.toString());
            bw.flush();
            bw.close();
        }

        private BufferedWriter bw;
        private StringBuilder  sb;
    }

    public class HeavyLight {

        public HeavyLight() {
            final int n = graph.length;

            heavy = new int[n];
            Arrays.fill(heavy, -1);

            parents = new int[n];
            depths = new int[n];
            pathRoot = new int[n];
            weightOfLightEdges = new int[n];
            used = new boolean[n];
            keys = new int[n];

            parents[0] = -1;

            dfs(0);

            segmentTree = new SegmentTree(n);
            getPath(0, 0);
        }

        public int query(int u, int v) {
            if (pathRoot[u] == pathRoot[v]) {
                return segmentTree.getSum(u, v);
            } else if (depths[u] > depths[v]) {
                return weightOfLightEdges[u];
            } else {
                return weightOfLightEdges[v];
            }
        }

        public void modify(int u, int v) {
            while (pathRoot[u] != pathRoot[v]) {
                if (depths[pathRoot[u]] < depths[pathRoot[v]]) {
                    int temp = u;
                    u = v;
                    v = temp;
                }
                if (pathRoot[u] != u) {
                    segmentTree.updateRange(u, pathRoot[u]);
                }
                weightOfLightEdges[pathRoot[u]]++;
                u = parents[pathRoot[u]];
            }
            segmentTree.updateRange(u, v);
        }

        private boolean[] used;
        private int eulerTourCounter = 0;
        private void getPath(int curr, int pathRoot) {
            if (curr == -1) {
                return;
            }

            used[curr] = true;
            keys[curr] = eulerTourCounter++;
            this.pathRoot[curr] = pathRoot;

            getPath(heavy[curr], pathRoot);
            for (final int next : graph[curr]) {
                if (!used[next] && next != heavy[curr]) {
                    getPath(next, next);
                }
            }
        }

        private int dfs(final Integer root) {
            int size = 1;
            int maxSubTreeSize = 0;
            for (final Integer children : graph[root]) {
                if (children == parents[root])
                    continue;
                parents[children] = root;
                depths[children] = depths[root] + 1;
                int currSubTreeSize = dfs(children);
                if (maxSubTreeSize < currSubTreeSize) {
                    maxSubTreeSize = currSubTreeSize;
                    heavy[root] = children;
                }
                size += currSubTreeSize;
            }
            return size;
        }

        private int[] heavy, parents, depths, pathRoot;
        private int[] weightOfLightEdges;
        private int[] keys;

        private SegmentTree segmentTree;

        private class SegmentTree {

            public SegmentTree(final int n) {
                this.numberOfVertex = n - 1;
                this.data = new int[4 * n];
            }

            public int getSum(final int u, final int v) {
                final int a = keys[u];
                final int b = keys[v];
                if (a > b) {
                    return sum(1, 0, numberOfVertex, b + 1, a);
                } else {
                    return sum(1, 0, numberOfVertex, a + 1, b);
                }
            }

            private int sum(int node, int treeLeft, int treeRight, int left, int right) {
                if (left > right)
                    return 0;

                if (left == treeLeft && right == treeRight)
                    return data[node];

                int middle = (treeLeft + treeRight) / 2;
                return sum(node * 2, treeLeft, middle, left, min(right, middle))
                        + sum(node * 2 + 1, middle + 1, treeRight, max(left, middle + 1), right)
                        + data[node];
            }

            public void updateRange(final int u, final int v) {
                final int left = keys[u];
                final int right = keys[v];
                if (left > right) {
                    incRange(1, 0, numberOfVertex, right + 1, left);
                } else {
                    incRange(1, 0, numberOfVertex, left + 1, right);
                }
            }

            private void incRange(int node, int treeLeft, int treeRight, int left, int right) {
                if (treeLeft > treeRight || treeLeft > right || treeRight < left) return;

                if (treeLeft >= left && treeRight <= right) {
                    data[node]++;
                    return;
                }

                int mid = (treeLeft + treeRight) / 2;
                incRange(node * 2, treeLeft, mid, left, right);
                incRange((node * 2) + 1, mid + 1, treeRight, left, right);
            }

            private int[] data;
            private final int numberOfVertex;
        }
    }

    private int m;
    private FastReader input;
    private HeavyLight tree;
    private ArrayList<Integer>[] graph;

    protected FastReader getInputFile() throws IOException {
        return new FastReader();
    }

    private void scan() throws IOException {
        input = new FastReader();
//        input = getInputFile();
        final int n = input.nextInt();
        m = input.nextInt();
        graph = new ArrayList[n];
        for (int i = 0; i != n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i != n; i++) {
            int u = input.nextInt(),
                    v = input.nextInt();
            u--;
            v--;
            graph[u].add(v);
            graph[v].add(u);
        }
        tree = new HeavyLight();
    }

    protected void getResult(final int result) {
        System.out.println(result);
    }

    public void run() throws IOException {
        scan();
        FastWriter out = new FastWriter();
        for (int i = 0; i != m; i++) {
            String command = input.next();
            final int u = input.nextInt(),
                    v = input.nextInt();
            if (command.equals("P")) {
                tree.modify(u - 1, v - 1);
            } else {
                out.println(tree.query(u - 1, v - 1));
//                getResult(tree.query(u - 1, v - 1));
            }
        }
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new A().run();
    }
}
