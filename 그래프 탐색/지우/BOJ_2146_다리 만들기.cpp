#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;
int num = 2;
int N; int INF = 98765432;
int ans = INF;
vector<vector<int>> maps;
vector<vector<bool>> used;
vector<vector<bool>> vis;
queue<tuple<int,int,int>> q2;

int dr[] = {1,0,-1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

int Bfs2(int x, int y, int color) {
    queue<tuple<int,int,int>> q;
    vector<vector<bool>> vis2(N, vector<bool>(N,0));
    vis2[x][y] = true;
    q.push({x,y,1});

    while(!q.empty()) {
        auto[r, c, cnt] = q.front(); q.pop();
        
        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];
            if(!inRange(nr,nc)) continue;
            if(maps[nr][nc] != color && !vis2[nr][nc]) {
                if(maps[nr][nc] != 0) {
                    return cnt;
                } else {
                    vis2[nr][nc] = true;
                    q.push({nr,nc, cnt+1});
                }
            }
        }
    }

    return INF;
}

void Bfs() {
    while(!q2.empty()) {
        auto[r, c, color] = q2.front(); q2.pop();
        
        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];
            if(!inRange(nr,nc)) continue;
            if(maps[nr][nc] != color && !vis[nr][nc]) {
                vis[nr][nc] = true;
                ans = min(ans, Bfs2(nr, nc, color));
            }
        }
    }
}

void MakeIsland(int r, int c) {
    queue<pair<int,int>> q;
    maps[r][c] = num;
    q.push({r,c});

    while(!q.empty()) {
        auto[r,c] = q.front(); q.pop();

        for(int d=0; d<4; d++) {
            int nr = r+dr[d]; int nc = c+dc[d];
            if(inRange(nr,nc) && maps[nr][nc]==1 && !used[nr][nc]) {
                maps[nr][nc] = num;
                q.push({nr,nc});
            }
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

    cin >> N;
    maps.resize(N, vector<int>(N,0));
    used.resize(N, vector<bool>(N,0));
    vis.resize(N, vector<bool>(N,0));

    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            cin >> maps[r][c];
        }
    }

    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            if(!used[r][c] && maps[r][c] == 1) {
                used[r][c] = true;
                MakeIsland(r,c);
                num++;
            }
        }
    }
    
    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            if(maps[r][c] != 0) q2.push({r,c,maps[r][c]});
        }
    }
    Bfs();
    cout << ans;
    return 0;
}