#include <iostream>
#include <vector>

using namespace std;
int R, C;
int ans;
vector<vector<char>> maps;
vector<bool> vis;

int dr[] = {-1,0,1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r, int c) {
    return r>=0 && r<R && c>=0 && c<C;
}

void dfs(int cnt, int r, int c) {
    ans = max(ans, cnt);
    
    for(int d=0; d<4; d++) {
        int nr = r + dr[d]; int nc = c + dc[d];
        if(inRange(nr,nc) && !vis[maps[nr][nc]-'A']) {
            vis[maps[nr][nc]-'A'] = true;
            dfs(cnt+1, nr, nc);
            vis[maps[nr][nc]-'A'] = false;
        }
    }
}

int main() {
    cin >> R >> C;
    maps.resize(R, vector<char>(C, '\0'));
    vis.resize(26, false);
    
    for(int r=0; r<R; r++) {
        string s; cin >> s;
        for(int c=0; c<C; c++) {
            maps[r][c] = s[c];
        }
    }

    vis[maps[0][0] - 'A'] = true;
    dfs(1,0,0);
    cout << ans;
    return 0;
}