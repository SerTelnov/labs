#include <stdio.h>
#include <stdlib.h>
#include <algorithm>

using namespace std;

int gcd(int a, int b, int & x, int & y) {
	if (a == 0) {
		x = 0; y = 1;
		return b;
	}
	int x1, y1;
	int d = gcd(b % a, a, x1, y1);
	x = y1 - (b / a) * x1;
	y = x1;
	return d;
}

int chinese_theorem(const int n, int * a, int * p) {
    int x[n];

    int r[n][n];
    for (int i = 0; i != n; ++i) {
        for (int j = i + 1; j < n; ++j) {
            int x, y;
            gcd(p[i], p[j], x, y);
            r[i][j] = (x % p[j] + p[j]) % p[j];
        }
    }

    for (int i = 0; i != n; ++i) {
        x[i] = a[i];
        for (int j = 0; j != i; ++j) {
            x[i] = r[j][i] * (x[i] - x[j]);
            x[i] %= p[i];
            if (x[i] < 0) {
                x[i] += p[i];
            }
        }
    }

    int res = x[0];
    for (int i = 1; i != n; ++i) {
        res += x[i] * p[i - 1];
    }
    return res;
}

int main() {
    int a[2];
    int p[2];
    scanf("%d%d%d%d", &a[0], &a[1], &p[0], &p[1]);
    printf("%d", chinese_theorem(2, a, p));
    return 0;
}