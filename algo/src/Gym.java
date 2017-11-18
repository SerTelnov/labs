import javafx.util.Pair;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by @author Telnov Sergey on 24.10.2017.
 */

public class Gym {

    private class ProblemFSolver {
        public ProblemFSolver(Map<Integer, HashSet<Integer>> edges) {
            final int n = edges.size();
            this.tree = new Vertex[n];
            for (int i = 0; i != n; i++) {
                tree[i] = new Vertex(i);
            }
            edges.forEach((u, v)-> {
                for (Integer curr : v) {
                    tree[u].addChild(tree[curr]);
                }
            });
            this.used = new boolean[n];
        }

        public void changeColor(final int v) {
            tree[v].changeColor();
        }

        public int getDist(final int v) {
            Arrays.fill(used, false);
            return dfs(tree[v], 0, tree[v].color);
        }

        private boolean[] used;
        private int dfs(Vertex curr, int dist, final boolean color) {
            used[curr.name] = true;
            int currResult = 0;
            if (curr.color == color) {
                currResult += dist;
            }
            dist++;
            for (Vertex next: curr.neighbors) {
                if (!used[next.name]) {
                    currResult += dfs(next, dist, color);
                }
            }
            return currResult;
        }

        private Vertex[] tree;
        private class Vertex {
            public Vertex(final int name) {
                this.name = name;
                this.color = false;
                this.neighbors = new ArrayList<>();
            }

            public void changeColor() {
                color = !color;
            }

            public void addChild(Vertex v) {
                neighbors.add(v);
            }

            public final int name;
            //               0 - black, 1 - white
            public boolean color;
            public List<Vertex> neighbors;
        }
    }
    public void run() throws IOException {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        final int n = in.nextInt();
        final int m = in.nextInt();
        HashMap<Integer, HashSet<Integer>> edges = new HashMap<>(n);
        for (int i = 1; i != n; i++) {
            final int v = in.nextInt() - 1,
                    u = in.nextInt() - 1;
            if (!edges.containsKey(v)) {
                edges.put(v, new HashSet<>());
            }
            edges.get(v).add(u);
            if (!edges.containsKey(u)) {
                edges.put(u, new HashSet<>());
            }
            edges.get(u).add(v);
        }
        ProblemFSolver tree = new ProblemFSolver(edges);
        for (int i = 0; i != m; i++) {
            final int command = in.nextInt();
            if (command == 1) {
                tree.changeColor(in.nextInt() - 1);
            } else {
                out.println(tree.getDist(in.nextInt() - 1));
            }
        }
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Gym().run();
    }
}
