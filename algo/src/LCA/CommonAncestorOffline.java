package LCA;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by @author Telnov Sergey on 19.10.2017.
 */

public class CommonAncestorOffline {

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader() throws IOException {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("lca.in"))
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

    private final int SIZE = 500000;

    private int n = 1;
    private int LOG_N;

    private int[] depths = new int[SIZE];
    private int[] parents = new int[SIZE];

    private int[][] dp;

    private int log2(int n) {
        int result = 1;
        while ((1 << result) <= n) {
            result++;
        }
        return result;
    }

    private void add(final int vertex, final int leaf) {
        n++;
        LOG_N = log2(n);
        depths[leaf] = depths[vertex] + 1;
        parents[leaf] = vertex;
        dp[leaf][0] = vertex;
        for (int i = 1; i != LOG_N; i++) {
            dp[leaf][i] = dp[dp[leaf][i - 1]][i - 1];
        }
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

    private void run() throws IOException {
        dp = new int[SIZE][log2(SIZE)];
        FastReader input = new FastReader();
        PrintWriter output = new PrintWriter(new FileWriter("lca.out"));
        final int k = input.nextInt();
        for (int i = 0; i != k; i++) {
            String command = input.next();
            final int a = input.nextInt(),
                    b = input.nextInt();
            if (command.equals("ADD")) {
                add(a - 1 , b - 1);
            } else {
                output.println(lca(a - 1, b - 1) + 1);
            }
        }
        output.close();
    }

    public static void main(String[] args) throws IOException {
        new CommonAncestorOffline().run();
    }
}
