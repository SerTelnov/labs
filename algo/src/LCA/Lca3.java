package LCA;

import java.io.*;
import java.util.*;

/**
 * Created by @author Telnov Sergey on 24.10.2017.
 */

public class Lca3 {

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() throws IOException {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("lca3.in"))
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

        public DynamicLca(List<List<Integer>> graph, int[] parents) {
            this.graph = graph;
            this.parents = parents;

            this.n = parents.length;
            this.LOG_N = log2(n - 1);

            this.dp = new int[n][LOG_N + 1];
            this.depths = new int[n];
            this.roots = new int[n];

            for (int i = 1; i != n; i++) {
                if (parents[i] == 0) {
                    roots[i] = i;
                    reCount(i);
                }
            }
        }

        public int lca(int a, int b) {
            if (depths[a] > depths[b])
                return lca(b, a);
            for (int i = LOG_N; i != -1; i--) {
                if (depths[b] - depths[a] >= Math.pow(2, i)) {
                    b = dp[b][i];
                }
            }
            if (depths[a] != depths[b]) {
                return 0;
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

        public void link(int u, int v) {
            graph.get(v).add(u);
            parents[u] = v;
            reCount(v);
        }

        private void reCount(final Integer prev) {
            for (final Integer curr : graph.get(prev)) {
                roots[curr] = roots[prev];

                dp[curr][0] = parents[curr];
                for (int i = 1; i <= LOG_N; i++) {
                    dp[curr][i] = dp[dp[curr][i - 1]][i - 1];
                }

                depths[curr] = depths[prev] + 1;
                reCount(curr);
            }
        }

        private int[][] dp;
        private int[] depths;
        private int[] parents;
        private int[] roots;
        private List<List<Integer>> graph;

        private final int n;
        private final int LOG_N;
    }

    private class QuestionFactory {
        public QuestionFactory(final int n) {
            this.n = n;
        }

        public void nextQuestion(final int x, final int y, final int prevAns) {
            this.u = generateValue(x, prevAns);
            this.v = generateValue(y, prevAns);
        }

        private int generateValue(final int value, final int prevAns) {
            return (value - 1 + prevAns) % n + 1;
        }

        public int u, v;
        private final int n;
    }

    private FastReader input;
    private DynamicLca tree;
    private QuestionFactory questions;

    private void scan() throws IOException {

        input = new FastReader();
        final int n = input.nextInt();

        List<List<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            final int parent = input.nextInt();
            if (parent != 0) {
                graph.get(parent).add(i);
            }
            parents[i] = parent;
        }

        tree = new DynamicLca(graph, parents);
        questions = new QuestionFactory(n);
    }

    private void solve() throws IOException {

        PrintWriter output = new PrintWriter(new FileWriter("lca3.out"));
        final int m = input.nextInt();

        int ans = 0;

        for (int i = 0; i != m; i++) {
            final int command = input.nextInt();

            final int x = input.nextInt(),
                    y = input.nextInt();

            questions.nextQuestion(x, y, ans);

            if (command == 1) {
                tree.link(questions.u, questions.v);
            } else {
                ans = tree.lca(questions.u, questions.v);
                output.println(ans);
            }
        }
        output.close();
    }

    public void run() throws IOException {
        scan();
        solve();
    }

    public static void main(String[] args) throws IOException {
        new Lca3().run();
    }
}