package TreeDecomposition;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by @author Telnov Sergey on 11.11.2017.
 */

public class F {
    protected static class FastReader {
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

    private class CentroidDecomposition {
        public CentroidDecomposition(List<Integer>[] graph) {
            final int n = graph.length;
            this.graph = graph;
            depths = new int[n];
            parents = new int[n];
            Arrays.fill(depths, -1);

            isTagged = new boolean[n];
            distTagged = new long[n];
            distNotTagged = new long[n];
            numberOfTaggedVertex = new int[n];
            numberOfNotTagged = new int[n];
            contributionTagged = new long[n];
            contributionNotTagged = new long[n];
            dist = new int[18][n];
            levelKeys = new int[n];

            build(0, n, -1);
        }

        public void changeColor(final int u) {
            decreaseDist(u, isTagged[u]);
            isTagged[u] = !isTagged[u];
        }

        private void decreaseDist(final int startVertex, boolean changeTagged) {
            int add = changeTagged ? 1 : -1;

            numberOfNotTagged[startVertex] += add;
            numberOfTaggedVertex[startVertex] -= add;

            if (parents[startVertex] != -1) {
                contributionNotTagged[startVertex] += (long) dist[levelKeys[parents[startVertex]]][startVertex] * add;
                contributionTagged[startVertex] -= (long) dist[levelKeys[parents[startVertex]]][startVertex] * add;
            }

            int curr = parents[startVertex];

            while (curr != -1) {
                if (parents[curr] != -1) {
                    contributionNotTagged[curr] += (long) dist[levelKeys[parents[curr]]][startVertex] * add;
                    contributionTagged[curr] -= (long) dist[levelKeys[parents[curr]]][startVertex] * add;
                }

                distNotTagged[curr] += (long) dist[levelKeys[curr]][startVertex] * add;
                distTagged[curr] -= (long) dist[levelKeys[curr]][startVertex] * add;

                numberOfNotTagged[curr] += add;
                numberOfTaggedVertex[curr] -= add;

                curr = parents[curr];
            }
        }

        private long getVertexDist(final int u, boolean isTagged) {
            return isTagged ? distTagged[u] : distNotTagged[u];
        }

        private long getContribution(final int u, boolean isTagged) {
            return isTagged ? contributionTagged[u] : contributionNotTagged[u];
        }

        private long getVertexSize(final int u, boolean isTagged) {
            return isTagged ? numberOfTaggedVertex[u] : numberOfNotTagged[u];
        }

        public long getDist(int startVertex) {
            final boolean isTagged = this.isTagged[startVertex];
            long result = getVertexDist(startVertex, isTagged);
            int curr = startVertex;
            while (curr != -1) {
                result -= getContribution(curr, isTagged);
                if (parents[curr] != -1) {
                    result += (getVertexDist(parents[curr], isTagged) +
                            ((getVertexSize(parents[curr], isTagged) - getVertexSize(curr, isTagged)) *
                                    (long) (dist[levelKeys[parents[curr]]][startVertex])));
                }
                curr = parents[curr];
            }
            return result;
        }

        private void dfs(int curr, int prev, int depth, final int centroid) {
            depth++;
            for (int next: graph[curr]) {
                if (next != prev && (depths[next] == -1 || (depths[curr] != -1 && depths[next] > depths[curr]))) {
                    dist[levelKeys[centroid]][next] = depth;
                    if (parents[centroid] != -1) {
                        contributionNotTagged[centroid] += dist[levelKeys[parents[centroid]]][next];
                    }
                    distNotTagged[centroid] += depth;
                    dfs(next, curr, depth, centroid);
                }
            }
        }

        private void build(int curr, int size, int prev) {
            AtomicInteger atomicCenter = new AtomicInteger(-1);
            int centroidSize = getCenter(curr, size, atomicCenter, -1);
            int center = atomicCenter.get();
            parents[center] = prev;
            if (prev == -1) {
                depths[center] = 0;
            } else {
                depths[center] = depths[prev] + 1;
                contributionNotTagged[center] = dist[levelKeys[prev]][center];
            }
            levelKeys[center] = depths[center];
            dfs(center, -1, 0, center);
            numberOfNotTagged[center] = centroidSize;
            for (final int next: graph[center]) {
                if (depths[next] == -1) {
                    build(next, size / 2, center);
                }
            }
        }

        private int getCenter(int curr, int size, AtomicInteger center, int parent) {
            int sum = 1;
            for (final int next: graph[curr]) {
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
        private List<Integer>[] graph;
        private boolean[] isTagged;
        private int[] numberOfNotTagged;
        private int[] numberOfTaggedVertex;
        private long[] distTagged;
        private long[] distNotTagged;
        private long[] contributionNotTagged;
        private long[] contributionTagged;
        private int[][] dist;
        private int[] levelKeys;
    }

    protected FastReader getScanner() {
        return new FastReader();
    }

    protected void getResult(final long res) {
        out.println(res);
    }

    private FastWriter out;

    public void run() throws IOException {
//        FastReader in = getScanner();
        FastReader in = new FastReader();
        out = new FastWriter();
        final int n = in.nextInt(),
                m = in.nextInt();
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i != n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i != n; i++) {
            final int u = in.nextVertex(),
                    v = in.nextVertex();
            graph[u].add(v);
            graph[v].add(u);
        }
        CentroidDecomposition cd = new CentroidDecomposition(graph);
        for (int i = 0; i != m; i++) {
            final int command = in.nextInt();
            final int u = in.nextVertex();
            if (command == 1) {
                cd.changeColor(u);
            } else {
                out.println(cd.getDist(u));
//                getResult(cd.getDist(u));
            }
        }
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new F().run();
    }

}
