#include <stdio.h>
#include <algorithm>

using namespace std;

typedef long long ll;

void exec(ll & a, ll & b, ll & c) {
    ll val = min(a, b);
    a -= val;
    b -= val;

    if (b > 0) {
        val = min(c, b);
        c -= val;
        b -= val;
    }
}

ll getRes1(ll r1, ll s1, ll p1, ll r2, ll s2, ll p2) {
    exec(r1, p2, p1);
    exec(s1, r2, r1);
    exec(p1, s2, s1);
    return r1 + p1 + s1;
}

ll getRes2(ll r1, ll s1, ll p1, ll r2, ll s2, ll p2) {
    exec(p1, s2, s1);
    exec(s1, r2, r1);
    exec(r1, p2, p1);
    return r1 + p1 + s1;
}

int main() {
    // FILE * input = fopen("C:/pro/homework/labs/algo/src/minFlow/d/rps2.in", "r");
    FILE * input = fopen("rps2.in", "r");
    FILE * output = fopen("rps2.out", "w");

    ll r1, s1, p1, r2, s2, p2;
    fscanf(input, "%lld %lld %lld %lld %lld %lld", &r1, &s1, &p1, &r2, &s2, &p2);

    ll val = min(getRes1(r1, s1, p1, r2, s2, p2),
        getRes2(r1, s1, p1, r2, s2, p2));

    fprintf(output, "%lld", val);

    fclose(input);
    fclose(output);
}