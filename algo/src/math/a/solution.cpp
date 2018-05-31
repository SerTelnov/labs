#include <stdio.h>
#include <algorithm>

using namespace std;

const int MAX_VALUE = 10e6 + 1;
bool is_prime[MAX_VALUE];

void set_prime_numbers() {
    int n = MAX_VALUE;
    fill(is_prime, is_prime + MAX_VALUE, true);    
    
    is_prime[0] = is_prime[1] = false;

    for (int i = 4; i < n; i += 2) {
        is_prime[i] = false;
    }

    for (int i = 3; i != n; i += 2) {
        if (is_prime[i]) {
            if ((long long) i * i < n) {
                for (int j = i * i; j < n; j += i) {
                    is_prime[j] = false;
                }
            }
        }
    }
}

int main() {
    set_prime_numbers();

    int n;
    scanf("%d", &n); 
    for (int i = 0; i != n; ++i) {
        int value;
        scanf("%d", &value);
        if (is_prime[value]) {
            printf("YES\n");
        } else {
            printf("NO\n");
        }
    }
    return 0;
}