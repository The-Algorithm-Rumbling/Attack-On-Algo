#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;
int N, M, K;
int INF = 98765432;
vector<vector<int>> maps;
vector<vector<vector<int>>> dp;
queue<tuple<int,int,int>> q;

int dr[] = {-1,0,1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r,int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

int bfs() {
    dp[0][0][K] = 1;
    q.push({0,0,K});

    while(!q.empty()) {
        auto[r,c,p] = q.front(); q.pop();
        
        if(r==N-1 && c==M-1) {
            return dp[r][c][p];
        }

        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];
            if(!inRange(nr,nc)) continue;
            
            if(maps[nr][nc] == 1 && p-1 >=0 && dp[nr][nc][p-1] > dp[r][c][p]+1) {
                dp[nr][nc][p-1] = dp[r][c][p]+1;
                q.push({nr,nc, p-1});
            }
            else if(maps[nr][nc] == 0 && dp[nr][nc][p] > dp[r][c][p]+1) {
                dp[nr][nc][p] = dp[r][c][p]+1;
                q.push({nr,nc, p});
            }
        }
    }
    return -1;
}

int main() {
    cin >> N >> M >> K;
    maps.resize(N, vector<int>(M, 0));
    dp.resize(N, vector<vector<int>>(M, vector<int>(K+1, INF)));

    for(int r=0; r<N; r++) {
        string s; cin >> s;
        for(int c=0; c<M; c++) {
            maps[r][c] = s[c]-'0';
        }
    }
    cout << bfs();
    return 0;
}