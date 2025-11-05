#include <iostream>
#include <vector>

using namespace std;
int INF = 98765432;

int main() {
    int N; cin >> N;
    vector<int> dp(5001, INF);

    for(int i=3; i<=5000; i+=3) {
        dp[i] = i/3;
    }
    for(int i=5; i<=5000; i+=5) {
        dp[i] = min(dp[i], i/5);
    }

    for(int t=3; t<=5000; t+=3) {
        for(int f=5; f<=5000; f+=5) {
            if(t+f > 5000) break;
            dp[t+f] = min(dp[t+f], dp[t]+dp[f]);
        }
    }

    if(dp[N] == INF) cout << -1;
    else cout << dp[N];
    
    return 0;
}