#include <stdio.h>
#include <unordered_set>
#include <unordered_map>
#include <queue>

using namespace std;

const int MAX_CELL_NUMBER = 50 * 50 * 2;
const int MAX_VALUE = 1000000;

bool changedCell[MAX_CELL_NUMBER][MAX_CELL_NUMBER];

int verticesCounter = 0;
int buildCellsCounter = 0;
unordered_map<int, int> network[MAX_CELL_NUMBER];
int cells[MAX_CELL_NUMBER][MAX_CELL_NUMBER];
char world[MAX_CELL_NUMBER][MAX_CELL_NUMBER];
unordered_set<int> graph[MAX_CELL_NUMBER];
int buildCells[MAX_CELL_NUMBER * 3];

int s = -1;
int t = -1;
int n, m;

bool used[MAX_CELL_NUMBER];

void fillSet(int curr) {
    if (curr == t || used[curr]) 
        return;
    
    used[curr] = true;
    for (int next : graph[curr]) {
        fillSet(next);
    }
}

int changeWorld() {
    const int size = verticesCounter;
    unordered_map<int, int> flows[size];
    vector<int> path(size);
    vector<int> mins(size);
    int queue[size * 2];

    while (true) {
        fill(path.begin(), path.end(), -1);
        fill(mins.begin(), mins.end(), MAX_VALUE);
        int tail = 0;
        int head = 0;

        queue[tail++] = s;
        path[s] = MAX_VALUE;

        while (head < tail) {
            const int curr = queue[head++];
            if (curr == t) 
                break;

            for (int next : graph[curr]) {
                if (path[next] == -1) {
                    path[next] = curr;
                    int value = network[curr][next];
                    if (value != MAX_VALUE) {
                        value -= flows[curr][next];
                    }
                    mins[next] = min(mins[curr], value);

                    queue[tail++] = next;
                }
            }
        }

        if (path[t] == -1)
            break;

        const int min = mins[t];

        if (min != 1) {
            return -1;
        }

        int curr = t;
        while (curr != s) {
            int prev = path[curr];

            flows[prev][curr] += min;
            flows[curr][prev] -= min;

            if (network[prev][curr] != MAX_VALUE && network[prev][curr] - flows[prev][curr] == 0) {
                graph[prev].erase(curr);
            }

            if (network[curr][prev] - flows[curr][prev] != 0) {
                graph[curr].insert(prev);
            }

            curr = prev;
        }
    }

    fillSet(s - 1);

    int count = 0;
    for (int i = 0; i != buildCellsCounter; ++i) {
        int curr = buildCells[i * 3];
        int x = buildCells[i * 3 + 1];
        int y = buildCells[i * 3 + 2];

        if (used[curr] && !used[curr + 1]) {
            changedCell[x][y] = true;
            count++;
        }
    }
    return count;
}

int getValue(int i, int j) {
    switch(world[i][j]) {
        case '.':
            return 1;
        case '#':
            return -1;
        default:
            return MAX_VALUE;
    }
}

void link(int entry, int dEntry) {
    graph[entry + 1].insert(dEntry);
    network[entry + 1][dEntry] = MAX_VALUE;

    graph[dEntry + 1].insert(entry);
    network[dEntry + 1][entry] = MAX_VALUE;
}

int getCell(int i, int j, int value) {
    if (cells[i][j] != -1) 
        return cells[i][j];
    
    int entry = verticesCounter++;
    int exit = verticesCounter++;

    cells[i][j] = entry;
    graph[entry].insert(exit);
    network[entry][exit] = value;

    if (value == 1) {
        buildCells[buildCellsCounter * 3] = entry;
        buildCells[buildCellsCounter * 3 + 1] = i;
        buildCells[buildCellsCounter * 3 + 2] = j;
        buildCellsCounter++;
    }

    return entry;
}

int sequence[4];

int putCell(int i, int j, int count) {
    if (i >= 0 && i < n && j >= 0 && j < m) {
        int value = getValue(i, j);
        if (value != -1) {
            sequence[count] = getCell(i, j, value);
            return 1;
        }
    }
    return 0;
}


int getNearCells(int i, int j) {
    int count = 0;

    count += putCell(i, j + 1, count);
    count += putCell(i, j - 1, count);
    count += putCell(i + 1, j, count);
    count += putCell(i - 1, j, count);
    return count;
}

void init() {
    for (int i = 0; i != n; ++i) {
        for (int j = 0; j != m; ++j) {
            int value = getValue(i, j);
            if (value != -1) {
                int curr = getCell(i, j, value);

                int count = getNearCells(i, j);
                for (int k = 0; k != count; ++k) {
                    link(curr, sequence[k]);
                }

                if (world[i][j] == 'A') {
                    s = curr + 1;
                } else if (world[i][j] == 'B') {
                    t = curr;
                }
            }
        }
    }
}

int main() {
    scanf("%d%d", &n, &m);

    for (int i = 0; i != n; ++i) {
        scanf("%s", world[i]);
        for (int j = 0; j != m; ++j) {
            cells[i][j] = -1;
        }
    }

    init();
    int flow = changeWorld();
    
    printf("%d\n", flow);
    if (flow >= 0) {
        for (int i = 0; i != n; ++i) {
            for (int j = 0; j != m; ++j) {
                if (changedCell[i][j]) {
                    printf("+");
                } else {
                    printf("%c", world[i][j]);
                }
            }
            printf("\n");
        }
    }
}