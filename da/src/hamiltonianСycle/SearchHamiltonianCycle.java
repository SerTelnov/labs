package hamiltonianСycle;

import java.io.*;
import java.util.Scanner;

/**
 * Created by @author Telnov Sergey on 12.10.2017.
 */

public class SearchHamiltonianCycle {

    private class ArrayQueue {

        public ArrayQueue(final int n) {
            this.data_ = new int[n];
            for (int i = 0; i != n; i++) {
                this.data_[i] = i;
            }
            this.head = 0;
        }


        public int at(int index) { return data_[(index + head) % data_.length]; }

        public void next() {
            this.head++;
            if (head == data_.length) {
                head = 0;
            }
        }

        public void subReverse(final int index) {
            int left = head + 1;
            int right = index + head;
            while (left < right) {
                int temp = data_[left % data_.length];
                data_[left % data_.length] = data_[right % data_.length];
                data_[right % data_.length] = temp;
                left++;
                right--;
            }
        }

        public void print() throws IOException {
            PrintWriter output = new PrintWriter(new FileWriter("fullham.out"));

            for (int i = 0; i != n; i++) {
                output.print((data_[i] + 1) + " ");
            }
            output.close();
        }

        private int[] data_;
        private int head;
    }

    private boolean[][] graph;
    private int n;
    private ArrayQueue queue;

    private void scan() throws FileNotFoundException {
        Scanner input = new Scanner(new File("fullham.in"));

        n = input.nextInt();
        input.nextLine();
        graph = new boolean[n][n];
        for (int i = 0; i != n; i++) {
            String curLine = input.nextLine();
            for (int j = 0; j != curLine.length(); j++) {
                graph[i][j] = graph[j][i] = curLine.charAt(j) == '1';
            }
        }
        input.close();
    }

    private void solve() {
        queue = new ArrayQueue(n);
        int count = n * (n - 1);
        for (int i = 0; i != count; i++) {
            int first = queue.at(0);
            int second = queue.at(1);
            if (!graph[first][second]) {
                int index = 2;
                while (index < n &&
                        (!(graph[first][queue.at(index)] &&
                                graph[second][queue.at(index + 1)]))
                        ) {
                    index++;
                }
                queue.subReverse(index);
            }
            queue.next();
        }
    }

    public void run() throws IOException {
        scan();
        solve();
        queue.print();
    }

    public static void main(String[] args) throws IOException {
        new SearchHamiltonianCycle().run();
    }
}
