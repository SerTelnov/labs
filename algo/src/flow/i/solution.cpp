#include <stdio.h>
#include <vector>
#include <unordered_map>
#include <unordered_set>
#include <iostream>
#include <queue>

using namespace std;

const int MAX_N = 100;
const int MAX_COUNT = 20000;
const int MAX_VALUE = 10000;

unordered_map<int, int> network[MAX_COUNT];
int teams[MAX_N];
int counter = 0;

int setTeam(int i) {
    if (teams[i] != 0) {
        return teams[i];
    } else {
        teams[i] = counter;
        return counter++;
    }
}

char getSymbol(int value) {
    switch(value) {
        case 3:
            return 'W';
        case 2:
            return 'w';
        case 1:
            return 'l';
        default:
            return 'L';
    }
}

int getValue(char ch) {
    switch(ch) {
        case 'W':
            return 3;
        case 'w':
            return 2;
        case 'l':
            return 1;
        default:
            return 0;
    }
}

int getOpValue(char ch) {
    switch(ch) {
        case 'W':
            return 0;
        case 'w':
            return 1;
        case 'l':
            return 2;
        default:
            return 3;
    }
}

int main() {
    int n;
    scanf("%d", &n);
    vector<int> scores(n);
    
    int s = counter++;
    int games[n][n];
    char result[n][n];
    unordered_set<int> graph[MAX_COUNT];
    for (int i = 0; i != n; ++i) {
        scanf("%s", result[i]);
        for (int j = 0; j != n; ++j) {
            if (i > j) {
                if (result[i][j] == '.') {
                    int curr = counter++;
                    network[s][curr] = 3;
                    graph[s].insert(curr);
                    
                    int team1 = setTeam(i);
                    int team2 = setTeam(j);

                    network[curr][team1] = 3;
                    graph[curr].insert(team1);
                    network[curr][team2] = 3;
                    graph[curr].insert(team2);

                    games[i][j] = curr;
                    games[j][i] = curr;
                } else {
                    scores[i] -= getValue(result[i][j]);
                    scores[j] -= getOpValue(result[i][j]);
                }
            }
        }
    }

    int t = counter++;
    for (int i = 0; i != n; ++i) {
        int value;
        scanf("%d", &value);
        graph[teams[i]].insert(t);
        network[teams[i]][t] = value + scores[i];
    }

    vector<int> path;
    unordered_map<int, int> flows[counter];
    deque<int> q;

    while (true) {
        path.assign(counter, -1);
        q.clear();
        q.push_back(s);
 
        path[s] = MAX_VALUE;
        while (!q.empty()) {            
            const int curr = q.front();
            q.pop_front();

            bool wasEnd = false;            
            for (int next : graph[curr]) {
                if (path[next] == -1) {
                    path[next] = curr;
                    if (next != t) {
                        q.push_back(next);
                    } else {
                        wasEnd = true;
                        break;
                    }
                }
            }
            if (wasEnd) {
                break;
            }
        }
 
        if (path[t] == -1) {
            break;
        }

        int curr = t; 
        int min = MAX_VALUE;
        while (curr != s) {
            const int prev = path[curr];
 
            if (min > network[prev][curr] - flows[prev][curr]) {
                min = network[prev][curr] - flows[prev][curr];
            }
            curr = prev;
        }
 
        curr = t;
        while (curr != s) {
            const int prev = path[curr];
            flows[prev][curr] += min;
            flows[curr][prev] -= min;
 
            if (network[prev][curr] - flows[prev][curr] == 0) {
                graph[prev].erase(curr);
            }

            if (network[curr][prev] - flows[curr][prev]) {
                graph[curr].insert(prev);
            }
            curr = prev;
        }
    }

    for (int i = 0; i != n; ++i) {
        for (int j = 0; j != n; j++) {
            if (result[i][j] == '.') {
                printf("%c", getSymbol(flows[games[i][j]][teams[i]]));
            } else {
                printf("%c", result[i][j]);
            }
        }
        printf("\n");
    }
    return 0;
}
