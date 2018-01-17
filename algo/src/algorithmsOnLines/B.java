package algorithmsOnLines;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Telnov Sergey on 04.12.2017.
 */

public class B {
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

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        String s = in.next();
        int[] prefixFunction = new int[s.length()];
        StringBuilder stringBuilder = new StringBuilder(0 + " ");
        for (int i = 1; i != s.length(); i++) {
            int k = prefixFunction[i - 1];
            while (k > 0 && s.charAt(i) != s.charAt(k)) {
                k = prefixFunction[k - 1];
            }
            prefixFunction[i] = s.charAt(i) == s.charAt(k) ? k + 1 : k;
            stringBuilder.append(prefixFunction[i]).append(" ");
        }
        System.out.print(stringBuilder.toString());
    }
}
