#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;

int N, M, T;
int INF = 98765432;
int answer = INF;
vector<vector<int>> maps;
vector<vector<int>> vis;
queue<pair<int, int>> q;

int dr[] = {1,0,-1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}
 
void bfs() {
    vis[0][0] = 0;
    q.push({0,0});

    while(!q.empty()) {
        auto[r,c] = q.front(); q.pop();
        if(maps[r][c] == 2) {
            answer = min(answer, vis[r][c] + (N-1-r) + (M-1-c));
            continue;
        }
        if(r==N-1 && c==M-1) {
            answer = min(answer, vis[r][c]);
            return;
        }

        for(int d=0; d<4; d++) {
            int nr = r+dr[d]; int nc = c + dc[d];
            if(inRange(nr, nc) && maps[nr][nc] != 1 && vis[nr][nc] == INF) {
                vis[nr][nc] = vis[r][c]+1;
                q.push({nr,nc});
            }
        }
    }
}

int main() {
    cin >> N >> M >> T;
    maps.resize(N, vector<int>(M, 0));
    vis.resize(N, vector<int>(M, INF));

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
        }
    }
    bfs();
    if(answer <= T) cout << answer;
    else cout << "Fail";
    
    return 0;
}