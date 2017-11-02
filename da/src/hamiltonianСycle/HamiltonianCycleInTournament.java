package hamiltonianСycle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by @author Telnov Sergey on 15.10.2017.
 */

public class HamiltonianCycleInTournament {

    private int n;
    private boolean[][] graph;
    private LinkedList<Integer> cycle;
    private LinkedList<Integer> noUsed;

    private void scan() throws IOException {
        Scanner input = new Scanner(new File("guyaury.in"));
        n = input.nextInt();
        input.nextLine();

        graph = new boolean[n][n];
        cycle = new LinkedList<>();
        noUsed = new LinkedList<>();

        for (int i = 0; i != n; i++) {
            noUsed.add(i);
            String info = input.nextLine();
            for (int j = 0; j != info.length(); j++) {
                if (info.charAt(j) == '1') {
                    graph[i][j] = true;
                } else {
                    graph[j][i] = true;
                }
            }
        }
    }

    private void putVertex(final Integer vertex) {
        putVertex(cycle.size(), vertex);
    }

    private void putVertex(final int position, final Integer vertex) {
        cycle.add(position, vertex);
        noUsed.remove(vertex);
    }

    private int getSecondVertex(final Integer vertex, final boolean vertexIsFrom) {
        for (final Integer curr: noUsed) {
            if (vertexIsFrom) {
                if (graph[curr][cycle.peek()] && graph[vertex][curr]) {
                    return curr;
                }
            } else if (graph[cycle.peek()][curr] && graph[curr][vertex]) {
                return curr;
            }
        }
        return -1;
    }

    private void addVertexes() {
        for (final Integer curr : noUsed) {
            int to, from;
            if (graph[curr][cycle.peek()]) {
                from = getSecondVertex(curr, false);
                to = curr;
            } else {
                from = curr;
                to = getSecondVertex(curr, true);
            }
            if (from == -1 || to == -1) {
                continue;
            }
            noUsed.add(cycle.removeLast());
            putVertex(from);
            putVertex(to);
            break;
        }
    }

    private boolean addVertex(final Integer vertex) {
        int prev = cycle.peekLast();
        int index = 0;
        for (final int curr : cycle) {
            if (graph[prev][vertex] && graph[vertex][curr]) {
                putVertex(index, vertex);
                return true;
            }
            index++;
            prev = curr;
        }
        return false;
    }

    private void createStartCycle() {
        putVertex(0);
        if (n != 1) {
            for (final int i : noUsed) {
                if (graph[0][i]) {
                    for (final int j : noUsed) {
                        if (graph[j][0]) {
                            if (graph[i][j]) {
                                putVertex(i);
                                putVertex(j);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private void solve() {
        createStartCycle();

        for (int i = 0; i != n - 3; i++) {
            boolean vertexAdded = false;
            for (final Integer curr : noUsed) {
                if (addVertex(curr)) {
                    vertexAdded = true;
                    break;
                }
            }
            if (!vertexAdded) {
                addVertexes();
            }
        }
    }

    public void run() throws IOException {
        scan();
        solve();
        PrintWriter output = new PrintWriter(new FileWriter("guyaury.out"));
        for (final Integer curr : cycle) {
            output.print(curr + 1 + " ");
        }
        output.close();
    }

    public static void main(String[] args) throws IOException {
        new HamiltonianCycleInTournament().run();
    }
}
