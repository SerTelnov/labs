#include <stdio.h>
#include <vector>
#include <unordered_map>
#include <math.h>
#include <float.h>

using namespace std;

typedef long long ll;

const int MAX_SIZE = 55;

struct point {
    int x, y;
    double speed;

    point() {
    }

    point(int x, int y, int speed)
        : x(x)
        , y(y)
        , speed(speed) 
    { }

    double evaluate(int x, int y) {
        double dx = this->x - x;
        double dy = this->y - y;
        return sqrt(dx * dx + dy * dy) / speed;
    }
};

double values[MAX_SIZE][MAX_SIZE];
vector<vector<bool>> is_deleted(MAX_SIZE, vector<bool>(MAX_SIZE, false));
vector<bool> used(MAX_SIZE);
vector<int> matching(MAX_SIZE);
int n;

int found_matching(int curr) {
	if (used[curr]) 
		return false;
	used[curr] = true;
	for (int next = 0; next != n; ++next) {
		if (!is_deleted[curr][next]) {
			if (matching[next] == -1 || 
				found_matching(matching[next])) {
				matching[next] = curr;
				return true;
			}
		}
	}
	return false;
}

double get_curr_value() {
	fill(matching.begin(), matching.end(), -1);
	for (int i = 0; i != n; ++i) {
		fill(used.begin(), used.end(), false);
		found_matching(i);
	}

	double max = -1;
	for (int i = 0; i != n; ++i) {
		if (matching[i] == -1) {
			return -1;
		}
		double val = values[matching[i]][i];
		if (max < val) {
			max = val;
		}
	}
	
	for (int i = 0; i != n; ++i) {
		for (int j = 0; j != n; ++j) {
			if (values[i][j] >= max) {
				is_deleted[i][j] = true;
			}
		}
	}
	return max;
}

int main() {
    scanf("%d", &n);
	vector<point> points(n);

	for (int i = 0; i != n; ++i) {
		int x, y, speed;
		scanf("%d%d%d", &x, &y, &speed);
		points[i] = point(x, y, speed);
	}

	for (int i = 0; i != n; ++i) {
		int x, y;
		scanf("%d%d", &x, &y);
		for (int j = 0; j != n; ++j) {
			values[j][i] = points[j].evaluate(x, y);
		}
	}

    double result = -1;
	while (true) {
		double val = get_curr_value();
		if (val == -1) 
			break;
		result = val;
	}
    printf("%llf", result);
	return 0;
}