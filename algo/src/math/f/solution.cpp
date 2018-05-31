#include <stdio.h>
#include <stdlib.h>
#include <algorithm>
#include <unordered_set>
#include <math.h>

using namespace std;

void gcd(int a, int b, int & x, int & y) {
	if (a == 0) {
		x = 0; y = 1;
	} else {
        int x1, y1;
        gcd(b % a, a, x1, y1);
        x = y1 - (b / a) * x1;
        y = x1;
    }
}

int powmod(int a, int b, int m) {
	int res = 1;
	while (b) {
		if (b & 1) {
			res = (res * 1ll * a) % m;
            --b;
         } else {
			a = (a * 1ll * a) % m;
            b >>= 1;
         }
    }
	return res;
}

int main() {
    int n, e, mes;
    scanf("%d%d%d", &n, &e, &mes);

    int p, q, fi;

    if (n % 2 == 0) {
        p = 2;
        q = n / 2;
        fi = (p - 1) * (q - 1);
    } else {
        for (int a = 3; a < n; a += 2) {
            if (n % a == 0) {
                p = a;
                q = n / a;
                fi = (p - 1) * (q - 1);
                break;
            }
        }
    }

    int x, y;
    gcd(e, fi, x, y);
    int d = (x % fi + fi) % fi;

    printf("%d", powmod(mes, d, n));
    return 0;
}