#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int R, C; int ans = -1;
vector<vector<char>> maps;
vector<vector<int>> dp;
queue<pair<int,int>> hq;
queue<pair<int,int>> wq;

int dr[] = {1, 0, -1, 0};
int dc[] = {0, -1, 0, 1};

bool inRange(int r, int c) {
    return r>=0 && r<R && c>=0 && c<C;
}

void bfs() {
    while(!hq.empty()) {
        int wsize = wq.size();
        while(wsize--) {
            auto[r,c] = wq.front(); wq.pop();

            for(int d=0; d<4; d++) {
                int nr = r+dr[d]; int nc = c+dc[d];
                if(inRange(nr,nc) && maps[nr][nc] == '.') {
                    maps[nr][nc] = '*';
                    wq.push({nr,nc});
                }
            }
        }
        
        int hsize = hq.size();
        while(hsize--) {
            auto[r,c] = hq.front(); hq.pop();

            if(maps[r][c] == 'D') {
                ans = dp[r][c];
                return;
            }
            
            for(int d=0; d<4; d++) {
                int nr = r+dr[d]; int nc = c+dc[d];
                if(inRange(nr,nc) && (maps[nr][nc] == '.' || maps[nr][nc] == 'D') && dp[nr][nc] == -1) {
                    dp[nr][nc] = dp[r][c]+1;
                    hq.push({nr,nc});
                }
            }
        }
    }
}

int main() {
    cin >> R >> C;
    maps.resize(R, vector<char>(C,'\0'));
    dp.resize(R, vector<int>(C,-1));

    for(int r=0; r<R; r++) {
        for(int c=0; c<C; c++) {
            cin >> maps[r][c];
            if(maps[r][c] == '*') wq.push({r,c});
            else if(maps[r][c] == 'S') {
                dp[r][c] = 0;
                maps[r][c] = '.';
                hq.push({r,c});
            }
        }
    }

    bfs();
    if(ans == -1) cout << "KAKTUS";
    else cout << ans;
    
    return 0;
}