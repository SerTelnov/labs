package LCA;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by @author Telnov Sergey on 19.10.2017.
 */

public class Ancestor {

    private int n;
    private int root;
    private int[] depths;
    private int[] parents;
    private int[][] dp;
    private List<List<Integer>> graph;

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
        input = new Scanner(new File("ancestor.in"));

        n = input.nextInt();
        input.nextLine();

        LOG_N = log2(n);

        dp = new int[n][LOG_N + 1];
        depths = new int[n];
        parents = new int[n];
        graph = new ArrayList<>(n);

        for (int i = 0; i != n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i != n; i++) {
            int v = input.nextInt();
            v--;
            if (v == -1) {
                root = i;
                parents[i] = 0;
            } else {
                graph.get(v).add(i);
                parents[i] = v;
            }
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
        for (int i = 0; i != n; i++) {
            dp[i][0] = parents[i];
        }

        for (int i = 1; i <= LOG_N; i++) {
            for (int v = 0; v != n; v++) {
                dp[v][i] = dp[dp[v][i - 1]][i - 1];
            }
        }
    }

    private boolean isAncestor(int a, int b) {
        if (depths[a] > depths[b])
            return false;
        for (int i = LOG_N; i != -1; i--) {
            if (depths[b] - depths[a] >= Math.pow(2, i)) {
                b = dp[b][i];
            }
        }
        return a == b;
    }

    private void solve() throws IOException {
        countDepths(root, 0);
        preprocess();
        final int m = input.nextInt();
        input.nextLine();

        PrintWriter output = new PrintWriter(new FileWriter("ancestor.out"));

        for (int i = 0; i != m; i++) {
            int a = input.nextInt(),
                    b = input.nextInt();
            input.nextLine();
            if (isAncestor(a - 1, b -1)) {
                output.println(1);
            } else {
                output.println(0);
            }
        }
        output.close();
    }

    public void run() throws IOException {
        scan();
        solve();
    }

    public static void main(String[] args) throws IOException {
        new Ancestor().run();
    }
}
