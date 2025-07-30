#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int K, R, C; int ans = -1;
vector<vector<int>> maps;
vector<vector<vector<int>>> dp;
queue<vector<int>> q;

int dr[] = {-1,0,1,0};
int dc[] = {0,1,0,-1};
int hr[] = {-2,-2, 2, 2, -1,1, -1, 1};
int hc[] = {-1,1, -1, 1, -2,-2, 2,2};

bool inRange(int r, int c) {
    return r>=0 && r<R && c>=0 && c<C;
}

int main() {
    cin >> K >> C >> R;
    maps.resize(R, vector<int>(C,0));
    dp.resize(R, vector<vector<int>>(C,vector<int>(K+1,0)));
    for(int r=0; r<R; r++) {
        for(int c=0; c<C; c++) {
            cin >> maps[r][c];
        }
    }

    q.push({0,0,0}); // 원숭이 r, c, 말이동 사용횟수
    dp[0][0][0] = 1;
    while(!q.empty()) {
        vector<int> curr = q.front(); q.pop();
        int r = curr[0]; int c = curr[1]; int horse = curr[2];

        if(r == R-1 && c == C-1) {
            ans = dp[r][c][horse] -1;
            break;
        }

        if(horse < K) {
            for(int d=0; d<8; d++) {
                int nr = r + hr[d];
                int nc = c + hc[d];

                if(inRange(nr, nc) && maps[nr][nc] != 1 && dp[nr][nc][horse + 1] == 0) {
                    dp[nr][nc][horse+1] = dp[r][c][horse] + 1;
                    q.push({nr,nc,horse+1});
                }
            }
        }

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(inRange(nr, nc) && maps[nr][nc] != 1 && dp[nr][nc][horse] == 0) {
                dp[nr][nc][horse] = dp[r][c][horse] + 1;
                q.push({nr,nc,horse});
            }
        } 
    }

    cout << ans;
    return 0;
}