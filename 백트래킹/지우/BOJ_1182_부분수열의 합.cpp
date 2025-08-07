#include <iostream>
#include <vector>

using namespace std;

int N, S;
int ans = 0;
vector<int> nums;

void dfs(int sum, int idx) {
    if(idx == N) {
        if(sum == S) ans++;
        return;
    }

    dfs(sum + nums[idx], idx+1);
    dfs(sum, idx+1);
}

int main() {
    cin >> N >> S;
    nums.resize(N,0);

    for(int i=0; i<N; i++) {
        cin >> nums[i];
    }

    
    dfs(0, 0);
    if(S == 0) ans--;
    cout << ans;
    
    return 0;
}