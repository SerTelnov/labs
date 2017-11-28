package TreeDecomposition;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by @author Telnov Sergey on 11.11.2017.
 */

public class E {

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

        public int nextVertex() throws IOException {
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

    private class CentroidDecomposition {
        public CentroidDecomposition(List<List<Integer>> graph) {
            final int n = graph.size();
            this.graph = graph;
            depths = new int[n];
            parents = new int[n];
            Arrays.fill(depths, -1);

            build(0, n, -1);
        }

        public void printPath() {
            for (int curr: parents) {
                out.print(curr + 1);
            }
        }

        private void build(int curr, int size, int prev) {
            AtomicInteger atomicCenter = new AtomicInteger(-1);
            getCenter(curr, size, atomicCenter, -1);
            int center = atomicCenter.get();
            parents[center] = prev;
            if (prev == -1) {
                depths[center] = 0;
            } else {
                depths[center] = depths[prev] + 1;
            }
            for (final int next: graph.get(center)) {
                if (depths[next] == -1) {
                    build(next, size / 2, center);
                }
            }
        }

        private int getCenter(int curr, int size, AtomicInteger center, int parent) {
            int sum = 1;
            for (final int next: graph.get(curr)) {
                if (depths[next] == -1 && next != parent) {
                    sum += getCenter(next, size, center, curr);
                }
            }
            if (center.get() == -1 && (size <= 2 * sum || parent == -1)) {
                center.set(curr);
            }
            return sum;
        }

        private int[] depths;
        private int[] parents;
        private List<List<Integer>> graph;
    }

    private FastWriter out;

    public void run() throws IOException {
        FastReader in = new FastReader();
        out = new FastWriter();
        final int n = in.nextInt();
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i != n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 1; i != n; i++) {
            final int u = in.nextVertex();
            final int v = in.nextVertex();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        CentroidDecomposition cd = new CentroidDecomposition(graph);
        cd.printPath();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new E().run();
    }
}
