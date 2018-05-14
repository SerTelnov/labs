#include <stdio.h>
#include <vector>
#include <queue>
#include <unordered_map>
#include <unordered_set>
#include <fstream>

using namespace std;

typedef long long ll;

struct edge {
    edge(int flow, int cost) 
        : capacity(flow)
        , flow(0)
        , cost(cost) {
    }

    void link(edge* other) {
        op_edge = other;
        other->op_edge = this;
    }

    bool not_completed() {
        return flow < capacity;
    }

    ll get_flow() {
        return capacity - flow;
    }

    void change_flow(int curr_flow) {
        flow += curr_flow;
        op_edge->flow -= curr_flow;
    }

    edge* op_edge;
    ll capacity;
    ll flow;
    ll cost;
};

const int MAX_SIZE = 1010;
const ll MAX_VALUE = 10e12;

vector<pair<int, edge*>> graph[MAX_SIZE];

ll run_flow(const int s, const int t, const int n) {
    vector<pair<int, edge*>> path(n);
    vector<ll> dist(n);
    vector<int> vertexInfo(n);
    deque<int> q; 
    const pair<int, edge*> NOT_VALUE = {-1, nullptr};

    ll minCost = 0;
    while (true) {
        q.clear();
        fill(path.begin(), path.end(), NOT_VALUE);
        fill(dist.begin(), dist.end(), MAX_VALUE);
        fill(vertexInfo.begin(), vertexInfo.end(), 0);

        dist[s] = 0;
        q.push_back(s);

        while (!q.empty()) {
            const int curr = q.front();
            q.pop_front();
            vertexInfo[curr] = 1;

            for (auto next: graph[curr]) {
                int to = next.first;
                auto edge = next.second;
                int cost = edge->cost;

                if (dist[to] > dist[curr] + cost && edge->not_completed()) {
                    dist[to] = dist[curr] + cost;
                    if (vertexInfo[to] == 0) {
                        q.push_back(to);
                    } else if (vertexInfo[to] == 1) {
                        q.push_front(to);
                    }

                    path[to] = {curr, edge};
                    vertexInfo[to] = 1;
                }
            }
        }

        if (path[t].first == -1) {
            break;
        }

        int curr = t; 
        ll min = MAX_VALUE;
        while (curr != s) {
            const int prev = path[curr].first;
            ll val = path[curr].second->get_flow();
            if (min > val) {
                min = val;
            }
            curr = prev;
        }
 
        curr = t;
        while (curr != s) {
            auto e = path[curr].second;

            minCost += e->cost * min;
            e->change_flow(min);
            
            curr = path[curr].first;
        }
    }
    return minCost;
}

int main() {
    int n, m;
    FILE * input = fopen("mincost.in", "r");
    fscanf(input, "%d%d", &n, &m);

    for (int i = 0; i != m; ++i) {
        int from, to, flow, cost;
        fscanf(input, "%d%d%d%d", &from, &to, &flow, &cost);
        --from;
        --to;

        edge* toEdge = new edge(flow, cost);
        edge* fromEdge = new edge(0, -cost);
        toEdge->link(fromEdge);

        graph[from].push_back({to, toEdge});
        graph[to].push_back({from, fromEdge});
    }
    fclose(input);

    ofstream fout("mincost.out");
    fout << run_flow(0, n - 1, n);

    return 0;
}