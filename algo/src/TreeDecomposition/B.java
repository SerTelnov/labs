package TreeDecomposition;

import java.io.*;
import java.util.*;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

/**
 * Created by @author Telnov Sergey on 07.11.2017.
 */
public class B {

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() throws IOException {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("mail.in"))
            );
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }


    public class HeavyLight {

        public HeavyLight(final List<List<Integer>> graph, int[] weight) {
            final int n = graph.size();
            this.graph = graph;

            heavy = new int[n];
            Arrays.fill(heavy, -1);

            parents = new int[n];
            depths = new int[n];
            pathRoot = new int[n];

            parents[0] = -1;
            depths[0] = 0;

            dfs(0);

            this.segmentTree = new HeavyLight.MultipleSegmentTree(n);

            int[] tempWeights = new int[weight.length];
            for (int u = 0; u != n; u++) {
                if (parents[u] == -1 || heavy[parents[u]] != u) {
                    HashMap<Integer, Integer> currPath = new HashMap<>();
                    for (int v = u, index = 0; v != -1; v = heavy[v], index++) {
                        currPath.put(v, index);
                        tempWeights[index] = weight[v];
                        pathRoot[v] = u;
                    }
                    segmentTree.addSegmentTree(u, currPath, tempWeights);
                }
            }
        }

        public int query(int u, int v) {
            if (pathRoot[u] == pathRoot[v]) {
                return segmentTree.getRangeSum(pathRoot[u], u, v);
            }
            if (depths[pathRoot[u]] < depths[pathRoot[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }
            return Math.max(
                    query(parents[pathRoot[u]], v),
                    segmentTree.getRangeSum(pathRoot[u], u, pathRoot[u])
            );
        }

        public void modify(int u, final int value) {
            segmentTree.setValue(pathRoot[u], u, value);
        }

        private int dfs(final Integer root) {
            int size = 1;
            int maxSubTreeSize = 0;
            for (final Integer children : graph.get(root)) {
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
        private List<List<Integer>> graph;

        private HeavyLight.MultipleSegmentTree segmentTree;

        private class MultipleSegmentTree {

            public MultipleSegmentTree(final int n) {
                this.data = new HashMap<>();
                this.keys = new HashMap<>(n);
            }

            public void addSegmentTree(final Integer numberOfPath, HashMap<Integer, Integer> currKeys, int[] weights) {
                data.put(numberOfPath, new HeavyLight.MultipleSegmentTree.SegmentTree(weights, currKeys.size()));
                keys.putAll(currKeys);
            }

            public void setValue(final Integer numberOfPath, final Integer u, final int value) {
                data.get(numberOfPath).setValue(keys.get(u), value);
            }

            public int getRangeSum(final Integer numberOfPath, final Integer u, final Integer v) {
                return data.get(numberOfPath).getMax(keys.get(u), keys.get(v));
            }

            private HashMap<Integer, HeavyLight.MultipleSegmentTree.SegmentTree> data;
            private HashMap<Integer, Integer> keys;

            private class SegmentTree {

                public SegmentTree(int[] weight, final int n) {
                    this.numberOfVertex = n - 1;
                    this.data = new int[4 * n];
                    build(weight, 1, 0, numberOfVertex);
                }

                private void build(int[] a, int node, int left, int right) {
                    if (left == right) {
                        data[node] = a[left];
                    } else {
                        int middle = (left + right) / 2;
                        build(a, node * 2, left, middle);
                        build(a, node * 2 + 1, middle + 1, right);
                        data[node] = Integer.max(
                                data[node * 2],
                                data[node * 2 + 1]
                        );
                    }
                }

                public int getMax(final int u, final int v) {
                    if (u > v) {
                        return getMaxOnRange(1, 0, numberOfVertex, v, u);
                    } else {
                        return getMaxOnRange(1, 0, numberOfVertex, u, v);
                    }
                }

                private int getMaxOnRange(int node, int treeLeft, int treeRight, int left, int right) {
                    if (left > right)
                        return 0;
                    if (left == treeLeft && right == treeRight)
                        return data[node];
                    int middle = (treeLeft + treeRight) / 2;
                    return Integer.max(
                            getMaxOnRange(node * 2, treeLeft, middle, left, min(right, middle)),
                            getMaxOnRange(node * 2 + 1, middle + 1, treeRight, max(left, middle + 1), right)
                    );
                }

                public void setValue(final int u, final int value) {
                    update(1, 0, numberOfVertex, u, value);
                }

                private void update(int node, int treeLeft, int treeRight, int index, int newValue) {
                    if (treeLeft == treeRight)
                        data[node] = newValue;
                    else {
                        int middle = (treeLeft + treeRight) / 2;
                        if (index <= middle) {
                            update(node * 2, treeLeft, middle, index, newValue);
                        } else {
                            update(node * 2 + 1, middle + 1, treeRight, index, newValue);
                        }
                        data[node] = Integer.max(
                            data[node * 2],
                            data[node * 2 + 1]
                        );
                    }
                }
                private int[] data;
                private final int numberOfVertex;
            }

        }
    }

    private FastReader input;
    private HeavyLight tree;

    private void scan() throws IOException {
        input = new FastReader();

        final int n = input.nextInt();
        int[] weight = new int[n];
        for (int i = 0; i != n; i++) {
            weight[i] = input.nextInt();
        }
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i != n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 1; i != n; i++) {
            final int u = input.nextInt(),
                    v = input.nextInt();
            graph.get(u - 1).add(v - 1);
            graph.get(v - 1).add(u - 1);
        }
        tree = new HeavyLight(graph, weight);
    }

    public void run() throws IOException {
        scan();
        final int k = input.nextInt();
        PrintWriter out = new PrintWriter(new FileWriter("mail.out"));
        for (int i = 0; i != k; i++) {
            String command = input.next();
            if (command.equals("?")) {
                out.println(tree.query(
                        input.nextInt() - 1,
                        input.nextInt() - 1)
                );
            } else {
                tree.modify(input.nextInt() - 1, input.nextInt());
            }
        }
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new B().run();
    }
}
