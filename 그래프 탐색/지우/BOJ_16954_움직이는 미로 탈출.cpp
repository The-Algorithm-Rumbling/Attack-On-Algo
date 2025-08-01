#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int ans = 0;
vector<vector<vector<int>>> maps;
queue<vector<int>> q;
queue<vector<int>> wq;
vector<vector<vector<bool>>> vis;

int dr[] = {1, 0, -1, 0, -1, -1, 1 ,1};
int dc[] = {0, 1, 0, -1, -1, 1, -1, 1};

bool inRange(int r, int c) {
    return r>=0 && r<8 && c>=0 && c<8;
}

void bfs() {
    while(!q.empty()) {

        int wSize = wq.size();
        while(wSize--) {
            vector<int> curr = wq.front(); wq.pop();
            int r = curr[0]; int c = curr[1];
            int time = curr[2];
            
            int nr = r + dr[0];
            int nc = c + dc[0];
            if(inRange(nr,nc)) {
                maps[nr][nc][time+1] = 1; 
                wq.push({nr, nc, time+1});
            }
        }

        int cSize = q.size();
        while(cSize--) {
            vector<int> curr = q.front(); q.pop();
            int r = curr[0]; int c = curr[1];
            int time = curr[2];

            if(r == 0 && c == 7) {
                ans = 1;
                return;
            }

            for(int d=0; d<8; d++) {
                int nr = r + dr[d]; int nc = c + dc[d];
                if(inRange(nr, nc) && !vis[nr][nc][time+1] && maps[nr][nc][time] != 1 && maps[nr][nc][time+1] != 1) { 
                                                                //지금 time에 벽이 없어야 갈 수 있다 && 다음에 넘어갈 곳도 벽도 없어야 함
                    vis[nr][nc][time+1] = true;
                    q.push({nr,nc,time+1});
                }
            }
            if(!vis[r][c][time+1] && maps[r][c][time+1] != 1) {
                vis[r][c][time+1] = true;
                q.push({r,c,time+1});
            }
        }

        
    }
}

int main() {
    maps.resize(8, vector<vector<int>>(8,vector<int>(66,0))); // r,c, 걸린 시간
    vis.resize(8, vector<vector<bool>>(8,vector<bool>(66,false)));
    
    // 8 * 8
    for(int r=0; r<8; r++) {
        string a; cin >> a;
        for(int c=0; c<8; c++) {
            if(a[c] == '.') {
                maps[r][c][1] = 0;
            } else {
                maps[r][c][1] = 1;
                wq.push({r,c,1});
            }
        }
    }

    vis[7][0][1] = true;
    q.push({7,0,1});
    bfs();
    cout << ans;
    return 0;
}