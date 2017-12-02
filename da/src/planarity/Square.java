package planarity;

import java.io.*;
import java.util.*;

/**
 * Created by Telnov Sergey on 21.11.2017.
 */

public class Square {
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

    private class Point {

        public Point(final double x, final double y) {
            this.x = x == -.0 ? .0 : x;
            this.y = y == -.0 ? .0 : y;
        }

        public double getFi(final double dx, final double dy) {
            double fi = Math.atan2(dy - y, dx - x);
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

    private class Line {
        public Line(final int x1, final int y1, final int x2, final int y2) {
            this.A = y1 - y2;
            this.B = x2 - x1;
            this.C = x1 * y2 - x2 * y1;

            this.points = new TreeSet<>(this::comparator);
        }

        public Point getIntersection(Line that) {
            final double c = this.A * that.B - that.A * this.B;
            if (c == 0) {
                return null;
            } else {
                return new Point(
                        - (this.C * that.B - that.C * this.B) / c,
                        - (this.A * that.C - that.A * this.C) / c
                );
            }
        }

        private int comparator(Point a, Point b) {
            if (a.x != b.x) {
                return Double.compare(a.x, b.x);
            } else {
                return Double.compare(a.y, b.y);
            }
        }

        public Point findPoint(Point that) {
            Iterator<Point> iterator = points.iterator();
            while(iterator.hasNext()) {
                Point curr = iterator.next();
                if(that.equals(curr)) {
                    return curr;
                }
            }
            return null;
        }

        public boolean addPoint(Point p) {
            return points.add(p);
        }

        public final double A, B, C;
        public SortedSet<Point> points;
    }

    private void countSquare(List<Integer> facet) {
        if (facet.isEmpty()) {
            return;
        }
        double result = 0.0;
        Point prev = keys[facet.get(facet.size() - 1)];
        for (int i = 0; i != facet.size(); i++) {
            Point curr = keys[facet.get(i)];
            result += (prev.x - curr.x) * (prev.y + curr.y);
            prev = curr;
        }
        this.result[resultSize++] = Math.abs(result / 2);
    }

    private void traversalOnFaces() {
        boolean[][] used = new boolean[graph.length][graph.length];
        List<Integer> facet = new ArrayList<>();
        for (int i = 0; i != graph.length; i++) {
            for (int j = 0; j != graph[i].size(); j++) {
                if (!used[i][j]) {
                    used[i][j] = true;
                    int v = graph[i].get(j);
                    int pv = i;
                    while(true) {
                        Point currPoint = keys[v];
                        facet.add(v);
                        int index = Collections
                                .binarySearch(graph[v], pv,
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
                    countSquare(facet);
                    facet.clear();
                }
            }
        }
    }

    private Line[] lines;
    private List<Integer>[] graph;
    private Point[] keys;
    private double[] result;
    private int resultSize = 0;
    private HashMap<Point, Integer> pointsIndex;

    private void scan() throws IOException {
        FastReader in = new FastReader();
        final int n = in.nextInt();

        lines = new Line[n];
        keys = new Point[n * n];
        pointsIndex = new HashMap<>(n * n);

        for (int i = 0; i != n; i++) {
            Line line = new Line(
                    in.nextInt(), in.nextInt(),
                    in.nextInt(), in.nextInt()
            );
            for (int j = 0; j != i; j++) {
                Point currIntersection = lines[j].getIntersection(line);
                if (currIntersection != null) {
                    if (lines[j].addPoint(currIntersection)) {
                        keys[pointsIndex.size()] = currIntersection;
                        pointsIndex.put(currIntersection, pointsIndex.size());
                        line.addPoint(currIntersection);
                    } else {
                        line.addPoint(lines[j].findPoint(currIntersection));
                    }
                }
            }
            lines[i] = line;
        }

        graph = new ArrayList[pointsIndex.size()];
        for (int i = 0; i != graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        result = new double[n * n];
    }

    public void run() throws IOException {
        scan();
        for (int i = 0; i != lines.length; i++) {
            if (lines[i].points.isEmpty())
                continue;
            Point prev = lines[i].points.first();
            for (Point curr: lines[i].points) {
                if (!prev.equals(curr)) {
                    graph[pointsIndex.get(prev)].add(pointsIndex.get(curr));
                    graph[pointsIndex.get(curr)].add(pointsIndex.get(prev));
                }
                prev = curr;
            }
        }
        for (int i = 0; i != graph.length; i++) {
            Point currPoint = keys[i];
            graph[i].sort((Integer a, Integer b) -> keys[a].compareTo(keys[b], currPoint));
        }

        traversalOnFaces();

        FastWriter out = new FastWriter();

        Arrays.sort(result, 0, resultSize);
        int startIndex = 0;
        while (startIndex < resultSize && result[startIndex] < 0.00_000_001) {
            startIndex++;
        }
        out.println(Math.max(0, --resultSize - startIndex));
        for (int i = startIndex; i < resultSize; i++) {
            out.println(result[i]);
        }
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Square().run();
    }
}
