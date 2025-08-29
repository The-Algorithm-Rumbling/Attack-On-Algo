#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;

int N, M, A, B, K; int ans;
int sr, sc, er, ec;
vector<vector<int>> maps;
vector<vector<bool>> vis;
queue<tuple<int,int,int>> q;

int dr[] = {1, 0, -1, 0};
int dc[] = {0, -1, 0, 1};

bool inRange(int r,int c) {
    return r>=1 && r<=N && c>=1 && c<=M;
}

bool isOk(int x, int y) {
    if(!inRange(x,y) || !inRange(x+A-1, y+B-1)) return false;
    for(int r=x; r<x+A; r++) {
        for(int c=y; c<y+B; c++) {
            if(maps[r][c] == 2) return false;
        }
    }
    return true;
}

bool bfs() {
    vis[sr][sc] = true;
    q.push({sr,sc,0});

    while(!q.empty()) {
        auto[r,c, cnt] = q.front(); q.pop();

        if(r==er && c == ec) {
            ans = cnt;
            return true;
        }

        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];
            if(isOk(nr, nc) && !vis[nr][nc]) {
                vis[nr][nc] = true;
                q.push({nr,nc, cnt+1});
            }
        }
    }

    return false;
}

int main() {
    cin >> N >> M >> A >> B >> K;
    maps.resize(N+1, vector<int>(M+1, 0));
    vis.resize(N+1, vector<bool>(M+1,false));
    for(int i=0; i<K; i++) {
        int r, c;
        cin >> r >> c;
        maps[r][c] = 2;
    }
    cin >> sr >> sc >> er >> ec;
    
    if(bfs()) cout << ans;
    else cout << -1;
    return 0;
}