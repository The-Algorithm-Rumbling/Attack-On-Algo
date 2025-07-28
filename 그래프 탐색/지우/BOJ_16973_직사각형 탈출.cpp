#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, M, H, W, sr, sc, fr, fc;
int ans;
vector<vector<int>> maps;
vector<vector<int>> prefix;
vector<vector<int>> dp;
queue<pair<int,int>> q;

int dr[] = {-1,0,1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r, int c) {
    int endR = r+H-1; int endC = c+W-1;
    return r>=1 && r<=N && endR >=1 && endR <=N && c>=1 && c<=M && endC >=1 && endC<=M;
}

bool possible(int r, int c) {
    int endR = r+H-1; int endC = c+W-1;
    int sum = prefix[endR][endC] - prefix[r-1][endC] - prefix[endR][c-1] + prefix[r-1][c-1];
    if(sum == 0) return true;
    return false;
}

void bfs() {
    while(!q.empty()) {
        pair<int, int> curr = q.front(); q.pop();
        int r = curr.first;
        int c = curr.second;

        if(r==fr && c==fc) {
            ans = dp[r][c];
            return;
        }

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(inRange(nr, nc) && dp[nr][nc] == 0 && possible(nr, nc)) {
                dp[nr][nc] = dp[r][c] + 1;
                q.push({nr,nc});
            }
        }
    }

    return;
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    maps.resize(N+1, vector<int>(M+1, 0));
    dp.resize(N+1, vector<int>(M+1, 0));
    prefix.resize(N+1, vector<int>(M+1, 0));
    for(int r=1; r<=N; r++) {
        for(int c=1; c<=M; c++) {
            int in; cin >> in;
            maps[r][c] = in;
        }
    }

    for(int r=1; r<=N; r++) {
        for(int c=1; c<=M; c++) {
            prefix[r][c] = maps[r][c] + prefix[r-1][c] + prefix[r][c-1] - prefix[r-1][c-1];
        }
    }
    
    cin >> H >> W >> sr >> sc >> fr >> fc;
    dp[sr][sc] = 1; q.push({sr,sc}); 

    bfs();
    cout << ans-1;
    return 0;
}