#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

int main() {
    int n; cin >> n;
    vector<int> dp(50001, 98765432);

    dp[0] = 1; dp[1] = 1;
    for(int i=2; i<=n; i++) {
        int f = floor(sqrt(i));
        if(f*f == i) {
            dp[i] = 1;
        }
        else {
            while(f>=1) {
                dp[i] = min(dp[i], dp[f*f] + dp[i - f*f]);
                f--;
            }
        }
    }
    
    cout << dp[n];
    return 0;
}