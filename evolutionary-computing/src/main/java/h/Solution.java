package h;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    private int n;
    private FastReader reader;

    private double consumer(final double x, final int idx) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i != idx; i++) {
            builder.append("0 ");
        }

        builder.append(x).append(" ");

        for (int i = idx + 1; i < n; i++) {
            builder.append("0 ");
        }

        System.out.println(builder.toString());
        return reader.nextDouble();
    }

    private double ternarySearchMin(double left, double right, final int idx) throws IOException {
        final double eps = 0.0001;

        while (right - left > eps) {
            double a = (left * 2.0 + right) / 3.0;
            double b = (left + right * 2.0) / 3.0;

            if (consumer(a, idx) < consumer(b, idx)) {
                right = b;
            } else {
                left = a;
            }
        }

        return (left + right) / 2.0;
    }

    public void run() throws IOException {
        reader = new FastReader();
        n = reader.nextInt();

        double[] xs = new double[n];

        final double ADD_VALUE = 1.0 / 20.0;
        for (int i = 0; i != n; i++) {
            double curX = 0.0;
            double minVal = Double.MAX_VALUE;

            while (curX < 1.0) {
                double x = ternarySearchMin(curX, curX + ADD_VALUE, i);
                double xVal = consumer(x, i);

                if (minVal > xVal) {
                    minVal = xVal;
                    xs[i] = x;
                }

                curX += ADD_VALUE;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i != n; i++) {
            builder.append(xs[i]).append(' ');
        }

        System.out.println(builder.toString());
        final double ans = reader.nextDouble();
        System.out.println("minimum " + ans);
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
}
