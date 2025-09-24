#include <iostream>
#include <vector>
#include <iomanip>

using namespace std;

int N; double ans = 0.0;
vector<vector<bool>> vis;
vector<double> pos;

int dr[] = {0, 0, 1, -1};
int dc[] = {1, -1, 0, 0};

void dfs(int r, int c, int moveCnt, double currPos) {
    if(moveCnt == N) {
        ans += currPos;
        return;
    }

    for(int d=0; d<4; d++) {
        int nr = r+dr[d]; int nc = c+dc[d];
        double nxtPos = currPos * pos[d];
        
        if(!vis[nr][nc]) {
            vis[nr][nc] = true;
            dfs(nr, nc, moveCnt+1, nxtPos);
            vis[nr][nc] = false;
        }
    }
}

int main() {
    cin >> N; 
    vis.resize(100, vector<bool>(100, false));
    pos.resize(4, 0);

    for(int i=0; i<4; i++) {
        int a; cin >> a;
        pos[i] = a/100.0;
    }

    vis[25][25] = true;
    dfs(25, 25, 0, 1.0);

    cout << fixed;
    cout.precision(15);
    cout << ans;
    return 0;
}