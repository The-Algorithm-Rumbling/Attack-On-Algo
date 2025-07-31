#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, M; int ans = -1;
vector<vector<int>> maps;
vector<vector<vector<int>>> dp;
queue<vector<int>> q;

int dr[] = {-1,0,1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

int main() {
    cin >> N >> M;
    maps.resize(N, vector<int>(M,0));
    dp.resize(N, vector<vector<int>>(M,vector<int>(2,0)));

    for(int r=0; r<N; r++) {
        string a; cin >> a;
        for(int c=0; c<M; c++){
            maps[r][c] = a[c] - '0';
        }
    }

    q.push({0,0,0});
    dp[0][0][0] = 1;

    while(!q.empty()) {
        vector<int> curr = q.front(); q.pop();
        int r = curr[0]; int c = curr[1];
        int crush = curr[2];


        if(r == N-1 && c == M-1) {
            ans = dp[r][c][crush];
            break;
        }

        if(crush == 0) {
            for(int d=0; d<4; d++) {
                int nr = r + dr[d]; int nc = c + dc[d];

                if(inRange(nr,nc) && maps[nr][nc] == 1 && dp[nr][nc][1] == 0) {
                    dp[nr][nc][crush+1] = dp[r][c][crush] + 1;
                    q.push({nr, nc, crush+1});
                }
            }
        }

        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];

            if(inRange(nr,nc) && maps[nr][nc] == 0 && dp[nr][nc][crush] == 0) {
                dp[nr][nc][crush] = dp[r][c][crush] + 1;
                q.push({nr, nc, crush});
            }
        }
    }

    cout << ans;
    return 0;
}