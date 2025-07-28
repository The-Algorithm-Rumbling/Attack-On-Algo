#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, L, R; int ans;
bool moved;
vector<vector<int>> A; vector<vector<int>> dp;
queue<pair<int,int>> q; queue<pair<int,int>> q2;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<N;    
}

void bfs() {
    int sum = 0;
    while(!q.empty()) {
        pair curr = q.front(); q.pop();
        int r= curr.first; int c= curr.second;
        sum += A[r][c]; q2.push({r,c});

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
 
            if(inRange(nr, nc) && dp[nr][nc] == 0 && abs(A[r][c] - A[nr][nc]) >= L && abs(A[r][c] - A[nr][nc]) <= R) {
                moved = true;
                dp[nr][nc]  = dp[r][c];
                q.push({nr, nc});
            }
        }
    }

    int newVal = sum/q2.size();

    while(!q2.empty()) {
        pair curr = q2.front(); q2.pop();
        int r= curr.first; int c= curr.second;
    
        A[r][c] = newVal;
    }
}

void makingIsland() {
    while(1) {
        dp.assign(N, vector<int>(N,0));
        moved = false;
        
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(dp[r][c] == 0) {
                    dp[r][c] = 1;
                    q.push({r,c});
                    bfs();
                }
            }
        }
        if(!moved) break;
        ans++;
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> L >> R; A.resize(N, vector<int>(N,0));

    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            cin >> A[r][c];
        }
    }

    makingIsland();
    cout << ans;
    
    return 0;
}