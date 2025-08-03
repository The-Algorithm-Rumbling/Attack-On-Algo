#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int M, N, K; int cnt;
vector<vector<int>> maps;
vector<vector<bool>> vis;
queue<pair<int,int>> q;

int dr[] = {-1,0,1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

void bfs() {
    while(!q.empty()) {
        auto [r, c] = q.front(); q.pop();

        for(int d=0; d<4; d++) {
            int nr = dr[d] + r; int nc = dc[d] + c;
            if(inRange(nr,nc) && !vis[nr][nc] && maps[nr][nc] == 1) {
                vis[nr][nc] = true;
                q.push({nr,nc});
            }
        }
    }
    cnt++;
}


int main() {
    cin.tie(0); cout.tie(0); 
    ios::sync_with_stdio(0);
    int T; cin >> T;
    while(T--) {
        cin >> M >> N >> K; cnt = 0;
        maps.assign(N,vector<int>(M,0));
        vis.assign(N, vector<bool>(M,false));
        
        for(int i=0; i<K; i++) {
            int c; int r;
            cin >> c >> r;
            maps[r][c] = 1;
        }
       

        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(!vis[r][c] && maps[r][c] == 1) {
                    vis[r][c] = true; q.push({r,c});
                    bfs();
                }
            }
        }
        cout << cnt << "\n";
    }
    return 0;
}