package LCA;

import java.io.*;
import java.util.*;

/**
 * Created by @author Telnov Sergey on 21.10.2017.
 */

public class RevisitedProblem {

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() throws IOException {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("lca_rmq.in"))
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

    private class QuestionFactory {
        QuestionFactory(final int a, final int b,
                        final int x, final int y, final int z, final int n) {
            this.x = x;
            this.y = y;
            this.z = z;

            this.a = a;
            this.b = b;

            this.u = a;
            this.v = b;

            this.n = n;
        }

        void getQuestion(final int v) {
            a = (x * a + y * b + z) % n;
            b = (x * b + y * a + z) % n;
            u = (int) (a + v) % n;
            this.v = (int) b;
        }

        private long a, b;
        private final long x, y, z;
        private final int n;

        public int u, v;
    }

    private class SparseTable {

        public SparseTable() {

            this.keys = new int[n];
            this.depths = new int[n];
            this.vertexes = new int[2 * n];
            setVertexes(0, -1, 0);

            int n = this.size;

            logTable = new int[n + 1];
            powTable = new int[n];

            powTable[0] = 1;

            for (int i = 2; i <= n; i++) {
                logTable[i] = logTable[i >> 1] + 1;
                powTable[i - 1] = powTable[i - 2] * 2;
            }

            data = new int[logTable[n] + 1][n];

            for (int i = 0; i != n; i++) {
                data[0][i] = vertexes[i];
            }

            for (int k = 1; k != data.length; k++) {
                for (int i = 0; i + powTable[k] <= n; i++) {
                    data[k][i] = min(data[k - 1][i], data[k - 1][i + powTable[k - 1]]);
                }
            }
        }

        public int getLca(final int u, final int v) {
            final int left = keys[u],
                    right = keys[v];
            if (left > right)
                return getLca(v, u);
            int k = logTable[right - left];
            return min(data[k][left], data[k][right - powTable[k] + 1]);
        }

        private int min(final int i, final int j) {
            return depths[i] > depths[j] ? j : i;
        }

        private int size = 0;

        private void setVertexes(final Integer curr, final Integer prev, int depth) {
            keys[curr] = size;
            depths[curr] = depth;
            vertexes[size++] = curr;
            depth++;
            if (graph.containsKey(curr)) {
                for (final Integer next : graph.get(curr)) {
                    setVertexes(next, curr, depth);
                }
            }
            if (prev != -1) {
                vertexes[size++] = prev;
            }
        }

        private int[] vertexes;
        private int[] keys;
        private int[] logTable, powTable;
        private int[][] data;
        private int[] depths;
    }

    private HashMap<Integer, List<Integer>> graph;

    private int n;
    private int m;
    private QuestionFactory questions;

    private void scan() throws IOException {
        FastReader input = new FastReader();

        n = input.nextInt();
        m = input.nextInt();

        graph = new HashMap<>(n);

        for (int i = 1; i != n; i++) {
            Integer vertex = input.nextInt();
            if (!graph.containsKey(vertex)) {
                graph.put(vertex, new ArrayList<>());
            }
            graph.get(vertex).add(i);
        }

        questions = new QuestionFactory(input.nextInt(), input.nextInt(),
                input.nextInt(), input.nextInt(), input.nextInt(), n);
    }

    private void solve() throws IOException {
        SparseTable sparseTable = new SparseTable();
        int ans = sparseTable.getLca(questions.u, questions.v);
        long result = ans;
        for (int i = 1; i != m; i++) {
            questions.getQuestion(ans);
            ans = sparseTable.getLca(questions.u, questions.v);
            result += ans;
        }
        PrintWriter output = new PrintWriter(new FileWriter("lca_rmq.out"));
        output.print(result);
        output.close();
    }

    public void run() throws IOException {
        scan();
        solve();
    }

    public static void main(String[] args) throws IOException {
        new RevisitedProblem().run();
    }
}
