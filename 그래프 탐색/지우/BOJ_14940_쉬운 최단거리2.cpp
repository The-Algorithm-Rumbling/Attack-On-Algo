#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int N, M;
vector<vector<int>> maps;
vector<vector<int>> dp;
queue<pair<int,int>> q;

int dr[] = {1, 0, -1, 0};
int dc[] = {0, 1, 0, -1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

void bfs(int sr, int sc) {
    dp[sr][sc] = 0;
    q.push({sr, sc});

    while(!q.empty()) {
        auto[r, c] = q.front(); q.pop();

        for(int d=0; d<4; d++) {
            int nr = r+dr[d]; int nc = c+dc[d];
            if(!inRange(nr,nc) || maps[nr][nc] == 0 || dp[nr][nc] != -1) continue;
            dp[nr][nc] = dp[r][c] + 1;
            q.push({nr,nc});
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    maps.resize(N, vector<int>(M, 0));
    dp.resize(N, vector<int>(M, -1));

    int sr = 0; int sc = 0;
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
            if(maps[r][c] == 2) {
                sr = r; sc = c;
            }
        }
    }

    bfs(sr, sc);
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            if(maps[r][c] == 0) cout << 0 << " ";
            else cout << dp[r][c] << " ";
        }
        cout << "\n";
    }
    
    return 0;
}