package LCA;

import java.io.*;
import java.util.*;

/**
 * Created by @author Telnov Sergey on 28.10.2017.
 */

public class Dinosaurs {

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() throws IOException {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("carno.in"))
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

    private static int log2(int value) {
        int result = 0;
        while (value >= 2) {
            result++;
            value /= 2;
        }
        return result;
    }

    public class DynamicLca {

        public DynamicLca(final int n) {

            this.parentsInTree = new int[n];
            this.depths = new int[n];
            this.vertexes = new JointVertex(n);

            this.size = 1;
            this.dp = new int[n][log2(n) + 1];
        }

        public void cut(final Integer u) {
            vertexes.union(vertexes.get(parentsInTree[u]), u);
        }

        public void add(final int u) {
            final Integer newVertex = size;
            depths[newVertex] = depths[u] + 1;
            size++;
            this.logSize = log2(size);
            parentsInTree[newVertex] = u;
            dp[newVertex][0] = u;
            for (int i = 1; i <= logSize; i++) {
                dp[newVertex][i] = dp[dp[newVertex][i - 1]][i - 1];
            }
        }

        private int lca(int a, int b) {
            if (depths[a] > depths[b])
                return lca(b, a);
            for (int i = this.logSize; i != -1; i--) {
                if (depths[b] - depths[a] >= Math.pow(2, i)) {
                    b = dp[b][i];
                }
            }
            if (a == b) {
                return vertexes.get(a);
            }
            for (int i = this.logSize; i != -1; i--) {
                if (dp[a][i] != dp[b][i]) {
                    a = dp[a][i];
                    b = dp[b][i];
                }
            }
            return vertexes.get(parentsInTree[a]);
        }

        private int[][] dp;
        private int[] depths;
        private int[] parentsInTree;

        private int logSize;
        private JointVertex vertexes;
        private int size;

        private class JointVertex {

            public JointVertex(final int n) {
                this.parents = new int[n];
                for (int i = 0; i != n; i++) {
                    parents[i] = i;
                }
            }

            void union(int u, int v) {
                u = get(u);
                v = get(v);
                if (u == v) {
                    return;
                }
                parents[v] = u;
            }

            int get(final int x) {
                int root = x;
                while (parents[root] != root) {
                    root = parents[root];
                }
                int i = x;
                while (parents[i] != i) {
                    final int temp = parents[i];
                    parents[i] = root;
                    i = temp;
                }
                return root;
            }

            private int[] parents;
        }
    }

    public void run() throws IOException {
        FastReader input = new FastReader();
        PrintWriter output = new PrintWriter(new FileWriter("carno.out"));

        final int m = input.nextInt();
        DynamicLca tree = new DynamicLca(m);

        for (int i = 0; i != m; i++) {
            String command = input.next();
            switch (command) {
                case "+":
                    tree.add(input.nextInt() - 1);
                    break;
                case "-":
                    tree.cut(input.nextInt() - 1);
                    break;
                default:
                    output.println(tree.lca(input.nextInt() - 1, input.nextInt() - 1) + 1);
            }
        }
        output.close();
    }

    public static void main(String[] args) throws IOException {
        new Dinosaurs().run();
    }
}
