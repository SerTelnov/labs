package algorithmsOnLines;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Telnov Sergey on 05.12.2017.
 */

public class K {
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

    private int[] zFunction;
    private ReverseString reverseString;

    class ReverseString {
        ReverseString(String s) {
            STRING_SIZE = s.length();
            string = s.toCharArray();
            int left = 0;
            int right = s.length() - 1;
            while (left < right) {
                char temp = string[left];
                string[left] = string[right];
                string[right] = temp;
                left++;
                right--;
            }
        }

        public int charAt(final int i) { return string[currLength + i]; }

        public void setCurrLength(final int length) { this.currLength = STRING_SIZE - length; }

        private int currLength = 0;
        private final int STRING_SIZE;
        private char[] string;
    }

    private int getMaxInZFunction(final int length) {
        reverseString.setCurrLength(length);
        int max = 0;
        int left = 0;
        int right = 0;
        for (int i = 1; i != length; i++) {
            zFunction[i] = Math.max(
                    0,
                    Math.min(
                            right - i,
                            zFunction[i - left]
                    )
            );
            while (i + zFunction[i] < length && reverseString.charAt(zFunction[i]) == reverseString.charAt(i + zFunction[i])) {
                zFunction[i]++;
            }
            if (i + zFunction[i] > right) {
                left = i;
                right = i + zFunction[i];
            }
            if (max < zFunction[i]) {
                max = zFunction[i];
            }
        }
        return max;
    }

    public void run() throws IOException {
        FastReader in = new FastReader("count.in");
//        FastReader in = new FastReader();
        String s = in.next();
        zFunction = new int[s.length()];

        reverseString = new ReverseString(s);

        long result = 0;
        for (int currLength = 1; currLength <= s.length(); currLength++) {
            result += currLength - getMaxInZFunction(currLength);
        }
        FastWriter out = new FastWriter("count.out");
        out.println(result);
        out.close();
//        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new K().run();
    }
}
