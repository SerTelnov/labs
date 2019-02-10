package e;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class Solution {

    private int[] t;

    private double[] lenDivision;
    private char[][] out;

    private double[][] possibleOptions;

    private void setOptions(final int idx, char[] in) {
        int cur = 0;
        for (int i = 0; i != in.length; i++) {
            int eIdx = in[i] - '0';
            possibleOptions[cur * 2 + eIdx][out[idx][i] - 'a'] += lenDivision[idx];
            cur = t[cur * 2 + eIdx];
        }
    }

    public void run() throws IOException {
        FastReader reader = new FastReader("discrete.in");

        int n = reader.nextInt();
        int testCount = reader.nextInt();

        t = new int[2 * n];

        for (int i = 0; i != n; i++) {
            t[2 * i] = reader.nextVertex();
            t[2 * i + 1] = reader.nextVertex();
        }

        lenDivision = new double[testCount];
        out = new char[testCount][];
        possibleOptions = new double[2 * n][26];

        for (int i = 0; i != testCount; i++) {
            lenDivision[i] = 1.0 / reader.nextDouble();
            char[] in = reader.next().toCharArray();
            out[i] = reader.next().toCharArray();

            setOptions(i, in);
        }

        FastWriter writer = new FastWriter("discrete.out");

        for (int i = 0; i != n; i++) {
            writer.print((char) ('a' + getMaxIndex(2 * i)));
            writer.println((char) ('a' + getMaxIndex(2 * i + 1)));
        }

        writer.close();
    }

    private int getMaxIndex(int idx) {
        int index = -1;
        double maxIdx = -1;

        for (int j = 0; j != 26; j++) {
            if (maxIdx < possibleOptions[idx][j]) {
                maxIdx = possibleOptions[idx][j];
                index = j;
            }
        }

        return index;
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
