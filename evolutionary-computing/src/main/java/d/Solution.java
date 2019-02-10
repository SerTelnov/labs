package d;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Solution {

    public void run() throws IOException {
        FastReader reader = new FastReader("start.in");
        int m = reader.nextInt();
        int n = reader.nextInt();

        int[] t = new int[n];
        int[] f = new int[n];
        char[] out = new char[n];

        for (int i = 0; i != n; i++) {
            f[i] = reader.nextVertex();
            t[i] = reader.nextVertex();
            out[i] = reader.next().charAt(0);
        }

        String z = reader.next();
        boolean[][] dp = new boolean[n][2];

        for (int i = 0; i != n; i++) {
            dp[i][0] = true;
        }

        int curInd = 1;
        int prevInd = 0;

        for (int i = m - 1; i >= 0; i--) {
            final char currCh = z.charAt(i);
            for (int j = 0; j != n; j++) {
                dp[j][curInd] = (dp[f[j]][prevInd] && out[f[j]] == currCh) ||
                        (dp[t[j]][prevInd] && out[t[j]] == currCh);
            }

            curInd = (curInd + 1) % 2;
            prevInd = (prevInd + 1) % 2;
        }

        int counter = 0;
        for (int i = 0; i != n; i++) {
            if (dp[i][prevInd])
                counter++;
        }

        FastWriter writer = new FastWriter("start.out");
        writer.print(counter);

        for (int i = 0; i != n; i++) {
            if (dp[i][prevInd])
                writer.print(i + 1);
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
            sb.append(ch);
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
