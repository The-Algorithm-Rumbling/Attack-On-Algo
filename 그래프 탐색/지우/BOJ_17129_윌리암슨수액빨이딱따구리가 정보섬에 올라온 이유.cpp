#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n, m;
vector<vector<int>> maps;
vector<vector<int>> dp;
queue<pair<int,int>> q;

int dr[] = {1, 0, -1, 0};
int dc[] = {0, -1, 0, 1};

bool inRange(int r, int c) {
    return r>=0 && r<n && c>=0 && c<m;
}

void bfs() {
    while(!q.empty()) {
        auto [r,c] = q.front(); q.pop();
        
        if(maps[r][c] >= 3) {
            cout << "TAK" << "\n";
            cout << dp[r][c];
            exit(0);
        }

        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; 
            int nc = c + dc[d];
            
            if(inRange(nr,nc) && dp[nr][nc] == -1 && maps[nr][nc] != 1) {
                dp[nr][nc] = dp[r][c] + 1;
                q.push({nr,nc});
            }
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> n >> m;
    maps.resize(n, vector<int>(m, 0));
    dp.resize(n, vector<int>(m, -1));

    for(int r=0; r<n; r++) {
        string s; cin >> s;
        for(int c=0; c<m; c++) {
            maps[r][c] = s[c] - '0';
            if(maps[r][c] == 2) {
                dp[r][c] = 0;
                q.push({r,c});
            }
        }
    }
    
    bfs();
    cout << "NIE";
    
    return 0;
}