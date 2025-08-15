#include <iostream>
#include <vector>
#include <climits>

using namespace std;

int N, M, K;
int ans = INT_MIN; // 음수가 들어올 수 있다.
vector<vector<int>> maps;
vector<vector<bool>> vis;

int dr[] = {-1, 0, 1, 0};
int dc[] = { 0, 1, 0, -1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

bool notAdj(int r, int c) {
    for(int d=0; d<4; d++) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        if(!inRange(nr,nc)) continue;
        if(vis[nr][nc]) return false;
    }
    return true;
}

void dfs(int idx, int cnt, int sum) {
    if(cnt == K) {
        ans = max(ans, sum);
        return;
    }
    if(idx == N*M) return;
        
    int r = idx/M;
    int c = idx%M;
    if(notAdj(r,c)) {
        vis[r][c] = true;
        dfs(idx+1, cnt+1, sum+maps[r][c]);
        vis[r][c] = false;
    }

    dfs(idx+1, cnt, sum);
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M >> K;
    maps.resize(N, vector<int>(M,0));
    vis.resize(N, vector<bool>(M,false));

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
        }
    }

    dfs(0, 0, 0);
    cout << ans;
    return 0;
}