#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int arr[] = {9,8,7,6,5,4,3,2,1,0};
vector<long long> dp;

void dfs(int start, long long sum) {
    if(start != 0) {
        dp.push_back(sum);
    }
    if(start == 10) return;
    
    for(int i=start; i<10; i++) {
        dfs(i+1, sum*10 + arr[i]);
    }
}

int main() {
    int N; cin >> N;

    dfs(0, 0);
    sort(dp.begin(), dp.end());
    int maxN = dp.size();

    if(maxN >= N) cout << dp[N-1];
    else cout << -1;
    return 0;
}