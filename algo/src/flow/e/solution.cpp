#include <stdio.h>
#include <vector>
#include <unordered_set>
#include <unordered_map>

using namespace std;

const int MAX_N = 100;
const int MAX_DIAGONALS_NUMBER = MAX_N * 2;
int n, m;
int matchingCounter = 0;
int diagonalsCounter;
int numberMatching;
bool classicBoard[MAX_N][MAX_N];
bool board[MAX_N][MAX_N];
vector<int> graph[2][MAX_DIAGONALS_NUMBER];
vector<int> matching[2];
vector<bool> used(MAX_DIAGONALS_NUMBER * 2);
unordered_set<int> L, R;
unordered_set<int> matchingL, matchingR;
unordered_map<int, int> matchingEdges;
vector<int> pgraph[2][MAX_DIAGONALS_NUMBER * 2];

bool foundMatching(int curr, const int number) {
    if (used[curr]) 
        return false;

    used[curr] = true;
    for (auto next : graph[number][curr]) {
        if (matching[number][next] == -1 ||
            foundMatching(matching[number][next], number)) {
            matching[number][next] = curr;
            matchingCounter++;
            return true;
        }
    }
    return false;
}

int getMinMatching() {
    matching[0] = vector<int>(diagonalsCounter, -1);
    for (int i = 0; i != diagonalsCounter; ++i) {
        fill(used.begin(), used.end(), false);
        foundMatching(i, 0);
    }
    const int currCounter = matchingCounter;
    matchingCounter = 0;
    
    matching[1] = vector<int>(diagonalsCounter, -1);
    for (int i = 0; i != diagonalsCounter; ++i) {
        fill(used.begin(), used.end(), false);
        foundMatching(i, 1);
    }

    int number = currCounter <= matchingCounter ? 0 : 1;
    for (int i = 0; i != diagonalsCounter; ++i) {
        if (matching[number][i] != -1) {
            matchingL.insert(matching[number][i]);
            matchingR.insert(i + diagonalsCounter);
            matchingEdges[i + diagonalsCounter] = matching[number][i];
        }
    }
    return number;
}

void addVertex(const int i, const int j) {
    const int first = i + j;
    const int second = i + m - j - 1;
    if (board[i][j] != classicBoard[i][j]) {
        graph[0][first].push_back(second);
        pgraph[0][first].push_back(second + diagonalsCounter);
        pgraph[0][second + diagonalsCounter].push_back(first);
    } else {
        graph[1][first].push_back(second);
        pgraph[1][first].push_back(second + diagonalsCounter);
        pgraph[1][second + diagonalsCounter].push_back(first);
    }
}

void dfs(int curr, bool isRight) {
    int index = curr;
    if (isRight) 
        curr -= diagonalsCounter;

    if (used[index]) 
        return;

    if (isRight)
        R.insert(curr);
    else 
        L.insert(curr);

    used[index] = true;
    for (int next : pgraph[numberMatching][index]) {
        if (matchingEdges[index] == next) {
            if (isRight) {
                dfs(next, !isRight);
            }
        } else if (!isRight) {
            dfs(next, !isRight);
        }
    }
}

void fillSet() {
    fill(used.begin(), used.end(), false);
    for (int i = 0; i != diagonalsCounter; ++i) {
        if (matchingL.find(i) == matchingL.end())
            dfs(i, false);
    }
}

void printDiagonal(const int type, const int number) {
    int i, j;
    if (type == 0) {
        if (number < m) {
            i = 0;
            j = number;
        } else {
            i = number - m + 1;
            j = m - 1;
        }
    } else {
        if (number < m) {
            i = 0;
            j = m - number - 1;
        } else {
            i = number - m + 1;
            j = 0;
        }
    }

    char ch;
    if (numberMatching == 0) {
        if (classicBoard[i][j])
            ch = 'B';
        else
            ch = 'W';
    } else {
        if (!classicBoard[i][j]) 
            ch = 'B';
        else 
            ch = 'W';
    }
    printf("%d %d %d %c\n", type + 1, i + 1, j + 1, ch);
}

int main() {
    scanf("%d%d", &n, &m);
    diagonalsCounter = n + m - 1;

    bool curr = false;
    for (int i = 0; i != n; ++i) {
        char line[m];
        scanf("%s", line);
        for (int j = 0; j != m; ++j) {
            classicBoard[i][j] = curr;
            curr = !curr;
            board[i][j] = line[j] == 'B';
            addVertex(i, j);
        }
    }

    numberMatching = getMinMatching();
    fillSet();

    vector<pair<int, int>> result;
    for (int i = 0; i != diagonalsCounter; ++i) {
        if (L.find(i) == L.end()) {
            result.push_back({0, i});
        }
    }

    for (auto el : R) {
        result.push_back({1, el});
    }

    printf("%d\n", result.size());

    for (auto el : result) {
        printDiagonal(el.first, el.second);
    }    

    return 0;
}