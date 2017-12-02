package planarity;

import java.io.*;
import java.util.*;

/**
 * Created by Telnov Sergey on 20.11.2017.
 */

public class Planarity {

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

        public void println(final int i) {
            sb.append(i).append("\n");
        }

        public void println(String s) {
            sb.append(s).append("\n");
        }

        public void println() {
            sb.append("\n");
        }

        public void println(final double d) {
            sb.append(d).append("\n");
        }

        public void print(String s) {
            sb.append(s).append(" ");
        }

        public void print(final int i) {
            sb.append(i).append(" ");
        }

        public void print(final double d) {
            sb.append(d).append(" ");
        }

        public void close() throws IOException {
            bw.write(sb.toString());
            bw.flush();
            bw.close();
        }

        private BufferedWriter bw;
        private StringBuilder  sb;
    }

    private Color[] colors;
    private int[] indexes;
    private List<Integer>[] edgeGraph;
    private int startEdge = -1;
    private Graph graph;

    private class Graph {

        public Graph(final int numberOfVertexes, final int n) {
            this.numberOfVertexes = numberOfVertexes;
            this.size = n;
            to = new int[n];
            from = new int[n];
        }

        public void add(final int from, final int to, final int index) {
            this.from[index] = from;
            this.to[index] = to;
        }

        public void sort(int[] positions) {
            for (int i = 0; i != size; i++) {
                if (positions[from[i]] > positions[to[i]]) {
                    final int temp = to[i];
                    to[i] = from[i];
                    from[i] = temp;
                }
            }
        }

        public int[] to, from;
        private final int size;
        private final int numberOfVertexes;
    }

    private enum Color {
        NO_COLOR,
        WHITE,
        BLACK
    }

    private boolean isBipartiteGraph(int curr) {
        for (int next: edgeGraph[curr]) {
            if (colors[next].equals(Color.NO_COLOR)) {
                if (colors[curr].equals(Color.BLACK)) {
                    colors[next] = Color.WHITE;
                } else {
                    colors[next] = Color.BLACK;
                }
                if (!isBipartiteGraph(next)) {
                    return false;
                }
            } else if (colors[next].equals(colors[curr])) {
                return false;
            }
        }
        return true;
    }

    private void buildEdgeGraph() {
        edgeGraph = new ArrayList[graph.size];
        for (int i = 0; i != graph.size; i++) {
            final int a1 = indexes[graph.from[i]],
                    b1 = indexes[graph.to[i]];
            for (int j = 0; j != graph.size; j++) {
                final int a2 = indexes[graph.from[j]],
                        b2 = indexes[graph.to[j]];
                if (a1 < a2 && a2 < b1 && b1 < b2) {
                    if (startEdge == -1) {
                        startEdge = i;
                    }
                    if (edgeGraph[i] == null) {
                        edgeGraph[i] = new ArrayList<>();
                    }
                    edgeGraph[i].add(j);
                    if (edgeGraph[j] == null) {
                        edgeGraph[j] = new ArrayList<>();
                    }
                    edgeGraph[j].add(i);
                }
            }
        }
    }

    private void scan() throws IOException {
        FastReader in = new FastReader();
        final int n = in.nextInt(),
                m = in.nextInt();
        graph = new Graph(n, m);
        colors = new Color[m];
        Arrays.fill(colors, Color.NO_COLOR);
        for (int i = 0; i != m; i++) {
            graph.add(in.nextVertex(), in.nextVertex(), i);
        }
        indexes = new int[n];
        for (int i = 0; i != n; i++) {
            indexes[in.nextVertex()] = i;
        }
        graph.sort(indexes);
    }

    public void run() throws IOException {
        scan();
        buildEdgeGraph();

        FastWriter out = new FastWriter();
        if (startEdge == -1 || isBipartiteGraph(startEdge)) {
            out.println("YES");
            for (int i = 0; i != graph.numberOfVertexes; i++) {
                out.print(indexes[i]);
                out.print(0);
            }

            out.println();

            for (int i = 0; i != graph.size; i++) {
                out.print(
                        (indexes[graph.from[i]] + indexes[graph.to[i]]) / 2.0
                );
                int index = Math.abs(indexes[graph.from[i]] - indexes[graph.to[i]]);
                if (colors[i].equals(Color.WHITE)) {
                    out.println(index / -2.0);
                } else {
                    out.println(index / 2.0);
                }
            }
        } else {
            out.println("NO");
        }
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Planarity().run();
    }
}
