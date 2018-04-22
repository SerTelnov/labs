package algorithmsOnLines;

import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Created by Telnov Sergey on 05.12.2017.
 */

public class J {
    private class SuffixArray {

        /*
        bool sufCmp(int i, int j)
	{
		if (pos[i] != pos[j])
			return pos[i] < pos[j];
		i += gap;
		j += gap;
		return (i < N && j < N) ? pos[i] < pos[j] : i > j;
	}

	void buildSA()
	{
		N = strlen(S);
		REP(i, N) sa[i] = i, pos[i] = S[i];
		for (gap = 1;; gap *= 2)
		{
			sort(sa, sa + N, sufCmp);
			REP(i, N - 1) tmp[i + 1] = tmp[i] + sufCmp(sa[i], sa[i + 1]);
			REP(i, N) pos[sa[i]] = tmp[i];
			if (tmp[N - 1] == N - 1) break;
		}
	}
         */

        public SuffixArray(String s) {
            length = s.length();
            data = s.toCharArray();

            for (int i = 0; i != length; i++) {
                suffixes[i] = i;
            }
            for (int level = 1;; level *= 2) {
                final int currLevel = level;

            }
        }

        public void solve() {
            lcp = new int[length];
            int[] rp = new int[length];
            for (int i = 0; i != length; i++) {
                rp[suffixes[i]] = i;
            }
            int counter = 0;
            for (int i: rp) {
                if (i != 0) {
                    counter = Math.max(counter - 1, 0);
                    while (data[suffixes[i] + counter] == data[suffixes[i - 1] + counter])
                        counter++;

                    lcp[i] = counter;
                }
            }
        }

        public void printSuffixes() {
            for (int i = 0; i != length; i++) {
                for (int j = suffixes[i]; j != length; j++) {
                    System.out.print(data[j]);
                }
                System.out.println();
            }
        }

        private char[] data;
        private int[] suffixes;
        private final int SIZE_ALPHABET = 29;
        private final int length;
        private int[] lcp;
    }

    public void run() throws IOException {
//        FastReader in = new FastReader("array.in");
        FastReader in = new FastReader();

        String s = in.next() + "\0";

        SuffixArray sa = new SuffixArray(s);

        FastWriter out = new FastWriter();

        sa.printSuffixes();

        for (int suffix: sa.suffixes) {
            out.print(suffix + 1);
        }
        out.println();
        sa.solve();
        for (int i = 1; i != sa.length; i++) {
            out.print(sa.lcp[i]);
        }
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new J().run();
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

        public void println() { sb.append("\n"); }

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
