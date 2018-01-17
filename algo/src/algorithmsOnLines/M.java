package algorithmsOnLines;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Telnov Sergey on 29.12.2017.
 */
public class M {
    private int[] zFunction;
    String line;
    int lineLength;

    private void buildZFunction(String s) {
        zFunction = new int[s.length()];
        int left = 0;
        int right = 0;
        for (int i = 1; i != s.length(); i++) {
            zFunction[i] = Math.max(
                    0,
                    Math.min(
                            right - i,
                            zFunction[i - left]
                    )
            );
            while (i + zFunction[i] < s.length() && s.charAt(zFunction[i]) == s.charAt(i + zFunction[i])) {
                zFunction[i]++;
            }
            if (i + zFunction[i] > right) {
                left = i;
                right = i + zFunction[i];
            }
        }
    }

    int getShiftIndex() {
        int index = 0;
        for (int i = 0; i != lineLength; i++) {
            if (line.charAt(zFunction[i]) > line.charAt(zFunction[i] % lineLength + i)) {
                index++;
            }
        }
        return index;
    }

    public void run() throws IOException {
        FastReader in = new FastReader("cyclic.in");

        line = in.next();

        lineLength = line.length();

        line += line;

        buildZFunction(line);

        FastWriter out = new FastWriter("cyclic.out");
        out.println(getShiftIndex() + 1);

        out.close();
    }

    public static void main(String[] args) throws IOException {
        new M().run();
    }
    private static class FastReader {
        BufferedReader br;
        StringTokenizer st;

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

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public int nextIndex() throws IOException {
            return nextInt() - 1;
        }

        public boolean hasNext() throws IOException {
            if (st == null) {
                st = new StringTokenizer(br.readLine());
            } else if (!st.hasMoreElements()) {
                String nextString = br.readLine();
                if (nextString == null) {
                    return false;
                }
                st = new StringTokenizer(nextString);
            }
            return st.hasMoreElements();
        }
    }
    private static class FastWriter {
        public FastWriter() {
            bw = new BufferedWriter(
                    new OutputStreamWriter(System.out)
            );
            sb = new StringBuilder();
        }

        public FastWriter(String fileName) throws IOException {
            bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName))
            );
            sb = new StringBuilder();
        }

        public void println(final int i) {
            sb.append(i).append("\n");
        }

        public void println(final long l) {
            sb.append(l).append("\n");
        }

        public void print(final int i) {
            sb.append(i).append(" ");
        }

        public void close() throws IOException {
            bw.write(sb.toString());
            bw.flush();
            bw.close();
        }

        private BufferedWriter bw;
        private StringBuilder  sb;
    }
}
