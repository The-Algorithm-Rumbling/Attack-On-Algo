#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, H, D;
vector<vector<char>> maps;
vector<vector<int>> dp;
queue<vector<int>> q;
int ans = -1;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, -1, 0, 1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

void bfs() {
    while(!q.empty()) {
        
        vector<int> curr = q.front(); q.pop();
        int r = curr[0]; 
        int c = curr[1]; 
        int umb = curr[2]; // 우산 내구도
        int power = curr[3]; 
        int move = curr[4];
        
        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(!inRange(nr, nc)) continue;

            int nUmb = umb;
            int nPower = power;

            if(maps[nr][nc] == 'E') {
                ans = move + 1;
                return;
            }

            if(maps[nr][nc] == 'U') {
                nUmb = D-1; // 우산 새로 주워도 비 때문에 깎임 (예제3. 체력 1이 남는다고 써있음)
            }
            
            if(maps[nr][nc] == '.') {
                if(umb > 0) {
                    nUmb--;
                } else {
                    nPower--;
                }
            }

            if(nPower <= 0) continue;
            int total = nPower + nUmb;
            if(dp[nr][nc] < total) {
                dp[nr][nc] = total;
                q.push({nr, nc, nUmb, nPower, move+1});
            }
            
        }
    }

    return;
}


int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> H >> D;
    maps.resize(N,vector<char>(N,'\0'));
    dp.resize(N,vector<int>(N,-1));

    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            cin >> maps[r][c];
            if(maps[r][c] == 'S') {
                dp[r][c] = H;
                q.push({r,c, 0, H, 0});
            }
        }
    }

      
    bfs();
    cout << ans;
    return 0;
}