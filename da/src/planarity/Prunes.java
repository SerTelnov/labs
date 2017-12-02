package planarity;

import java.io.*;
import java.util.*;

/**
 * Created by Telnov Sergey on 28.11.2017.
 */
public class Prunes {
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

    private int start, end;
    private Point[] keys;
    private HashMap<Point, Integer> indexesPoint;
    private List<Integer>[] graph;
    private HashMap<Edge, Integer> edges;
    private Set<Integer>[] facets;
    private int numberOfFacet = 0;
    private int startVertex = -1;
    private int endVertex = -1;
    private List<Integer>[] dualGraph;


    private class Point {

        public Point(final double x, final double y) {
            this.x = x == -.0 ? .0 : x;
            this.y = y == -.0 ? .0 : y;
        }

        public double getFi(final double dx, final double dy) {
            double fi = Math.atan2(dx - x, dy - y);
            return fi < 0 ? fi + 2 * Math.PI : fi;
        }

        public int compareTo(Point p, Point centerPoint) {
            Double fi = this.getFi(centerPoint.x, centerPoint.y);
            Double fip = p.getFi(centerPoint.x, centerPoint.y);
            return fi.compareTo(fip);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            } else {
                Point p = (Point) obj;
                return this.x == p.x && this.y == p.y;
            }
        }

        public final double x, y;
    }

    private class Edge {
        public Edge(final int prev, final int next) {
            this.prev = prev;
            this.next = next;
        }

        private final int prev, next;
    }

    private void setFacets() {
        boolean[][] used = new boolean[graph.length][graph.length];
        for (int i = 0; i != graph.length; i++) {
            for (int j = 0; j != graph[i].size(); j++) {
                if (!used[i][j]) {
                    used[i][j] = true;
                    int v = graph[i].get(j);
                    int pv = i;
                    while(true) {
                        Point currPoint = keys[v];
                        facets[numberOfFacet].add(v);
                        int index = Collections.binarySearch(graph[v], pv,
                                        (Integer a, Integer b) -> keys[a].compareTo(keys[b], currPoint)
                                ) + 1;
                        if (index == graph[v].size()) {
                            index = 0;
                        }
                        if (used[v][index]) {
                            break;
                        }
                        used[v][index] = true;
                        pv = v;
                        v = graph[v].get(index);
                    }
                    if (startVertex == -1 && facets[numberOfFacet].contains(start) && facets[numberOfFacet].contains(end)) {
                        Set<Integer> currFacets = facets[numberOfFacet];
                        Set<Integer> subFacets = new HashSet<>();
                        boolean isStartEndSequence = false;
                        for (Integer curr : currFacets) {
                            if (curr.equals(end)) {
                                isStartEndSequence = false;
                                subFacets.add(curr);
                            }
                            if (isStartEndSequence) {
                                currFacets.remove(curr);
                                subFacets.add(curr);
                            }
                            if (curr.equals(start)) {
                                isStartEndSequence = true;
                                subFacets.add(curr);
                            }
                        }
                        startVertex = numberOfFacet;
                        facets[++numberOfFacet] = subFacets;
                        endVertex = numberOfFacet;
                    }
                    numberOfFacet++;
                }
            }
        }
    }

    private void buildDualGraph() {
        dualGraph = new List[numberOfFacet];
        for (int i = 0; i != numberOfFacet; i++) {
            dualGraph[i] = new ArrayList();
        }
        for (int i = 0; i != numberOfFacet; i++) {
            for (Integer curr: facets[i]) {
                for (int j = 0; j != numberOfFacet; j++) {
                    if (i != j && facets[j].contains(curr)) {
                        dualGraph[i].add(j);
                        dualGraph[j].add(i);
                    }
                }
            }
        }
    }

    private void scan() throws IOException {
        FastReader in = new FastReader();
        final int n = in.nextInt(),
                m = in.nextInt();
        start = in.nextInt();
        end = in.nextInt();

        keys = new Point[n];
        indexesPoint = new HashMap<>(n);
        graph = new ArrayList[n];
        facets = new HashSet[n];
        edges = new HashMap<>(m);

        for (int i = 0; i != n; i++) {
            Point curr = new Point(
                    in.nextInt(),
                    in.nextInt()
            );
            keys[i] = curr;
            indexesPoint.put(curr, i);
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i != m; i++) {
            final int u = in.nextVertex(),
                    v = in.nextVertex();
            final int value = in.nextInt();
            graph[u].add(v);
            graph[v].add(u);
            edges.put(new Edge(u, v), value);
            edges.put(new Edge(v, u), value);
        }
    }

    public void run() throws IOException {
        scan();

        for (int i = 0; i != graph.length; i++) {
            Point currPoint = keys[i];
            graph[i].sort((Integer a, Integer b) -> keys[a].compareTo(keys[b], currPoint));
        }

        setFacets();

        buildDualGraph();

    }

    public static void main(String[] args) throws IOException {
        new Prunes().run();
    }
}
