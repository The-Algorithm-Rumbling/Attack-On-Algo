#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int N, M, V;
vector<vector<int>> adj;
vector<int> vis;

void bfs(int v) {
    queue<int> q;
    vis[v] = true;
    q.push(v);

    while(!q.empty()) {
        int curr = q.front(); q.pop();
        cout << curr << " ";

        for(int nxt : adj[curr]) {
            if(vis[nxt]) continue;
            vis[nxt] = true;
            q.push(nxt);
        }
    }
}

void dfs(int v) {
    cout << v << " ";
    
    for(int nxt : adj[v]) {
        if(vis[nxt]) continue;
        vis[nxt] = true;
        dfs(nxt);
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M >> V;

    adj.resize(N+1, vector<int>(0));
    
    for(int i=0; i<M; i++) {
        int a, b; cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    for(int i=1; i<=N; i++) {
        sort(adj[i].begin(), adj[i].end());
    }
    
    vis.assign(N+1, false);
    vis[V] = true;
    dfs(V);
    cout << "\n";

    vis.assign(N+1, false);
    bfs(V);
    return 0;
}