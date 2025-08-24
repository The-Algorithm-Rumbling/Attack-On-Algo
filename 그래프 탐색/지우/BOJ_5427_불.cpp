#include <iostream>
#include <vector>
#include <tuple>
#include <queue>

using namespace std;

int ans; int w, h; 
vector<vector<char>> maps;
vector<vector<bool>> vis;
queue<pair<int,int>> fq;
queue<tuple<int,int,int>> sq;

int dr[] = {1,0,-1,0};
int dc[] = {0, -1, 0, 1};

bool inRange(int r, int c) {
    return r>=0 && r<h && c>=0 && c<w;
}

void bfs() {
    int fsize = fq.size();
    while(fsize--) {
        auto[r,c] = fq.front(); fq.pop();

        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];

            if(inRange(nr,nc) && maps[nr][nc] == '.') {
                maps[nr][nc] = '*';
                fq.push({nr,nc});
            }
        }
    }

    int ssize = sq.size();
    while(ssize--) {
        auto[r,c, time] = sq.front(); sq.pop();

        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];

            if(inRange(nr,nc) && maps[nr][nc] == '.' && !vis[nr][nc]) {
                vis[nr][nc] = true;
                sq.push({nr,nc, time+1});
            }
            else if (!inRange(nr,nc)) {
                ans = time;
                return;
            }
        }
    }

    if(!sq.empty()) bfs();
    else return;
}

int main() {
    int T; cin >> T;
    while(T--) {
        cin >> w >> h; ans = -1;
        maps.assign(h, vector<char>(w,'\0'));
        vis.assign(h, vector<bool>(w,false));
        fq = queue<pair<int,int>>(); sq = queue<tuple<int,int,int>>();
        
        for(int r=0;r<h; r++) {
            string s; cin >> s;
            for(int c=0; c<w; c++) {
                char a = s[c];
                if(a == '@') {
                    maps[r][c] = '.';
                    vis[r][c] = true;
                    sq.push({r,c,1});
                }
                else if(a == '*') {
                    maps[r][c] = a;
                    fq.push({r,c});
                }
                else maps[r][c] = a;
            }
        }

        bfs();
        if(ans == -1) cout << "IMPOSSIBLE" << "\n";
        else cout << ans << "\n";
        
    }
    return 0;
}