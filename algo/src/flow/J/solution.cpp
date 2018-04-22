#include <stdio.h>
#include <iostream>
#include <unordered_map>
#include <queue>
#include <vector>

using namespace std;

typedef long long ll;

const int MAX_N = 5010;
const ll INF = 10e9 + 100;

struct object {
    object() {
    }

    object(ll x1, ll y1, ll x2, ll y2)
        : left(x1)
        , right(x2)
        , bottom(y1)
        , top(y2) 
    { }

    ll dist(object & other) {
        if (left > other.left)
            return other.dist(*this);
        
        ll val = abs(right - other.left);
        if (right > other.left) {
            if (bottom <= other.bottom && top >= other.top ||
                    other.bottom <= bottom && other.top >= top) {
                return 0;
            }
            val = 0;
        }
        if (top <= other.bottom) {
            val = max(val, abs(other.bottom - top));
        } else if (bottom >= other.top) {
            val = max(val, abs(bottom - other.top));
        }
        return val;
    }

    ll left, right, bottom, top;
};

int n;
ll w;
vector<pair<int, ll>> graph[MAX_N];

ll getMinDist() {
    int start = 0;
    int end = 1;
    vector<bool> used(n + 2, false);
    vector<ll> dist(n + 2, INF);
    
    dist[start] = 0;

    for (int i = 0; i != n + 2; ++i) {
        int curr = -1;
        for (int j = 0; j != n + 2; j++) {
            if (!used[j] && (curr == -1 || dist[j] < dist[curr])) {
                curr = j;
            }
        }

        if (dist[curr] == INF)
            break;

        used[curr] = true;
        for (auto next : graph[curr]) {
            if (dist[next.first] > dist[curr] + next.second) {
                dist[next.first] = dist[curr] + next.second;
            }
        }
    }
    return dist[end];
}

int main() {
    scanf("%d%lld", &n, &w);
    object objects[MAX_N];
    objects[0] = object(-INF, -1, INF, 0);
    objects[1] = object(-INF, w, INF, w + 1);
    graph[0].push_back({1, w});
    graph[1].push_back({0, w});

    for (int i = 0; i != n; ++i) {
        ll x1, y1, x2, y2;
        scanf("%lld%lld%lld%lld", &x1, &y1, &x2, &y2);
        objects[i + 2] = object(x1, y1, x2, y2);

        for (int j = 0; j != i + 2; ++j) {
            ll val = objects[j].dist(objects[i + 2]);
            graph[j].push_back({i + 2, val});
            graph[i + 2].push_back({j, val});
        }
    }

    printf("%lld", getMinDist());
    return 0;
}
