#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N;
int ans1; int ans2;
vector<vector<char>> maps;
vector<vector<bool>> vis;
queue<pair<int,int>> q;

int dr[] = {1, 0, -1, 0};
int dc[] = {0, -1, 0, 1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

void bfs(int r, int c, char color) {
    vis[r][c] = true;
    q.push({r,c});

    while(!q.empty()) {
        auto[r,c] = q.front(); q.pop();

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(inRange(nr, nc) && maps[nr][nc] == color && !vis[nr][nc]) {
                vis[nr][nc] = true;
                q.push({nr,nc});
            }
        }
    }
}

void bfs2(int r, int c, char color) {
    vis[r][c] = true;
    q.push({r,c});
    bool flag = false;
    
    if(color == 'R' || color == 'G') {
        flag = true;
    }
    
    while(!q.empty()) {
        auto[r,c] = q.front(); q.pop();

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(!flag) {
                if(inRange(nr, nc) && maps[nr][nc] == color && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    q.push({nr,nc});
                }
            }
            else {
                if(inRange(nr, nc) && (maps[nr][nc] == 'R' || maps[nr][nc] == 'G') && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    q.push({nr,nc});
                }
            }
            
        }
    }
}

int main() {
    cin >> N;
    maps.resize(N, vector<char>(N,0));

    for(int r=0; r<N; r++) {
        string s; cin >> s;
        for(int c=0; c<N; c++) {
            maps[r][c] = s[c];
        }
    }

    vis.assign(N, vector<bool>(N,false));
    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            if(!vis[r][c]) {
                bfs(r,c, maps[r][c]);
                ans1++;
            }
        }
    }

    vis.assign(N, vector<bool>(N,false));
    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            if(!vis[r][c]) {
                bfs2(r,c, maps[r][c]);
                ans2++;
            }
        }
    }
    
    cout << ans1 << " " << ans2;
    return 0;
}