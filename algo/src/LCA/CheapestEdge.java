package LCA;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by @author Telnov Sergey on 20.10.2017.
 */

public class CheapestEdge {

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() throws IOException {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("minonpath.in"))
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

    private int n;
    private int LOG_N;

    private int[] parents;
    private int[] depths;
    private int[][] jumps;
    private int[][] dp;
    private int[] weight;
    private List<List<Integer>> graph;

    private FastReader input;

    private int log2(int value) {
        int result = 0;
        while (value >= 2) {
            result++;
            value /= 2;
        }
        return result;
    }

    private void scan() throws IOException {
        input = new FastReader();

        n = input.nextInt();

        LOG_N = log2(n);

        parents = new int[n];
        depths = new int[n];
        jumps = new int[n][LOG_N + 1];
        dp = new int[n][LOG_N + 1];
        weight = new int[n];
        graph = new ArrayList<>(n);

        weight[0] = Integer.MAX_VALUE;

        for (int i = 0; i != n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i != n; i++) {
            int p = input.nextInt(),
                    w = input.nextInt();

            p--;
            parents[i] = p;
            graph.get(p).add(i);
            weight[i] = w;
        }
    }

    private void countDepths(final Integer curr, int depth) {
        depths[curr] = depth;
        depth++;
        for (final Integer next : graph.get(curr)) {
            countDepths(next, depth);
        }
    }

    private void preprocess() {
        countDepths(0, 0);

        for (int i = 0; i != n; i++) {
            jumps[i][0] = parents[i];
            dp[i][0] = weight[i];
        }

        for (int i = 1; i <= LOG_N; i++) {
            dp[0][i] = Integer.MAX_VALUE;
            for (int v = 1; v != n; v++) {
                jumps[v][i] = jumps[jumps[v][i - 1]][i - 1];
                if (jumps[v][i - 1] == 0) {
                    dp[v][i] = Integer.MAX_VALUE;
                } else {
                    dp[v][i] = Math.min(
                            dp[jumps[v][i - 1]][i - 1],
                            dp[v][i - 1]
                    );
                }
            }
        }
    }

    private int lca(final int w, final int v) {
        if (depths[w] > depths[v])
            return lca(v, w);
        int a = w,
                b = v;
        int min = Integer.MAX_VALUE;
        for (int i = LOG_N; i != -1; i--) {
            if (depths[b] - depths[a] >= Math.pow(2, i)) {
                min = Math.min(min, dp[b][i]);
                b = jumps[b][i];
            }
        }
        if (a == b) {
            return min;
        }
        for (int i = LOG_N; i != -1; i--) {
            if (jumps[a][i] != jumps[b][i]) {
                min = Math.min(
                        min,
                        Math.min(dp[a][i], dp[b][i])
                );
                a = jumps[a][i];
                b = jumps[b][i];
            }
        }
        return Math.min(
                min,
                Math.min(
                        weight[a],
                        weight[b]
                )
        );
    }

    private void solve() throws IOException {
        preprocess();

        PrintWriter output = new PrintWriter(new FileWriter("minonpath.out"));
        final int m = input.nextInt();

        for (int i = 0; i != m; i++) {
            final int a = input.nextInt(),
                    b = input.nextInt();

            output.println(lca(a - 1, b - 1));
        }
        output.close();
    }

    public void run() throws IOException {
        scan();
        solve();
    }

    public static void main(String[] args) throws IOException {
        new CheapestEdge().run();
    }
}
