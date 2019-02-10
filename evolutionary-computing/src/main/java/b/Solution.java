package b;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class Solution {

    private int len;
    private char[][] A;
    private char[] pattern;

    private void scanInput() throws IOException {
        FastReader reader = new FastReader("crossover.in");

        int n = reader.nextInt();
        len = reader.nextInt();

        A = new char[n][];

        for (int i = 0; i != n; i++) {
            String s = reader.next();
            A[i] = s.toCharArray();
        }

        pattern = reader.next().toCharArray();
    }

    private boolean singlePointCrossover() {
        int maxPrefix = 0;
        int maxSuffix = 0;

        for (int i = 0; i != A.length; i++) {
            int pIdx = 0;
            while (pIdx < len && A[i][pIdx] == '0') {
                pIdx++;
            }

            if (maxPrefix < pIdx)
                maxPrefix = pIdx;

            int sIdx = len - 1;
            while (sIdx >= 0 && A[i][sIdx] == '0') {
                sIdx--;
            }

            sIdx = len - sIdx - 1;
            if (maxSuffix < sIdx)
                maxSuffix = sIdx;
        }

        return maxPrefix + maxSuffix >= len;
    }

    private boolean twoPointCrossover() {
        int[] b = new int[len];

        for (char[] arr : A) {
            int idx = 0;
            int startIdx = 0;
            while (idx < len) {
                while (idx < len && arr[idx] == '0') {
                    idx++;
                }

                for (int j = startIdx; j != idx; j++) {
                    b[j] = Math.max(b[j], idx - j);
                }

                idx++;
                startIdx = idx;
            }
        }

        for (int i = 0; i != A.length; i++) {
            int pIdx = 0;
            while (pIdx < len && A[i][pIdx] == '0') {
                pIdx++;
            }

            int sIdx = len - 1;
            while (sIdx >= 0 && A[i][sIdx] == '0') {
                sIdx--;
            }

            sIdx = len - sIdx - 1;

            if (pIdx == len || pIdx + b[pIdx] + sIdx >= len)
                return true;
        }

        return false;
    }

    private boolean homogeneousCrossover() {
        if (A.length == 1) {
            for (int i = 0; i != len; i++) {
                if (A[0][i] == '1')
                    return false;
            }
            return true;
        }
        int counter = 0;
        for (int i = 0; i != A.length; i++) {
            for (int j = 0; j != A.length; j++) {
                if (counter >= 100_000) {
                    break;
                }

                if (i != j && andIsZero(A[i], A[j]))
                    return true;

                counter++;
            }
        }

        return false;
    }

    private boolean andIsZero(char[] a, char[] b) {
        for (int i = 0; i != len; i++) {
            if (a[i] == '1' && b[i] == '1')
                return false;
        }
        return true;
    }

    public void run() throws IOException {
        scanInput();

        for (int i = 0; i != A.length; i++) {
            for (int j = 0; j != len; j++) {
                boolean a = A[i][j] == '1';
                boolean p = pattern[j] == '1';
                A[i][j] = a ^ p ? '1' : '0';
            }
        }

        FastWriter writer = new FastWriter("crossover.out");

        writer.println(singlePointCrossover() ? "YES" : "NO");
        writer.println(twoPointCrossover() ? "YES" : "NO");
        writer.println(homogeneousCrossover() ? "YES" : "NO");

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
