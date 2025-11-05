#include <iostream>
#include <vector>

using namespace std;

int main() {
    int N; cin >> N;
    vector<int> arr(301, 0);
    vector<int> dp(301, 0);

    for(int i=1; i<=N; i++) {
        cin >> arr[i];
    }
    
    dp[1] = arr[1];
    dp[2] = arr[1] + arr[2];
    dp[3] = max(arr[1]+arr[3], arr[2]+arr[3]);
    
    for(int i=4; i<=N; i++) {
        int a = dp[i-2] + arr[i];
        int b = dp[i-3] + arr[i-1] + arr[i];
        dp[i] = max(a,b);
    }
    
    cout << dp[N];
    
    return 0;
}