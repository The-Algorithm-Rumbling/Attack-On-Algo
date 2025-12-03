#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;

int N, M;
int INF = 98765432;
vector<vector<int>> maps;
vector<vector<vector<int>>> dp;

int dr[] = {1, 1, 1};
int dc[] = {-1, 0, 1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

void bfs(int sr, int sc) {
    queue<tuple<int,int,int>> q;
    for(int i=0; i<3; i++) {
        dp[i][sr][sc] = maps[sr][sc];
        q.push({sr,sc,i});
    }

    while(!q.empty()) {
        auto[r, c, curD] = q.front(); q.pop();
        if(r == N-1) continue;

        for(int d=0; d<3; d++) {
            if(d==curD) continue;
            
            int nr = r+dr[d]; int nc=c+dc[d];
            if(!inRange(nr,nc)) continue;
            if(dp[d][nr][nc] > dp[curD][r][c] + maps[nr][nc]) {
                dp[d][nr][nc] = dp[curD][r][c] + maps[nr][nc];
                q.push({nr,nc,d});
            }
        }
    }
}

int main() {
    cin >> N >> M;
    maps.resize(N, vector<int>(M,0));
    dp.resize(3, vector<vector<int>>(N, vector<int>(M, INF)));

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
        }
    }

    for(int c=0; c<M; c++) {
        bfs(0,c);
    }

    int ans = INF;
    for(int c=0; c<M; c++) {
        for(int d=0; d<3; d++) {
            ans = min(ans, dp[d][N-1][c]);
        }
    }
    cout << ans;
    
    return 0;
}