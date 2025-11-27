#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int N, M;
vector<vector<int>> maps;
vector<vector<bool>> vis;
queue<pair<int,int>> q;

int dr[] = {1,0,-1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

void bfs2(int sr, int sc) {
    vis[sr][sc] = true;
    queue<pair<int,int>> q2; 
    q2.push({sr,sc});

    while(!q2.empty()) {
        auto[r,c] = q2.front(); q2.pop();

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(inRange(nr,nc) && maps[nr][nc] > 0 && !vis[nr][nc]) {
                vis[nr][nc] = true;
                q2.push({nr,nc});
            }
        }
    }
}

void bfs1() {
    vector<vector<int>> tmps(N, vector<int>(M, 0));
    
    while(!q.empty()) {
        auto[r,c] = q.front(); q.pop();
        int cnt = 0;
        
        for(int d=0; d<4; d++) {
            int nr = r+dr[d]; int nc = c+dc[d];
            if(inRange(nr,nc) && maps[nr][nc]==0) {
                cnt++;
            }
        }

        tmps[r][c] = cnt;
    }


    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            maps[r][c] -= tmps[r][c];
            if(maps[r][c] < 0) maps[r][c] = 0;
            else if(maps[r][c] > 0) q.push({r,c});
        }
    }
}

int main() {
    cin >> N >> M;
    
    maps.resize(N, vector<int>(M, 0));
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
            if(maps[r][c] > 0) {
                q.push({r,c});
            }
        }
    }

    int year = 0;
    while(!q.empty()) {
        year++;
        bfs1();

        int cnt  = 0;
        vis.assign(N, vector<bool>(M, false));
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(maps[r][c] > 0 && !vis[r][c]) {
                    bfs2(r,c);
                    cnt++;
                    if(cnt >= 2) {
                        cout << year;
                        exit(0);
                    }
                }
            }
        }
    }

    cout << 0;
    return 0;
}