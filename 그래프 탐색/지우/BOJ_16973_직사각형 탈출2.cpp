#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;
int N, M; int H, W; 
int sr, sc, fr, fc;
vector<vector<int>> maps;
vector<vector<int>> dp; // dp[r][c] = 그때까지의 벽 누적합
vector<vector<bool>> vis;
queue<tuple<int,int,int>> q;

int dr[] = {1,0,-1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r, int c) {
    return r>=1 && r<=N && c>=1 && c<=M;
}

bool check(int sx, int sy) {
    int ex = sx+H-1; int ey = sy+W-1;
    if(!inRange(ex, ey)) return false;

    int cCnt = dp[ex][ey] - dp[sx-1][ey] - dp[ex][sy-1] + dp[sx-1][sy-1];
    if(cCnt > 0) return false;
    return true;
    
}

int bfs() {
    vis[sr][sc] = true;
    q.push({sr,sc,0});

    while(!q.empty()) {
        auto[r,c, cnt] = q.front(); q.pop();
        if(r == fr && c == fc) return cnt;
        
        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];

            if(inRange(nr,nc) && maps[nr][nc] == 0 && !vis[nr][nc] && check(nr,nc)) {
                vis[nr][nc] = true;
                q.push({nr,nc,cnt+1});
            }
        }
    }

    return -1;
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    maps.resize(N+1, vector<int>(M+1,0));
    dp.resize(N+1, vector<int>(M+1,0));
    vis.resize(N+1, vector<bool>(M+1,false));
    
    for(int r=1; r<=N; r++) {
        for(int c=1; c<=M; c++) {
            cin >> maps[r][c];
            dp[r][c] = maps[r][c] + dp[r-1][c] + dp[r][c-1] - dp[r-1][c-1];
        }
    }
    cin >> H >> W >> sr >> sc >> fr >> fc;

    cout << bfs();
    return 0;
}