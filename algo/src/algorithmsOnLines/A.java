package algorithmsOnLines;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Telnov Sergey on 04.12.2017.
 */

public class A {
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

    private class StringComparator {
        public StringComparator(String s) {
            length = s.length();
            pow = new long[length];
            pow[0] = 1;

            hash = new long[length];
            hash[0] = s.charAt(0) - 'a' + 1;

            for (int i = 1; i != length; i++) {
                pow[i] = pow[i - 1] * P;
                hash[i] = (s.charAt(i) - 'a' + 1) * pow[i] + hash[i - 1];
            }
        }

        private long getHash(int start, int end) {
            if (start == 0) {
                return hash[end];
            } else {
                return hash[end] - hash[start - 1];
            }
        }

        public boolean isSameString(final int startA, final int endA, final int startB, final int endB) {
            if (endA - startA != endB - startB) {
                return false;
            } else {
                return getHash(startA, endA) * pow[startB] ==
                        getHash(startB, endB) * pow[startA];
            }
        }

        final int P = 31;
        final int length;
        long[] pow;
        long[] hash;
    }


    public void run() throws IOException {
        FastReader in = new FastReader();
        StringComparator comparator = new StringComparator(in.next());
        final int n = in.nextInt();
        for (int i = 0; i != n; i++) {
            if (comparator.isSameString(
                    in.nextIndex(), in.nextIndex(),
                    in.nextIndex(), in.nextIndex())) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new A().run();
    }
}
