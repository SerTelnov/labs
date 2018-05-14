#include <stdio.h>
#include <list>
#include <queue>
#include <vector>
#include <algorithm>
 
using namespace std;
 
typedef long long ll;
// #define MAX_VALUE 9223372036854775807

// typedef int ll;
const int MAX_VALUE = 10e9 + 10;
 
struct comp {
    bool operator() (pair<int, int> a, pair<int, int> b) {
        return a.second < b.second;
    }
};
 
struct edge {
    edge() : edge(0, 0, 0, 0) {
    }
 
    edge(int flow, int cost, int to_name, int index) 
        : index(index)
        , to_name(to_name)
        , capacity(flow)
        , flow(0)
        , cost(cost) {
    }
 
    int index, to_name;
    int capacity, flow, cost;
};
 
edge graph[550][550];
int graph_size[550];
 
int n, s, t;
 
inline ll run_flow() {
    ll* dist = new ll[n];
    int* min_values = new int[n];
    int* path = new int[n];
    int* edges_index = new int[n];
    int* vertex_info = new int[n];
    queue<int> q; 
 
    while (true) {
        fill(dist, dist + n, MAX_VALUE);
        fill(vertex_info, vertex_info + n, 0);
 
        q.push(s);
        dist[s] = 0;
        min_values[s] = MAX_VALUE;
        path[t] = -1;
 
        while (!q.empty()) {
            int curr = q.front();
            q.pop();
            vertex_info[curr] = 1;
 
            for (int i = 0; i != graph_size[curr]; ++i) {
                auto e = graph[curr][i];
                int to = e.to_name;
 
                if (e.flow < e.capacity && dist[to] > dist[curr] + e.cost) {
                    dist[to] = dist[curr] + e.cost;
                    edges_index[to] = i;
 
                    if (vertex_info[to] == 0) {
                        q.push(to);
                    } else if (vertex_info[to] == 1) {
                        q.push(to);
                    }
 
                    ll val = e.capacity - e.flow;
                    if (min_values[i] > val) {
                        min_values[to] = val;
                    } else {
                        min_values[to] = min_values[i];
                    }
 
                    path[to] = curr;
                    vertex_info[to] = 1;
                }
            }
        }

        if (path[t] == -1) {
            break;
        }
 
        int curr = t; 
        int min = min_values[t];
 
        while (curr != s) {
            int e_index = edges_index[curr];
            int prev = path[curr];
 
            graph[prev][e_index].flow += min;
            graph[curr][graph[prev][e_index].index].flow -= min;
             
            curr = prev;
        }
    }
 
    ll min_cost = 0;
    for (int i = 0; i != n; ++i)
        for (auto e: graph[i]) 
            if (e.flow > 0) 
                min_cost += e.flow * e.cost;
 
    return min_cost;
}
 
inline void push_edge(int from, int to, int cost, int flow) {
    graph[from][graph_size[from]] = edge(flow, cost, to, graph_size[to]);
    graph[to][graph_size[to]] = edge(0, -cost, from, graph_size[from]); 
    graph_size[from]++;
    graph_size[to]++;
}
 
int main() {
    int m;
    scanf("%d%d", &n, &m);
 
    s = 0;
    t = n + n + 1;
    fill(graph_size, graph_size + t, 0);    
 
    ll value;
    for (int i = 0; i != n; ++i) {    
        scanf("%d", &value);
 
        int left = i + i + 1;
        int right = left + 1;
 
        push_edge(s, left, 0, 1);
        push_edge(right, t, 0, 1);
        push_edge(left, right, value, MAX_VALUE);
        push_edge(right, left, 0, MAX_VALUE);
    }
 
    n = n + n + 2;    
 
    int from, to;
    for (int i = 0; i != m; ++i) {
        scanf("%d%d%d", &from, &to, &value);
        push_edge(from + from - 1, to + to, value, MAX_VALUE);
    }
 
    printf("%lld", run_flow());
    return 0;
}