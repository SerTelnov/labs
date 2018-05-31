#include <stdio.h>
#include <stdlib.h>
#include <algorithm>
#include <math.h>

using namespace std;

const int BLOCK_SIZE = 16000;
const int SQRT_N = 32000;

int main() {
    int n, x;
    scanf("%d%d", &n, &x);
    
    bool not_prime[SQRT_N];
    bool blocks[BLOCK_SIZE];
    int primes[SQRT_N];
    int sqrt_n = (int) sqrt(n);
    
    fill(not_prime, not_prime + sqrt_n, false);
    primes[0] = 2;
    not_prime[0] = not_prime[1] = true;
    for (int i = 4; i <= sqrt_n; i += 2) {
        not_prime[i] = true;
    }

    int prime_counter = 1;
    for (int i = 3; i <= sqrt_n; i += 2) {
        if (!not_prime[i]) {
            primes[prime_counter++] = i;
            if ((long long) i * i <= sqrt_n) {
                for (int j = i * i; j <= sqrt_n; j += i) {
                    not_prime[j] = true;
                }
            }
        }
    }

    int hash = 0;
    const int blocks_counter = n / BLOCK_SIZE;

    for (int k = 0; k <= blocks_counter; ++k) {
        fill(blocks, blocks + sqrt_n, false);
        int left = k * BLOCK_SIZE;

        for (int i = 0; i != prime_counter; ++i) {
            int index = (left + primes[i] - 1) / primes[i];
			int j = max(index, 2) * primes[i] - left;

			for (; j < BLOCK_SIZE; j += primes[i]) {
				blocks[j] = true;
            }
        }
        if (k == 0) {
            blocks[0] = blocks[1] = true;
        }
        for (int i = 0; i != BLOCK_SIZE && left + i <= n; ++i) {
            if (!blocks[i]) {
                hash = hash * x + left + i;
            }
        }
    }
    printf("%d", hash);
    return 0;
}
