#include <iostream>
#include <vector>

using namespace std;

int R, C, K;
int ans = 0;
vector<vector<char>> maps;
vector<vector<bool>> vis;

int dr[] = {1, 0, -1, 0};
int dc[] = {0, -1, 0, 1};

bool inRange(int r, int c) {
    return r>=0 && r<R && c>=0 && c<C;
}

void dfs(int r, int c, int cnt) {
    if(r==0 &&  c== C-1 && cnt == K) {
        ans++;
        return;
    }
    if(cnt >= K) return;

    for(int d=0; d<4; d++) {
        int nr = r + dr[d];
        int nc = c + dc[d];

        if(inRange(nr,nc) && maps[nr][nc] =='.' && !vis[nr][nc]) {
            vis[nr][nc] = true;
            dfs(nr, nc, cnt+1);
            vis[nr][nc] = false;
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> R >> C >> K;
    maps.resize(R, vector<char>(C,'\0'));
    vis.resize(R, vector<bool>(C,false));
    
    for(int r=0; r<R; r++) {
        for(int c=0; c<C; c++) {
            cin >> maps[r][c];
        }
    }

    vis[R-1][0] = true;
    dfs(R-1, 0, 1);
    cout << ans;
    return 0;
}