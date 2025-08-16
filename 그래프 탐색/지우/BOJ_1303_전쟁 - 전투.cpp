#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, M; int cnt;
int wAns; int bAns;
vector<vector<int>> maps;
vector<vector<bool>> vis;
queue<pair<int,int>> q;

int dr[] = {1, 0, -1, 0};
int dc[] = {0, -1, 0, 1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

int bfs(int color) {
    int cnt = 0;
    while(!q.empty()) {
        auto [r,c] = q.front(); q.pop();
        cnt++;

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(inRange(nr, nc) && !vis[nr][nc] && maps[nr][nc] == color) {
                vis[nr][nc] = true;
                q.push({nr,nc});
            } 
        }
    }
    return cnt;
}

int main() {
    cin >> M >> N;
    maps.resize(N, vector<int>(M,0));
    vis.resize(N, vector<bool>(M,false));
    
    for(int r=0; r<N; r++) {
        string s; cin >> s;
        for(int c=0; c<M; c++) {
            if(s[c] == 'W') maps[r][c] = 1;
            else maps[r][c] = 0;
        }
    }

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            if(maps[r][c] == 1 && !vis[r][c]) {
                vis[r][c] = true; q.push({r,c});
                
                cnt = bfs(1);
                wAns += cnt * cnt;
            } 
            else if(maps[r][c] == 0 && !vis[r][c]) {
                vis[r][c] = true; q.push({r,c});
                
                cnt = bfs(0);
                bAns += cnt * cnt;
            }
        }
    }

    cout << wAns << " " << bAns;
    return 0;
}