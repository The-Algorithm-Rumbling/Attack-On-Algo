#include <iostream>
#include <vector>

using namespace std;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    
    int T; cin >> T;
    vector<vector<int>> dp(30, vector<int>(30, 0));
    for(int m=0; m<=29; m++) {
        for(int n=0; n<=m; n++) {
            if(m==n) dp[m][n] = 1;
            else dp[m][n] = dp[m-1][n-1] + dp[m-1][n];
        }
    }
    
    while(T--) {
        int N, M;
        cin >> N >> M;
        cout << dp[M][N] << "\n";
    }
    
    return 0;
}