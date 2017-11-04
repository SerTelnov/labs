package LCA;

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by @author Telnov Sergey on 20.10.2017.
 */
public class CheapestDist {

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() throws IOException {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("tree.in"))
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
    private int[] weights;
    private List<List<Pair<Integer, Integer>>> graph;

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
        weights = new int[n];
        graph = new ArrayList<>(n);

        for (int i = 0; i != n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i != n; i++) {
            int u = input.nextInt(),
                    v = input.nextInt(),
                    w = input.nextInt();

            graph.get(u).add(new Pair<>(v, w));
            graph.get(v).add(new Pair<>(u, w));
        }
    }

    private boolean[] used;

    private void countDepths(final int curr, int depth) {
        depths[curr] = depth;
        depth++;
        used[curr] = true;
        for (final Pair next : graph.get(curr)) {
            int nextV = (int) next.getKey();
            if (!used[nextV]) {
                weights[nextV] = (int) next.getValue();
                parents[nextV] = curr;
                countDepths(nextV, depth);
            }
        }
    }

    private void preprocess() {
        used = new boolean[n];
        countDepths(0, 0);

        for (int i = 0; i != n; i++) {
            jumps[i][0] = parents[i];
            dp[i][0] = weights[i];
        }

        for (int i = 1; i <= LOG_N; i++) {
            for (int v = 0; v != n; v++) {
                jumps[v][i] = jumps[jumps[v][i - 1]][i - 1];
                dp[v][i] = dp[jumps[v][i - 1]][i - 1] + dp[v][i - 1];
            }
        }
    }

    private int lca(final int w, final int v) {
        if (depths[w] > depths[v])
            return lca(v, w);
        int a = w,
                b = v;
        int result = 0;
        for (int i = LOG_N; i != -1; i--) {
            if (depths[b] - depths[a] >= Math.pow(2, i)) {
                result += dp[b][i];
                b = jumps[b][i];
            }
        }
        if (a == b) {
            return result;
        }
        for (int i = LOG_N; i != -1; i--) {
            if (jumps[a][i] != jumps[b][i]) {
                result += dp[a][i] + dp[b][i];
                a = jumps[a][i];
                b = jumps[b][i];
            }
        }
        return result + (weights[a] + weights[b]);
    }

    private void solve() throws IOException {
        preprocess();

        PrintWriter output = new PrintWriter(new FileWriter("tree.out"));
        final int m = input.nextInt();

        for (int i = 0; i != m; i++) {
            final int a = input.nextInt(),
                    b = input.nextInt();

            output.println(lca(a, b));
        }
        output.close();
    }

    public void run() throws IOException {
        scan();
        solve();
    }

    public static void main(String[] args) throws IOException {
        new CheapestDist().run();
    }
}
