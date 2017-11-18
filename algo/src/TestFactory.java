import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static java.util.Collections.shuffle;

/**
 * Created by Telnov Sergey on 17.11.2017.
 */
public class TestFactory {

    private static class RandomTest {
        public RandomTest(final int n) {
            N = n;
        }

        public int nextVertex(final int prevVertex) {
            int value = random.nextInt();
            value = value < 0 ? -value : value;
            value %= N;
            if (value == prevVertex) {
                value = (value + 1) % N;
            }
            return value;
        }

        public boolean nextBoolean() {
            return random.nextBoolean();
        }

        public HashSet<Integer>[] getRandomTree() {
            final int n = N;
            List<Integer> vertexes = new ArrayList<>(n);
            List<Integer> indexes = new ArrayList<>(n);
            for (int i = 0; i != n; i++) {
                vertexes.add(i);
                indexes.add(i);
            }
            shuffle(vertexes);
            shuffle(indexes);
            HashSet<Integer>[] edges = new HashSet[n];
            for (int i = 0; i != n; i++) {
                edges[i] = new HashSet<>();
            }
            for (int i = 1; i != n; i++) {
                edges[vertexes.get(indexes.get(i - 1))].add(vertexes.get(indexes.get(i)));
                edges[vertexes.get(indexes.get(i))].add(vertexes.get(indexes.get(i - 1)));
            }
            return edges;
        }

        private final int N;
        private Random random = new Random();
    }

    private static class ProblemCTestCreator {

        private class ProblemCSolver {
            public ProblemCSolver(final int n) {
                graph = new ArrayList[n];
                for (int i = 0; i != n; i++) {
                    graph[i] = new ArrayList<>();
                }
                used = new boolean[n];
            }

            public void link(final int u, final int v) {
                graph[u].add(v);
                graph[v].add(u);
            }

            private boolean hasEdgs(int u, int v) {
                for (int curr : graph[u]) {
                    if (curr == v) {
                        return true;
                    }
                }
                return false;
            }

            public boolean cut(Integer u, Integer v) {
                if (!hasEdgs(u, v))
                    return false;
                graph[u].remove(v);
                graph[v].remove(u);
                return true;
            }

            private boolean dfs(final int curr, final int res) {
                if (curr == res) {
                    return true;
                }
                used[curr] = true;
                for (int next : graph[curr]) {
                    if (!used[next]) {
                        boolean isConnected = dfs(next, res);
                        if (isConnected) {
                            return true;
                        }
                    }
                }
                return false;
            }

            public boolean isConnected(final int u, final int v) {
                Arrays.fill(used, false);
                return dfs(u, v);
            }

            private List<Integer>[] graph;
            private boolean[] used;
        }

        public void createTest() throws IOException {
            Scanner scannerTestSize = new Scanner(System.in);
            System.out.println("print n and m values");
            final int n = scannerTestSize.nextInt();
            final int m = scannerTestSize.nextInt();
            PrintWriter testIn = new PrintWriter(new FileWriter("test.in"));
            PrintWriter testOut = new PrintWriter(new FileWriter("test.out"));
            RandomTest random = new RandomTest(n);
            testIn.println(n + " " + m);
            ProblemCSolver tree = new ProblemCSolver(n);
            for (int i = 0; i != m; i++) {
                final int u = random.nextVertex(-1);
                final int v = random.nextVertex(u);
                if (random.nextBoolean()) {
                    if (!tree.isConnected(u, v)) {
                        testIn.print("link");
                        tree.link(u, v);
                    } else {
                        if (!tree.cut(u, v)) {
                            testIn.print("connect");
                            if (tree.isConnected(u, v)) {
                                testOut.print(1);
                            } else {
                                testOut.print(0);
                            }
                            testOut.print(" ");
                        } else {
                            testIn.print("cut");
                        }
                    }
                } else {
                    testIn.print("connect");
                    if (tree.isConnected(u, v)) {
                        testOut.print(1);
                    } else {
                        testOut.print(0);
                    }
                    testOut.print(" ");
                }
                testIn.println(" " + (u + 1) + " " + (v + 1));
            }
            testIn.close();
            testOut.close();
            System.out.println("input in file 'test.in'\nanswers in file 'test.out'");
        }

    }

    private static class ProblemFTestCreator {

        private class ProblemFSolver {
            public ProblemFSolver(HashSet<Integer>[] edges) {
                final int n = edges.length;
                this.tree = new Vertex[n];
                for (int i = 0; i != n; i++) {
                    tree[i] = new Vertex(i);
                }
                for (int i = 0; i != edges.length; i++) {
                    for (Integer curr: edges[i]) {
                        tree[i].addChild(tree[curr]);
                    }
                }
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

        private void printTree(HashSet<Integer>[] edges) {
            for (int i = 0; i != edges.length; i++) {
                for (Integer curr: edges[i]) {
                    input.println((i + 1) + " " + (curr + 1));
                }
            }
        }

        public void createTest() throws IOException {
            System.out.println("write n and m values");
            input = new PrintWriter(new FileWriter("test.in"));
            PrintWriter output = new PrintWriter(new FileWriter("test.out"));
            Scanner scanner = new Scanner(System.in);
            final int n = scanner.nextInt(),
                    m = scanner.nextInt();
            RandomTest random = new RandomTest(n);
            input.println(n + " " + m);
            HashSet<Integer>[] randomTree = random.getRandomTree();
            printTree(randomTree);
            ProblemFSolver solver = new ProblemFSolver(randomTree);
            for (int i = 0; i != m; i++) {
                final int v = random.nextVertex(-1);
                if (random.nextBoolean()) {
                    input.println("1 " + (v + 1));
                    solver.changeColor(v);
                } else {
                    input.println("2 " + (v + 1));
                    output.println(solver.getDist(v));
                }
            }
            input.close();
            output.close();
            System.out.println("your input in 'test.in'\nanswers in 'test.out'");
        }

        private PrintWriter input;
    }

    public static void main(String[] args) throws IOException {
        new ProblemFTestCreator().createTest();
    }
}
