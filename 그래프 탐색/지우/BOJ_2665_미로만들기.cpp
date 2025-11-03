#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;
int n;
int INF = 98765432;
int ans = INF;
vector<vector<int>> maps;
vector<vector<vector<int>>> dp;
queue<tuple<int,int,int>> q;

int dr[] = {1,0,-1,0};
int dc[] = {0,-1,0,1};

bool inRange(int r,int c) {
    return r>=0 && r<n && c>=0 && c<n;
}

void bfs() {
    dp[0][0][0] = 0;
    q.push({0,0,0});

    while(!q.empty()) {
        auto[r,c,cnt] = q.front(); q.pop();
        if(r == n-1 && c == n-1) {
            ans = min(ans, cnt);
            continue;
        }

        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];
            if(!inRange(nr,nc)) continue;
            if(maps[nr][nc] == 1 && dp[nr][nc][cnt] > dp[r][c][cnt] + 1) {
                dp[nr][nc][cnt] = dp[r][c][cnt] + 1;
                q.push({nr,nc, cnt});
            } else if(maps[nr][nc] == 0 && cnt+1 < n*n && dp[nr][nc][cnt+1] > dp[r][c][cnt] + 1) {
                dp[nr][nc][cnt+1] = dp[r][c][cnt] + 1;
                q.push({nr,nc, cnt+1});
            }
        }
    }
    
}

int main() {
    cin >> n;
    maps.resize(n, vector<int>(n,0));
    dp.resize(n, vector<vector<int>>(n, vector<int>(n*n, INF)));

    for(int r=0; r<n; r++) {
        string s; cin >> s;
        for(int c=0; c<n; c++) {
            maps[r][c] = s[c]-'0';
        }
    }

    bfs();
    cout << ans;
    return 0;
}