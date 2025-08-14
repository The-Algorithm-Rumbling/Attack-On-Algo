#include <iostream>
#include <vector>

using namespace std;

int N, K;
int ans = 0;
vector<int> kit;
vector<bool> vis;

void dfs(int sIdx, int sum) {
    if(sIdx == N) {
        ans++;
        return;
    }

    for(int i=0; i<N; i++) {
        int nxtSum = sum - K + kit[i];
        if(vis[i] || nxtSum < 500) continue;
        
        vis[i] = true;
        
        dfs(sIdx+1, nxtSum);
        vis[i] = false;
    }
}

int main() {
    cin >> N >> K;
    kit.resize(N, 0);
    vis.resize(N, false);
    for(int i=0; i<N; i++) {
        cin >> kit[i];
    }

    dfs(0, 500);
    cout << ans;
    
    return 0;
}