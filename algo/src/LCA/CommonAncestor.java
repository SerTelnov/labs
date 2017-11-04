package LCA;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by @author Telnov Sergey on 18.10.2017.
 */

public class CommonAncestor {

    private int n;
    private int[] depths;
    private int[] parents;
    private int[][] dp;

    private int LOG_N;

    private Scanner input;


    private int log2(int value) {
        int result = 0;
        while (value >= 2) {
            result++;
            value /= 2;
        }
        return result;
    }

    private void scan() throws IOException {
        input = new Scanner(new File("lca.in"));

        n = input.nextInt();
        input.nextLine();

        LOG_N = log2(n);

        dp = new int[n][LOG_N + 1];
        depths = new int[n];
        parents = new int[n];

        parents[0] = -1;

        for (int i = 1; i != n; i++) {
            int v = input.nextInt();
            input.nextLine();
            v--;

            parents[i] = v;
            depths[i] = depths[v] + 1;
        }
    }

    private void preprocess() {
        for (int i = 1; i != n; i++) {
            dp[i][0] = parents[i];
        }

        for (int i = 1; i <= LOG_N; i++) {
            for (int v = 1; v != n; v++) {
                dp[v][i] = dp[dp[v][i - 1]][i - 1];
            }
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

    private void solve() throws IOException {
        preprocess();
        int m = input.nextInt();
        input.nextLine();

        PrintWriter output = new PrintWriter(new FileWriter("lca.out"));
        for (int i = 0; i != m; i++) {
            final int a = input.nextInt(),
                    b = input.nextInt();
            output.println(lca(a - 1, b - 1) + 1);
        }
        output.close();
    }

    private void run() throws IOException {
        scan();
        solve();
    }

    public static void main(String[] args) throws IOException {
        new CommonAncestor().run();
    }
}
