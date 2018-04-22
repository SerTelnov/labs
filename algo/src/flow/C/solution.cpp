#include <iostream>
#include <stdio.h>
#include <unordered_map>
#include <vector>
#include <queue>
 
using namespace std;
 
const int MAX_SIZE = 100000;
const int MAX_VALUE = MAX_SIZE * 2;
 
 
unordered_map<int, int> graph[MAX_SIZE];
unordered_map<int, int> flows[MAX_SIZE];
FILE * output;
 
bool printFlow(int curr, int end) {
    fprintf(output, "%d ", curr + 1);
 
    if (curr != end) {
        for (auto next : graph[curr]) {
            if (flows[curr][next.first] > 0 && next.second >= 1) {
                graph[curr][next.first]--;
                if (printFlow(next.first, end)) {
                    return true;
                }
            }
        }
    } else {
        return true;
    }
 
    return false;
}
 
 
int run_flow(int s, int t, const int n) {
    vector<int> path(n);
    queue<int> q;
 
    while (true) {
        fill(path.begin(), path.end(), -1);
        while (!q.empty()) 
            q.pop();
        q.push(s);
 
        path[s] = 0;
        while (!q.empty()) {            
            const int curr = q.front();
            q.pop();
            if (curr == t) 
                break;
            if (graph[curr].size() == 0) 
                continue;
            for (auto next : graph[curr]) {
                if (next.second - flows[curr][next.first] > 0) {
                    if (path[next.first] == -1) {
                        path[next.first] = curr;
                        q.push(next.first);
                    }
                }
            }
        }
 
        int curr = t;
        if (path[curr] == -1) {
            break;
        }
 
        int min = MAX_VALUE;
        while (curr != s) {
            const int prev = path[curr];
 
            if (min > graph[prev][curr] - flows[prev][curr]) {
                min = graph[prev][curr] - flows[prev][curr];
            }
            curr = prev;
        }
 
        curr = t;
        while (curr != s) {
            const int prev = path[curr];
            flows[prev][curr] += min;
            flows[curr][prev] -= min;
 
            if (graph[curr][prev] - flows[curr][prev] > 0) {
                graph[curr][prev] -= flows[curr][prev];
            }
            curr = prev;
        }
    }
 
    int flow = 0;
    for (auto it : graph[s]) {
        flow += flows[s][it.first];
    }
    return flow;
}
 
int main() {
    FILE * input = fopen("snails.in", "r");
     
    int n, m, s, t;
    fscanf(input, "%d%d%d%d", &n, &m, &s, &t);
     
    --s;
    --t;
 
    for (int i = 0; i != m; ++i) {
        int to, from;
        fscanf(input, "%d%d", &from, &to);
        if (from != to) {
            graph[from - 1][to - 1]++;
        }
    }
    fclose(input);
 
    output = fopen("snails.out", "w");
    const int flow = run_flow(s, t, n);
    if (flow < 2) {
        fprintf(output, "NO");
    } else {
        fprintf(output, "YES\n");
        printFlow(s, t);
        fprintf(output, "\n");
        printFlow(s, t);
    }
 
    fclose(output);
    return 0;
}
