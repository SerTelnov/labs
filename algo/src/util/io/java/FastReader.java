package util.io.java;

import java.io.*;
import java.util.StringTokenizer;

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
