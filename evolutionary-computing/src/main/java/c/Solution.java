package c;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Solution {

    private Node[] graph;
    private List<Node> newGraph;
    private boolean[] isReachable;
    private Map<Integer, Boolean> isMarked;

    private void dfs(int cur) {
        if (graph[cur].isChoice()) {
            Node c = graph[cur];
            if (isMarked.containsKey(c.p)) {
                if (isMarked.get(c.p) == Boolean.FALSE) {
                    dfs(c.f);
                } else {
                    dfs(c.t);
                }
            } else {
                isReachable[cur] = true;
                isMarked.put(c.p, Boolean.FALSE);
                dfs(c.f);
                isMarked.put(c.p, Boolean.TRUE);
                dfs(c.t);
                isMarked.remove(c.p);
            }
        } else {
            isReachable[cur] = true;
        }
    }

    private void setNewGraph(int cur, int parent, boolean isLeft) {
        Node curNode = graph[cur];

        if (isReachable[cur]) {
            final int curIndex = newGraph.size();
            newGraph.add(new Node(curNode.p));

            if (parent != -1) {
                Node pNode = newGraph.get(parent);

                if (isLeft) {
                    pNode.f = curIndex;
                } else {
                    pNode.t = curIndex;
                }
            }

            if (curNode.isChoice()) {
                setNewGraph(curNode.f, curIndex, true);
                setNewGraph(curNode.t, curIndex, false);
            }
        } else if (curNode.isChoice()) {
            setNewGraph(curNode.f, parent, isLeft);
            setNewGraph(curNode.t, parent, isLeft);
        }
    }

    public void run() throws IOException {
        FastReader reader = new FastReader("trees.in");

        final int n = reader.nextInt();
        graph = new Node[n];

        for (int i = 0; i != n; i++) {
            String command = reader.next();
            if (command.startsWith("leaf")) {
                graph[i] = new Node(reader.nextInteger());
            } else {
                Integer p = reader.nextInteger();
                int f = reader.nextVertex();
                int t = reader.nextVertex();

                graph[i] = new Node(p, f, t);
            }
        }

        isMarked = new HashMap<>(n);
        newGraph = new ArrayList<>();
        isReachable = new boolean[n];

        dfs(0);
        setNewGraph(0, -1, false);

        FastWriter writer = new FastWriter("trees.out");

        writer.println(newGraph.size());
        for (Node curNode : newGraph) {
            if (curNode.isChoice()) {
                writer.print("choice");
                writer.print(curNode.p);
                writer.print(curNode.f + 1);
                writer.println(curNode.t + 1);
            } else {
                writer.print("leaf");
                writer.println(curNode.p);
            }
        }
        writer.close();
    }

    private class Node {

        Integer p;
        int f, t;

        Node(Integer v) {
            this(v, Integer.MIN_VALUE, Integer.MIN_VALUE);
        }

        Node(Integer v, int f, int t) {
            this.p = v;
            this.f = f;
            this.t = t;
        }

        boolean isChoice() {
            return f != Integer.MIN_VALUE && t != Integer.MIN_VALUE;
        }
    }

    public static void main(String[] args) throws IOException {
        new Solution().run();
    }

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

        public Integer nextInteger() throws IOException {
            return Integer.valueOf(next());
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

    public class FastWriter implements Closeable {

        private BufferedWriter bw;
        private StringBuilder sb = new StringBuilder();

        public FastWriter() {
            bw = new BufferedWriter(
                    new OutputStreamWriter(System.out));
        }

        public FastWriter(String fileName) throws IOException {
            bw = Files.newBufferedWriter(Paths.get(fileName));
        }

        public void println(int i) {
            sb.append(i).append('\n');
        }

        public void println(long l) {
            sb.append(l).append('\n');
        }

        public void println(String s) {
            sb.append(s).append('\n');
        }

        public void println() {
            sb.append('\n');
        }

        public void println(double d) {
            sb.append(d).append('\n');
        }

        public void println(char[] chArr) {
            sb.append(chArr).append('\n');
        }

        public void print(char ch) {
            sb.append(ch);
        }

        public void print(String s) {
            sb.append(s).append(' ');
        }

        public void print(int i) {
            sb.append(i).append(' ');
        }

        public void print(double d) {
            sb.append(d).append(' ');
        }

        public void close() throws IOException {
            bw.write(sb.toString());
            bw.close();
        }
    }
}
