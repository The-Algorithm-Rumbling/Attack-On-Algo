#include <iostream>
#include <vector>
#include <tuple>
#include <deque>
using namespace std;

int C, R;
int sr, sc, er, ec;
int INF = 98765432;
vector<vector<char>> maps;
vector<vector<vector<int>>> dp;
deque<tuple<int,int,int,int>> dq;

int dr[] = {1,0,-1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r, int c) {
    return r>=0 && r<R && c>=0 && c<C;
}

int bfs() {
    for(int d=0; d<4; d++) {
        dp[sr][sc][d] = 0;
        dq.push_front({sr,sc,0,d});
    }
    while(!dq.empty()) {
        auto[r,c,cnt,dir] = dq.front(); dq.pop_front();
        if(r==er && c==ec) {
            return cnt;
        }
        maps[r][c] = cnt+'0';

        for(int d=0; d<4; d++) {
            if(abs(d-dir)==2) continue;

            int nr = r+dr[d]; int nc = c + dc[d];
            if(!inRange(nr,nc)) continue;
            if(maps[nr][nc] == '*') continue;

            if(d==dir && dp[nr][nc][d] > dp[r][c][d]) {
                dp[nr][nc][d] = dp[r][c][d];
                dq.push_front({nr,nc,cnt,d});
            }
            else if(d!=dir && dp[nr][nc][d] > dp[r][c][dir]+1) {
                dp[nr][nc][d] = dp[r][c][dir]+1;
                dq.push_back({nr,nc,cnt+1,d});
            }
        }
    }
    return -1;
}

int main() {
    cin >> C >> R;
    maps.resize(R, vector<char>(C, '\0'));
    dp.resize(R, vector<vector<int>>(C, vector<int>(4,INF)));
    bool first = true;
    for(int r=0; r<R; r++) {
        string s; cin >> s;
        for(int c=0; c<C; c++) {
            maps[r][c] = s[c];
            if(maps[r][c] == 'C') {
                if(first) {
                    sr = r; sc = c;
                    first = false;
                } else {
                    er = r; ec = c;
                }
            }
        }
    }
    cout << bfs();
    return 0;
}