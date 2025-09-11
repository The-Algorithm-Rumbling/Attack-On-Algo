#include <iostream>
#include <vector>
#include <deque>
#include <tuple>

using namespace std;
int INF = 98765432;
int N; int sr,sc, er,ec;
vector<vector<char>> maps;
vector<vector<vector<int>>> dp;
deque<tuple<int,int,int>> dq;

int dr[] = {-1,0,1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r,int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

int bfs() {
    for(int d=0; d<4; d++) {
        dp[sr][sc][d] = 0;
        dq.push_back({sr,sc,d});
    }

    while(!dq.empty()) {
        auto[r,c,dir] = dq.front(); dq.pop_front();

        if(r==er && c==ec) {
            return dp[r][c][dir];
        }

        if(maps[r][c] == '#' || maps[r][c] == '.') {
            int nr = r + dr[dir]; int nc = c + dc[dir];
            if(inRange(nr,nc) && maps[nr][nc] != '*' && dp[nr][nc][dir] > dp[r][c][dir]) {
                dp[nr][nc][dir] = dp[r][c][dir];
                dq.push_front({nr,nc,dir});
            }
        }
        else if(maps[r][c] == '!') {
            
            for(int d=0; d<4; d++) {
                if(abs(dir-d) == 2) continue;
                int nr = r + dr[d]; int nc = c + dc[d];
                if(!inRange(nr,nc)) continue;
                if(maps[nr][nc] == '*') continue;
                
                if(d == dir && dp[nr][nc][d] > dp[r][c][dir]) {
                    dp[nr][nc][d] = dp[r][c][dir];
                    dq.push_front({nr,nc,d});
                } 
                else if (d != dir && dp[nr][nc][d] > dp[r][c][dir]+1) {
                    dp[nr][nc][d] = dp[r][c][dir] + 1;
                    dq.push_back({nr,nc,d});
                }
            }
        }
    }
    return -1;
}

int main() {
    cin >> N;
    maps.resize(N, vector<char>(N,'\0'));
    dp.resize(N, vector<vector<int>>(N, vector<int>(4,INF)));

    bool first = true;
    for(int r=0; r<N; r++) {
        string s; cin >> s;
        for(int c=0; c<N; c++) {
            maps[r][c] = s[c];
            if(maps[r][c] == '#') {
                if(first){
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