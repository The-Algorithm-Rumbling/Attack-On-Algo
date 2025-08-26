#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

int N;

bool isPrime(int num) {
    for(int i=2; i<= sqrt(num); i++) {
        if(num % i == 0) return false;
    }
    return true;
}

void dfs(int num, int depth) {
    if(depth == N) {
        cout << num << "\n";
        return;
    }
    
    for(int i=1; i<=9; i+=2) {
        if(depth == 0 && i==1) continue; 
        int tmpNum = num * 10 + i;
        if(isPrime(tmpNum)) {
            dfs(tmpNum, depth+1);
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N; 

    dfs(2, 1);
    dfs(0, 0);
    return 0;
}