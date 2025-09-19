#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;
int ans = -1;
int N, M; int sr, sc, er, ec;
int INF = 98765432;
vector<vector<int>> maps;
vector<vector<vector<int>>> dp;
queue<tuple<int,int,int>> q;

int dr[] = {1,0,-1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

void Bfs() {
    dp[sr][sc][0] = 0;
    q.push({sr,sc,0});

    while(!q.empty()) {
        auto[r, c, wall] = q.front(); q.pop();
        
        if(r==er && c==ec) {
            ans = dp[er][ec][wall];
            return;
        }

        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c+dc[d];
            if(!inRange(nr,nc)) continue;
            // 벽 부순다!
            if(maps[nr][nc]==1 && wall == 0 && dp[nr][nc][1] > dp[r][c][0]+1) {
                dp[nr][nc][1] = dp[r][c][0]+1;
                q.push({nr,nc,1});
            }
            else if(maps[nr][nc]==0 && dp[nr][nc][wall] > dp[r][c][wall]+1){ // 안 부순다!
                dp[nr][nc][wall] = dp[r][c][wall]+1;
                q.push({nr,nc,wall});
            }
        }
    }
}
  
int main() {
    cin >> N >> M;
    maps.resize(N, vector<int>(M,0));
    dp.resize(N, vector<vector<int>>(M, vector<int>(2, INF)));
    cin >> sr >> sc >> er >> ec;
    sr--; sc--; er--; ec--;
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
        }
    }

    Bfs();
    cout << ans;
    
    return 0;
}