#include <iostream>
#include <vector>

using namespace std;
int INF = 98765432;

int main() {
    int N; cin >> N;
    vector<int> dp(1000001, INF);

    dp[1] = 0; dp[2] = 1; dp[3] = 1;
    for(int i=4; i<=N; i++) {
        if(i%3 == 0) dp[i] = dp[i/3]+1;
        if(i%2 == 0) dp[i] = min(dp[i], dp[i/2]+1);
        dp[i] = min(dp[i], dp[i-1]+1);
    }
    cout << dp[N];
    return 0;
}