#include <iostream>
#include <vector>
#include <climits>
#include <cmath>

using namespace std;

int sr, sc; int er, ec; 
long long ans;
vector<pair<int,int>> ports; // ports[포트Idx] = {r,c}
vector<int> adj; // adj[포트Idx] = 연결포트Idx
vector<bool> vis;

int dr[] = {1,0, -1, 0};
int dc[] = {0, -1, 0, 1};

long long dist(int r, int c, int r1, int c1) {
    return abs(r-r1) + abs(c-c1);
}

void dfs(int r, int c, long long time) {
    if(ans <= time) return;
    if(r == er && c == ec) {
        if(ans > time) ans = time;
        return;
    }

    // 순수거리 계산 - 중간중간 계속 갱신
    if(ans > time + dist(r,c, er, ec)) {
        ans = time + dist(r,c,er,ec);
    }
    
    // 텔레포트
    for(int i=0; i<6; i++) {
        if(vis[i]) continue;
        
        long long tmpTime = time;
        auto[pr,pc] = ports[i];
        auto[nr,nc] = ports[adj[i]];

        tmpTime += dist(r,c,pr,pc) + 10;
        vis[i] = vis[adj[i]] = true;
        dfs(nr, nc, tmpTime);
        vis[i] = vis[adj[i]] = false;
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> sr >> sc >> er >> ec;
    ports.resize(6, {0,0});
    vis.resize(6, false);
    adj.resize(6,0);

    ans = dist(sr,sc, er,ec);

    int idx = 0;
    for(int i=0; i<3; i++) {
        int r1,c1, r2,c2;
        cin >> r1 >> c1 >> r2 >> c2;
        
        ports[idx] = {r1,c1}; ports[idx+1] = {r2,c2};
        adj[idx] = idx+1; adj[idx+1] = idx;
        idx+=2;
    }
    
    dfs(sr,sc, 0);
    cout << ans;
    return 0;
}