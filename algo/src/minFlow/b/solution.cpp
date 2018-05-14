#include <stdio.h>
#include <vector>
using namespace std;

const int MAX_N = 310;
const int MAX_VALUE = 10e8;

int a[MAX_N][MAX_N];
vector<int> p(MAX_N);

int hungatian_algorithm(const int n) {
    vector<int> u(n + 1);
    vector<int> v(n + 1);

    vector<int> minv(n + 1);
    vector<int> path(n + 1);
    vector<bool> used(n + 1);

    for (int i = 1; i <= n; ++i) {
        p[0] = i;
        int empty_column = 0;
        fill(minv.begin(), minv.end(), MAX_VALUE);
        fill(used.begin(), used.end(), false);

        do {
            used[empty_column] = true;
            int curr_line = p[empty_column];
            int curr_column;
            int delta = MAX_VALUE;
            
            for (int j = 1; j <= n; ++j) {
                if (!used[j]) {
                    int curr = a[curr_line][j] - u[curr_line] - v[j];
                    if (curr < minv[j]) {
                        minv[j] = curr;
                        path[j] = empty_column;
                    }
                    if (minv[j] < delta) {
                        delta = minv[j];
                        curr_column = j;
                    }
                }
            }

            for (int j = 0; j <= n; ++j) {
                if (used[j]) {
                    u[p[j]] += delta;
                    v[j] -= delta;
                } else {
                    minv[j] -= delta;
                }
            }
            empty_column = curr_column;  
        } while (p[empty_column] != 0);

        do {
            int j = path[empty_column];
            p[empty_column] = p[j];
            empty_column = j;
        } while (empty_column != 0);
    }
    return -v[0];
}

int main() {
    FILE* input = fopen("assignment.in", "r");
    int n;
    fscanf(input, "%d", &n);

    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= n; ++j) {
            fscanf(input, "%d", &a[i][j]);
        }
    }

    FILE* output = fopen("assignment.out", "w");
    int cost = hungatian_algorithm(n);

    fprintf(output, "%d\n", cost);

    int ans[MAX_N];
    for (int i = 1; i <= n; ++i) {
        ans[p[i]] = i;
    }

    for (int i = 1; i <= n; ++i) {
        fprintf(output, "%d %d\n", i, ans[i]);
    }
    return 0;
}