#include <iostream>
#include <vector>

using namespace std;

int main() {
    int N; cin >> N;
    vector<int> dp(1001, 0);

    for(int i=1; i<=N; i+=2) {
        dp[i] = 0;
    }

    for(int i=2; i<=N; i+=2) {
        dp[i] = 1;
    }

    if(dp[N] == 0) cout << "SK";
    else cout << "CY";
    
    return 0;
}