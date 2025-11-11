#include <iostream>
#include <vector>
#include <tuple>
#include <queue>

using namespace std;
int N, K;
vector<vector<vector<char>>> maps;
vector<vector<vector<bool>>> vis;
vector<vector<char>> tmp;
queue<tuple<int,int,int,int>> q;

int dr[] = {0,1,0,-1,0};
int dc[] = {0,0,1,0,-1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

int Zone(int r, int c) {
    return (r/4)*4 + (c/4);
}

pair<int,int> rotatePos(int r, int c) {
    int sr = (r/4)*4;  // 4×4 블록 기준
    int sc = (c/4)*4;
    int nr = r % 4;
    int nc = c % 4;
    return {sr+nc, sc+3-nr};
}

int bfs() {

    while(!q.empty()) {
        auto[status, r, c, time] = q.front(); q.pop();
        if(maps[status][r][c] == 'E') return time;
        int currZone = Zone(r,c);

        for(int d=0; d<5; d++) {
            int nr = r + dr[d]; int nc = c+dc[d];
            if(!inRange(nr,nc)) continue;
            
            int nxtZone = Zone(nr,nc);

            // 현재 구역이 시계방향으로 90도 회전한다.
            int nStatus = (status+1)%4;
            if(currZone != nxtZone) nStatus = 1;

            // nr, nc도 회전한 상태의 위치여야 함
            auto[nnr, nnc] = rotatePos(nr,nc);

            if(!vis[nStatus][nnr][nnc] && maps[nStatus][nnr][nnc] != '#') {
                vis[nStatus][nnr][nnc] = true;
                q.push({nStatus, nnr, nnc, time+1});
            }
        }
    }
    return -1;
}

void Rotate(int R) {
    maps[R] = maps[0];
    
    for(int round=0; round<R; round++) {
        vector<vector<char>> tmp = maps[R];
        
        // 각 4×4 블록을 회전
        for(int br=0; br<N; br+=4) {
            for(int bc=0; bc<N; bc+=4) {
                for(int r=0; r<4; r++) {
                    for(int c=0; c<4; c++) {
                        int curR = br+r;
                        int curC = bc+c;
                        int nxtR = br+c;
                        int nxtC = bc+3-r;
                        maps[R][nxtR][nxtC] = tmp[curR][curC];
                    }
                }
            }
        }
    }
}

int main() {
    cin >> K; N = 4*K;
    maps.resize(4, vector<vector<char>>(N, vector<char>(N, '\0')));
    vis.resize(4, vector<vector<bool>>(N, vector<bool>(N, false)));

    for(int r=0; r<N; r++) {
        string s; cin >> s;
        for(int c=0; c<N; c++) {
            maps[0][r][c] = s[c];
            if(maps[0][r][c] == 'S') {
                maps[0][r][c] = '.';
                vis[0][r][c] = true;
                q.push({0,r,c,0});
            }
        }
    }
    
    Rotate(1); Rotate(2); Rotate(3);
    cout << bfs();
    return 0;
}