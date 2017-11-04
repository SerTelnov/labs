package LCA;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by @author Telnov Sergey on 23.10.2017.
 */

public class DynamicLCA {

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() throws IOException {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("dynamic.in"))
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

    private int log2(int value) {
        int result = 0;
        while (value >= 2) {
            result++;
            value /= 2;
        }
        return result;
    }

    private class DynamicTree {

        public DynamicTree(List<List<Integer>> graph) {

            this.graph = graph;
            SIZE = graph.size();
            LOG_N = log2(SIZE);

            parents = new int[SIZE];
            depths = new int[SIZE];
            dp = new int[SIZE][LOG_N + 1];

            init(0, 0, -1);
            setDynamic();
        }

        public int getLca(final int u, final int v) {
            final int res = lca(u, v);
            if (root == 0) {
                return res;
            } else {
                if (!isAncestor(res, root)) {
                    return res;
                }
                final int x = lca(u, root);
                final int y = lca(v, root);
                return depths[x] > depths[y] ? x : y;
            }
        }

        public void reBuild(final int newRoot) {
            this.root = newRoot;
        }

        private boolean isAncestor(int a, int b) {
            if (depths[a] > depths[b]) {
                return false;
            }
            for (int i = LOG_N; i != -1; i--) {
                if (depths[b] - depths[a] >= Math.pow(2, i)) {
                    b = dp[b][i];
                }
            }
            return a == b;
        }

        private int lca(int a, int b) {
            if (depths[a] > depths[b])
                return lca(b, a);
            for (int i = LOG_N; i != -1; i--) {
                if (depths[b] - depths[a] >= Math.pow(2, i)) {
                    b = dp[b][i];
                }
            }
            if (a == b) {
                return a;
            }
            for (int i = LOG_N; i != -1; i--) {
                if (dp[a][i] != dp[b][i]) {
                    a = dp[a][i];
                    b = dp[b][i];
                }
            }
            return parents[a];
        }

        private void init(final Integer curr, int depth, final Integer prev) {
            depths[curr] = depth;
            depth++;
            for (final Integer next : graph.get(curr)) {
                if (parents[next] == 0) {
                    if (next.equals(prev))
                        continue;
                    parents[next] = curr;
                    init(next, depth, curr);
                }
            }
        }

        private void setDynamic() {
            for (int i = 1; i != SIZE; i++) {
                dp[i][0] = parents[i];
            }

            for (int i = 1; i <= LOG_N; i++) {
                for (int v = 1; v != SIZE; v++) {
                    dp[v][i] = dp[dp[v][i - 1]][i - 1];
                }
            }
        }

        private final int LOG_N;
        private int root = 0;
        private final int SIZE;

        private int[] parents;
        private int[][] dp;
        private int[] depths;
        private List<List<Integer>> graph;
    }

    public void run() throws IOException {
        FastReader input = new FastReader();
        PrintWriter output = new PrintWriter(new FileWriter("dynamic.out"));
        while (true) {
            final int n = input.nextInt();
            if (n == 0) {
                break;
            }
            List<List<Integer>> graph = new ArrayList<>(n);
            for (int i = 0; i != n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 1; i != n; i++) {
                final int v = input.nextInt(),
                        u = input.nextInt();
                graph.get(v - 1).add(u - 1);
                graph.get(u - 1).add(v - 1);
            }
            DynamicTree tree = new DynamicTree(graph);

            final int m = input.nextInt();
            for (int i = 0; i != m; i++) {
                String command = input.next();
                if (command.equals("?")) {
                    final int u = input.nextInt(),
                            v = input.nextInt();
                    output.println(tree.getLca(u - 1, v - 1) + 1);
                } else if (command.equals("!")) {
                    final int newRoot = input.nextInt();
                    tree.reBuild(newRoot - 1);
                }
            }
        }
        output.close();
    }

    public static void main(String[] args) throws IOException {
        new DynamicLCA().run();
    }
}
