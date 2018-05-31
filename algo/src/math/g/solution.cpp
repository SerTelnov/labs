#include <stdio.h>
#include <iostream>
#include <stdlib.h>
#include <vector>
#include <complex>
#include <math.h>
#include <algorithm>

using namespace std;

typedef complex<double> comp_type;
const double PI = 3.14159265359;

int power_of_2(unsigned int n) {
	--n;
	n |= n >> 1;
	n |= n >> 2;
	n |= n >> 4;
	n |= n >> 8;
	n |= n >> 16;
	++n;
	return n;
}

void fft(vector<comp_type> & a, bool is_invert) {
	int n = a.size();
 
	for (int i = 1, j = 0; i != n; ++i) {
		int bit = n >> 1;
		for (; j >= bit; bit >>= 1) {
			j -= bit;
        }

		j += bit;
		if (i < j) {
			swap(a[i], a[j]);
        }
	}
 
	for (int len = 2; len <= n; len <<= 1) {
		double ang = 2 * PI / len;
        if (is_invert) {
            ang = -ang;
        }

		comp_type wlen(cos(ang), sin(ang));
		for (int i = 0; i < n; i += len) {
			comp_type w(1);
			for (int j = 0; j < len / 2; ++j) {
				comp_type u = a[i + j];
                comp_type v = a[i + j + len / 2] * w;
				a[i + j] = u + v;
				a[i + j + len / 2] = u - v;
				w *= wlen;
			}
		}
	}

	if (is_invert) {
		for (int i = 0; i != n; ++i) {
			a[i] /= n;
        }
    }
}

void multiply(const vector<int> & a, const vector<int> & b, vector<int> & result) {
    vector<comp_type> fa(a.begin(), a.end());
    vector<comp_type> fb(b.begin(), b.end());
    size_t n = power_of_2(a.size() > b.size() ? a.size() : b.size());
    n <<= 1;
    
    fa.resize(n);
    fb.resize(n);

    fft(fa, false);
    fft(fb, false);

    for (size_t i = 0; i != n; ++i) {
        fa[i] *= fb[i];
    }

    fft(fa, true);

    result.resize(n);
    for (size_t i = 0; i != n; ++i) {
        result[i] = (int) (fa[i].real() + 0.5);
    }

    int carry = 0;

    for (size_t i = 0; i != n; ++i) {
        result[i] += carry;
        carry = result[i] / 10;
        result[i] %= 10;
    }
}

void scanf_num(vector<int> & a, bool & res_is_neg) {
    char ch;
    while ((cin.get(ch)) && ch != '\n') {
        if (ch == '-') {
            res_is_neg = !res_is_neg;
        } else {
            a.push_back(ch - '0');
        }
    }

    reverse(a.begin(), a.end());
}

int main() {
    ios_base::sync_with_stdio(false);

    bool res_is_neg = false;
    vector<int> a;
    vector<int> b;
    vector<int> result;

    scanf_num(a, res_is_neg);
    scanf_num(b, res_is_neg);

    multiply(a, b, result);

    reverse(result.begin(), result.end());
    size_t i = 0;
    while (i != result.size() && result[i] == 0) {
        i++;
    }

    if (i != result.size()) {
        if (res_is_neg) {
            printf("-");
        }
        for (; i != result.size(); ++i) {
            printf("%d", result[i]);
        }
    } else {
        printf("0");
    }
    return 0;
}
