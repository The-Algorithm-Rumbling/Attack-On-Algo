#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;
int R, C; int INF = 98765432;
int sr,sc,sd; int er, ec, ed;
vector<vector<int>> maps;
vector<vector<vector<int>>> dp;
queue<tuple<int,int,int>> q;

// 우, 좌, 하, 상
int dr[] = {0, 0, 1, -1};
int dc[] = {1, -1, 0, 0};

bool inRange(int r, int c) {
    return r>=0 && r<R && c>=0 && c<C && maps[r][c] != 1;
}

bool Turn90(int d, int dir) {
    int tmp = d + dir; // 좌-우 하-상 안돼!!
    if(tmp == 1 || tmp == 5) return false;
    return true;
}

int bfs() {
    dp[sr][sc][sd] = 0;
    q.push({sr,sc,sd});

    while(!q.empty()) {
        auto[r,c,dir] = q.front(); q.pop();

        if(r == er && c == ec && dir == ed) {
            return dp[er][ec][ed];
        }

        // 간다.
        for(int i=1; i<=3; i++) {
            int nr = r + dr[dir]*i; int nc = c + dc[dir]*i;
            if(!inRange(nr,nc)) break; // 벽 있으면 점프 불가!!!
            if(dp[nr][nc][dir] > dp[r][c][dir]+1) {
                dp[nr][nc][dir] =  dp[r][c][dir]+1;
                q.push({nr,nc,dir});
            }
        }

        // 방향을 바꾼다!
        for(int d=0; d<4; d++) {
            if(d!=dir && Turn90(d,dir)) {
                if(dp[r][c][d] > dp[r][c][dir]+1) {
                    dp[r][c][d] = dp[r][c][dir]+1;
                    q.push({r,c,d});
                }
            }
        }
    }

    return 0;
}
 
int main() {
    cin >> R >> C;
    maps.resize(R, vector<int>(C, 0));
    dp.resize(R, vector<vector<int>>(C, vector<int>(4,INF)));

    for(int r=0; r<R; r++) {
        for(int c=0; c<C; c++) {
            cin >> maps[r][c];
        }
    }

    cin >> sr >> sc >> sd;
    cin >> er >> ec >> ed;
    sr--; sc--; sd--; er--; ec--; ed--;

    cout << bfs();
    
    return 0;
}