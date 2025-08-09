#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, M, T; int ans = 0;
vector<vector<int>> maps;
vector<vector<bool>> vis;
queue<pair<int,int>> q;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

int avg(int r, int g, int b) {
    int result = (r + g + b) / 3;
    return result;
}

void bfs() {
    while(!q.empty()) {
        auto[r,c] = q.front(); q.pop();

        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];
            if(inRange(nr,nc) && !vis[nr][nc] && maps[nr][nc] >= T) {
                vis[nr][nc] = true;
                q.push({nr,nc});
            }
        }
    }
    ans++;
}


int main() {
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(0);
    cin >> N >> M;
    maps.resize(N, vector<int>(M,0));
    vis.resize(N, vector<bool>(M,0));

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            int R, G, B; 
            cin >> R >> G >> B;
            int a = avg(R,G,B);
            maps[r][c] = a;
        }
    }

    cin >> T;

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            if(!vis[r][c] && maps[r][c] >= T) {
                vis[r][c] = true;
                q.push({r,c});
                bfs();
            }
        }
    }

    cout << ans;
    return 0;
}