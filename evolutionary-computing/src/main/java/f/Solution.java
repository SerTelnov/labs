package f;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class Solution {

    private int edgesCount;
    private int[] t;

    private double[][] system;
    private double[] ans;

    private double[] solveSystem() {
        final int n = edgesCount;

        for (int p = 0; p != n; p++) {
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(system[i][p]) > Math.abs(system[max][p])) {
                    max = i;
                }
            }

            if (p != max) {
                double[] temp = system[p];
                system[p] = system[max];
                system[max] = temp;

                double t = ans[p];
                ans[p] = ans[max];
                ans[max] = t;
            }

            for (int i = p + 1; i < n; i++) {
                if (system[p][p] != 0) {
                    final double alpha = system[i][p] / system[p][p];
                    ans[i] -= alpha * ans[p];
                    for (int j = p; j < n; j++) {
                        system[i][j] -= alpha * system[p][j];
                    }
                }
            }
        }

        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += system[i][j] * x[j];
            }

            if (system[i][i] != 0) {
                x[i] = (ans[i] - sum) / system[i][i];
            }
        }

        return x;
    }

    private void test(String in, double[] out) {
        int[] counters = new int[edgesCount];
        int prevPosition = 0;
        final double lenDiv = 1.0 / (double) in.length();

        for (int i = 0; i != in.length(); i++) {
            final int curEdgeIdx = prevPosition * 2 + (in.charAt(i) - '0');
            counters[curEdgeIdx]++;

            for (int x = 0; x != edgesCount; x++) {
                final double xValue = (double) counters[x];

                for (int y = 0; y != edgesCount; y++) {
                    final double yValue = (double) counters[y];
                    system[x][y] += xValue * yValue * lenDiv;
                }

                ans[x] += xValue * out[i] * lenDiv;
            }

            prevPosition = t[curEdgeIdx];
        }
    }

    public void run() throws IOException {
        FastReader reader = new FastReader("continuous.in");
        int n = reader.nextInt();
        final int m = reader.nextInt();

        edgesCount = 2 * n;
        t = new int[edgesCount];

        for (int i = 0; i != n; i++) {
            t[2 * i] = reader.nextVertex();
            t[2 * i + 1] = reader.nextVertex();
        }

        String[] inTest = new String[m];
        double[][] outTest = new double[m][];

        for (int i = 0; i != m; i++) {
            final int len = reader.nextInt();
            inTest[i] = reader.next();

            outTest[i] = new double[len];
            for (int j = 0; j != len; j++) {
                outTest[i][j] = reader.nextDouble();
            }
        }

        ans = new double[2 * n];
        system = new double[2 * n][2 * n];

        for (int i = 0; i != m; i++) {
            test(inTest[i], outTest[i]);
        }

        double[] ans = solveSystem();

        FastWriter writer = new FastWriter("continuous.out");
        for (int i = 0; i != n; i++) {
            writer.print(ans[i * 2]);
            writer.println(ans[i * 2 + 1]);
        }

        writer.close();
    }

    public static void main(String[] args) throws IOException {
        new Solution().run();
    }

    public class FastReader {

        private BufferedReader br;
        private StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String fileName) throws IOException {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileName))
            );
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        public Integer nextInteger() throws IOException {
            return Integer.valueOf(next());
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        public int nextVertex() throws IOException {
            return nextInt() - 1;
        }

        public boolean hasNext() throws IOException {
            if (st == null) {
                st = new StringTokenizer(br.readLine());
            } else if (st.hasMoreElements()) {
                String nextString = br.readLine();
                if (nextString == null) {
                    return false;
                }
                st = new StringTokenizer(nextString);
            }
            return st.hasMoreElements();
        }
    }

    public class FastWriter implements Closeable {

        private BufferedWriter bw;
        private StringBuilder sb = new StringBuilder();

        public FastWriter() {
            bw = new BufferedWriter(
                    new OutputStreamWriter(System.out));
        }

        public FastWriter(String fileName) throws IOException {
            bw = Files.newBufferedWriter(Paths.get(fileName));
        }

        public void println(char ch) {
            sb.append(ch).append('\n');
        }

        public void println(int i) {
            sb.append(i).append('\n');
        }

        public void println(long l) {
            sb.append(l).append('\n');
        }

        public void println(String s) {
            sb.append(s).append('\n');
        }

        public void println() {
            sb.append('\n');
        }

        public void println(double d) {
            sb.append(d).append('\n');
        }

        public void println(char[] chArr) {
            sb.append(chArr).append('\n');
        }

        public void print(char ch) {
            sb.append(ch).append(' ');
        }

        public void print(String s) {
            sb.append(s).append(' ');
        }

        public void print(int i) {
            sb.append(i).append(' ');
        }

        public void print(double d) {
            sb.append(d).append(' ');
        }

        public void close() throws IOException {
            bw.write(sb.toString());
            bw.close();
        }
    }
}
