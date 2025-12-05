#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> prices;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    int T; cin >> T;
    while(T--) {
        int N; cin >> N;
        prices.assign(N, 0);
        for(int i=0; i<N; i++) {
            cin >> prices[i];
        }

        long long ans = 0;
        int maxP = prices[N-1];
        for(int i=N-2; i>=0; i--) {
            if(maxP > prices[i]) {
                ans += maxP - prices[i];
            }
            else if(maxP < prices[i]) {
                maxP = prices[i];
            }
        }

        cout << ans << "\n";
    }
    return 0;
}