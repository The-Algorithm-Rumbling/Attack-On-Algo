#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

void print(__int128 num) {
    string s = "";

    while(num > 0) {
        s += num%10 + '0';
        num /= 10;
    }
    reverse(s.begin(), s.end());
    cout << s;
    
}

int main() {
    int N, M; cin >> N >> M;
    vector<vector<__int128>> dp(N + 1, vector<__int128>(M + 1, 0));

    for(int i=1; i<=N; i++) {
        for(int j=0; j<=M; j++) {
            if(i < j) break;
            if(i == j || j == 0) {
                dp[i][j] = 1;
            }
            else if(j == 1) {
                dp[i][j] = i;
            }
            else {
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j]; 
            }
        }
    }
    print(dp[N][M]);
    return 0;
}