#include <iostream>
#include <vector>

using namespace std;

int N; 

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    vector<int> dp(10001, 0);
    dp[0] = 1;

    for(int i=1; i<=10000; i++) {
        dp[i] += dp[i-1]; // 각 수는 모든 1을 해당 개수만큼 더해서 만들 수 있음
    }

    for(int i=2; i<=10000; i++) {
        dp[i] += dp[i-2]; // 2를 사용하면 경우의 수가 하나 더 늘어날 수 있음
    }

    for(int i=3; i<=10000; i++) {
        dp[i] += dp[i-3]; // 3을 사용하면 또 하나 더 늘어난닷!
    }
    
    int T; cin >> T;
    while(T--) {
        cin >> N;
        cout << dp[N] << "\n";
    }
    
    return 0;
}