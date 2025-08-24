#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, M;
int ans = 0;
vector<vector<int>> maps;
vector<vector<int>> dp;
queue<pair<int,int>> q;

int dr[] = {1,0,-1,0};
int dc[] = {0,-1,0,1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}
 
void bfs(int x, int y) {
    dp.assign(N, vector<int>(M,-1));
    dp[x][y] = 0;
    q.push({x,y});

    while(!q.empty()) {

        auto [r,c] = q.front(); q.pop();
        bool find = false;

        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];
            if(inRange(nr, nc) && maps[nr][nc] == 1 && dp[nr][nc] == -1) {
                find = true;
                dp[nr][nc] = dp[r][c] + 1;
                q.push({nr,nc});
            }
        }

        if(!find) {
            ans = max(ans, dp[r][c]);
        }
    }
    
}

int main() {
    cin >> N >> M;
    maps.resize(N, vector<int>(M,0));

    for(int r=0; r<N; r++) {
        string s; cin >> s;
        for(int c=0; c<M; c++) {
            if(s[c] == 'W') maps[r][c] = 0;
            else maps[r][c] = 1;
        }
    }

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            if(maps[r][c] == 1) {
                bfs(r,c);
            }
        }
    }
    cout << ans;    
    return 0;
}