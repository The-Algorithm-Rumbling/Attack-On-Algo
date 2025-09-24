#include <iostream>
#include <vector>
#include <set>

using namespace std;
int N;
vector<vector<int>> adj;
vector<bool> vis;
vector<int> parent;
set<int> sets;
bool found = false;
int ans;

void dfs(int curr, int depth) {
    if(sets.count(curr)) {
        ans = min(depth, ans);
        return;
    }
    if(depth >= ans) return;
    
    for(int nxt : adj[curr]) {
        if(!vis[nxt]) {
            vis[nxt] = true;
            dfs(nxt, depth+1);
            vis[nxt] = false;
        }
    }
}

void CheckRotate(int p, int curr) {
    if(found) return;

    for(int nxt : adj[curr]) {
        if(nxt == p) continue;
        if(!vis[nxt]) {
            vis[nxt] = true; parent[nxt] = curr;
            CheckRotate(curr, nxt);
            
            vis[nxt] = false; parent[nxt] = 0;
        }
        else { // 순환 발견!!
            found = true;
            for(int i=curr; i!=nxt; i = parent[i]) {
                sets.insert(i);
            }
            sets.insert(nxt);
            return;
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    adj.resize(N+1, vector<int>(0));
    parent.resize(N+1,0);
    vis.resize(N+1,false);

    for(int i=0; i<N; i++) {
        int a, b;
        cin >> a >> b;

        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    // 순환 확인!
    vis[1] = true; parent[1] = -1;
    CheckRotate(-1, 1);

    // 순환까지 최단거리 확인!
    for(int i=1; i<=N; i++) {
        if(sets.count(i)) {
            cout << 0 << " ";
        } else {
            ans = 98765432;
            vis.assign(N+1,false);
            vis[i] = true;
            dfs(i,0);
            cout << ans << " ";
        }
    }
    
    return 0;
}