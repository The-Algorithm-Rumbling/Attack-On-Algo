#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;
int N, M, K, X;
vector<vector<int>> adj;
vector<bool> vis;
queue<pair<int,int>> q;
vector<int> ans;

void bfs() {
    q.push({X, 0});
    vis[X] = true;
    
    while(!q.empty()) {
        auto [curr, dis] = q.front(); q.pop();
        if(dis == K) {
            ans.push_back(curr);
        }
        if(dis >= K) continue;

        for(auto nxt : adj[curr]) {
            if(vis[nxt]) continue;
            vis[nxt] = true;
            q.push({nxt, dis+1});
        }
    }
    
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M >> K >> X;
    adj.resize(N+1);
    vis.resize(N+1, false);
    
    for(int i=0; i<M; i++) {
        int a, b; cin >> a >> b;
        adj[a].push_back(b);
    }

    bfs();
    sort(ans.begin(), ans.end());
    
    if(ans.size() == 0) cout << -1;
    else {
        for(auto i : ans) {
            cout << i << "\n";
        }
    }
    return 0;
}