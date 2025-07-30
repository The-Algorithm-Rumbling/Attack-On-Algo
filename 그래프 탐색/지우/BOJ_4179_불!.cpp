#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int R, C; int ans; int INF;
vector<vector<int>> fireDP;
vector<vector<int>> maps;
vector<vector<bool>> vis;
queue<vector<int>> jQ;
queue<pair<int,int>> fQ;

int dr[] = {-1,0,1,0};
int dc[] = {0, 1, 0, -1};

void spreadFire();
void escape();
bool inRange(int r, int c) {
    return r>=0 && r<R && c>=0 && c<C;
}

int main() {
    cin >> R >> C; ans = -1;
    INF = 98765432;
    maps.resize(R, vector<int>(C,0));
    fireDP.resize(R, vector<int>(C,0)); 
    vis.resize(R, vector<bool>(C,0));// J 위치 visited
    for(int r=0; r<R; r++) {
        string a; cin >> a;
        for(int c=0; c<C; c++) {
            
            if(a[c] == '#') {
                maps[r][c] = 2;
                fireDP[r][c] = INF;
            } 
            else if(a[c] == 'F') {
                maps[r][c] = 1;
                fireDP[r][c] = 0;
                fQ.push({r,c});
            }
            else if(a[c] == '.') {
                maps[r][c] = 0;
                fireDP[r][c] = INF;
            }
            else if(a[c] == 'J') {
                maps[r][c] = 3;
                fireDP[r][c] = INF;
                jQ.push({r,c,0});
                vis[r][c] = true;
            }       
        }
    }

    spreadFire();
    escape();

    if(ans == -1) {
        cout << "IMPOSSIBLE";
        return 0;
    }
    cout << ans;
    return 0;
}

void spreadFire() {
    while(!fQ.empty()) {      
        auto[r,c] = fQ.front(); fQ.pop();

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(inRange(nr,nc) && maps[nr][nc] != 2 && fireDP[nr][nc] > fireDP[r][c]+1) {
                fireDP[nr][nc] = fireDP[r][c]+1;
                fQ.push({nr,nc});
            }
        }

        
    }
}

void escape() {
    while(!jQ.empty()) {
        vector<int> curr = jQ.front(); jQ.pop();
        int r = curr[0];
        int c = curr[1];
        int time = curr[2];

        if(r == 0 || c == 0 || r == R-1 || c == C-1) {
            ans = time+1;
            return;
        }

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(inRange(nr, nc) && maps[nr][nc] != 2 && !vis[nr][nc] && fireDP[nr][nc] > time +1) {
                vis[nr][nc] = true;
                jQ.push({nr,nc, time+1});
            }
        }
    }
}