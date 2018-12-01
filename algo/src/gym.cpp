#include <stdio.h>
#include <vector>
#include <math.h>
#include <fstream>

using namespace std;

int main() {
    int len = sqrt(250000) + 100;
    ofstream fout("input.txt");
    for (int i = 0; i != len; i++) {
        fout << i % 10;
    }    
    fout << "\n-";
    for (int i = 0; i != len; i++) {
        fout << i % 10;
    }    
    fout << '\n';
    fout.close();
}