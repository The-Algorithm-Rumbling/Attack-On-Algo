#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, M; int INF = 98765432;
int ans = INF;
vector<vector<int>> maps;
vector<vector<int>> dp;
vector<pair<int,int>> twos;
vector<int> picks;
queue<pair<int,int>> q;

int dr[] = {-1,0,1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r,int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

int bfs() {
    dp.assign(N, vector<int>(N, INF));
    for(int i=0; i<M; i++) {
        auto[r,c] = twos[picks[i]];
        dp[r][c] = 0;
        q.push({r,c});
    }

    while(!q.empty()) {
        auto [r,c] = q.front(); q.pop();
        
        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];
            if(!inRange(nr,nc)) continue;
            if(maps[nr][nc]==0 && dp[nr][nc] > dp[r][c]+1) {
                dp[nr][nc] = dp[r][c]+1;
                q.push({nr,nc});
            }
            else if(maps[nr][nc] ==2 && dp[nr][nc] > dp[r][c]+1) {
                dp[nr][nc] = dp[r][c]+1;
                q.push({nr,nc});
            }
        }
    }
    
    int maxTime = 0;
    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            if(maps[r][c] == 0) {
                maxTime = max(maxTime, dp[r][c]);
            }
        }
    }
    return maxTime;
}

void dfs(int cnt, int start) {
    if(cnt == M) {
        ans = min(ans,bfs());
        return;
    }

    for(int i=start; i<twos.size(); i++) {
        picks.push_back(i);
        dfs(cnt+1, i+1);
        picks.pop_back();
    }
}

int main() {
    cin >> N >> M;
    maps.resize(N, vector<int>(N, 0));
    dp.resize(N, vector<int>(N, INF));
    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            cin >> maps[r][c];
            if(maps[r][c] == 2) {
                twos.push_back({r,c});
            }
        }
    }

    dfs(0,0);
    if(ans == INF) cout << -1; 
    else cout << ans;
    return 0;
}