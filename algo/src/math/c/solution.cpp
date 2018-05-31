#include <stdio.h>
#include <stdlib.h>
#include <iostream>
 
using namespace std; 
typedef long long ll;

ll mul_mod(unsigned long long a, unsigned long long b, const unsigned long long n) {
    unsigned long long res = 0;
    while (b) {
        if (b % 2 != 0) {
            res += a;
            while (res >= n)
                res -= n;
            --b;
        } else {
            a <<= 1;
            while (a >= n)
                a -= n;
            b >>= 1;
        }
    }
    return (ll) res;
}
 
// ll gcd(ll a, ll b) {
//     if (a == 0ll) {
//         return b;
//     } else {
//         return gcd(b % a, a);
//     }
// }
 
// void transform_num(ll n, ll & p, ll & q) {
//     p = 0;
//     while (n % 2ll == 0ll) {
//         ++p;
//         n >>= 1ll;
//     }
//     q = n;
// }
 
ll powmod(ll a, ll b, ll m) {
    ll res = 1;
    while (b) {
        if (b % 2ll != 0) {
            res = mul_mod(res, a, m);
            --b;
         } else {
            a = mul_mod(a, a, m);
            b >>= 1ll;
         }
    }
    return res;
}

bool miller_rabin(ll d, const ll n) {
    int a = 2 + rand() % (n - 4);

    ll x = powmod(a, d, n);
    const ll dec_n = n - 1;

    if (x == 1ll || x == dec_n) {
        return true;
    }

    while (d != dec_n) {
        x = mul_mod(x, x, n);
        d <<= 1;
        if (x == 1) {
            return false;
        } else if (x == dec_n) {
            return true;
        }
    }
    return false;
}

bool prob_prime(ll n) {
    if (n == 2ll || n == 3ll) {
        return true;
    } else if (n < 2ll || n % 2ll == 0) {
        return false;
    }
     
    ll d = n - 1;
    while (d % 2 == 0) {
        d >>= 1;
    }

    for (int i = 0; i != 20; ++i) {
        if (miller_rabin(d, n) == false) {
            return false;
        }
    }
    return true;
}
 
int main() {
    // for (int i = 0; i != 1000000; ++i) {
    //     bool my = prob_prime(i);
    //     bool other = isPrime(i, 100);
    //     if (my != other) {
    //         cout << "wrong answer!\n";
    //         cout << i << "\n";
    //         cout << "was: " << my;
    //         cout << " expected: " << other << "\n";
    //         exit(1);
    //     }
    // }

    int n;
    scanf("%d", &n);
    for (int i = 0; i != n; ++i) {
        ll value;
        scanf("%lld", &value);

        if (prob_prime(value)) {
            printf("YES\n");
        } else {
            printf("NO\n");
        }
    }
    return 0;
}
