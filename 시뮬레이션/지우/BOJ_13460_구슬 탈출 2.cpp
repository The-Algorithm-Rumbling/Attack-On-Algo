#include <iostream>
#include <vector>
#include <tuple>
#include <queue>

using namespace std;
int N, M;
int dr[] = {1, 0, -1, 0};
int dc[] = {0, 1, 0, -1};
vector<vector<char>> maps;
vector<vector<vector<vector<bool>>>> vis;
queue<tuple<int,int,int,int,int>> q;

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

tuple<int,int,bool> Roll(int r,int c,int br, int bc, int d) {
    int nr = 0; int nc = 0;
    while(1) {
        nr = r + dr[d]; nc = c + dc[d];
        if(inRange(nr,nc) && (maps[nr][nc] == '.' || maps[nr][nc] == 'O') && !(nr == br && nc == bc)) {
            if(maps[nr][nc] == 'O') return {nr,nc,true};
            else {
                r = nr; c = nc;
            }
        }
        else return {r,c,false};
    }
    return {nr,nc,false};
    
}

bool RFirst(int r, int c, int br, int bc, int dir) {
    if(dir == 0) { // 하 
        return r > br;
    } else if(dir == 1) { // 우
        return c > bc;
    } else if(dir == 2) { // 상
        return r < br;
    }
    return c < bc; //좌 
}

int bfs() {
    while(!q.empty()) {
        auto [r,c,cnt,br,bc] = q.front(); q.pop();
        if(cnt == 10) continue;

        for(int d=0; d<4; d++) {
            // d 방향으로 돌려볼까? - 누구 먼저?
            if(RFirst(r,c,br,bc,d)) {
                auto[nr, nc, isS] = Roll(r,c,br,bc,d);
                if(isS) {
                    nr = -1; nc = -1;
                }
                auto[nbr, nbc, isF] = Roll(br,bc,nr,nc,d);

                
                if(isS && !isF) return cnt+1;
                else if(isF == true) continue;
                else if(!vis[nr][nc][nbr][nbc]){
                    vis[nr][nc][nbr][nbc] = true;
                    q.push({nr,nc,cnt+1,nbr,nbc});
                }
                
            } else {
                auto[nbr, nbc, isF] = Roll(br,bc,r,c,d);
                if(isF == true) continue;
                auto[nr, nc, isS] = Roll(r,c,nbr,nbc,d);
                
                if(isS && !isF) return cnt+1;
                else if(!vis[nr][nc][nbr][nbc]){
                    vis[nr][nc][nbr][nbc] = true;
                    q.push({nr,nc,cnt+1,nbr,nbc});
                }
            }
        }
    }

    return -1;
}

int main() {
    cin >> N >> M;
    maps.resize(N, vector<char>(M,'\0'));
    vis.resize(N, vector<vector<vector<bool>>>(M, vector<vector<bool>>(N, vector<bool>(M, false))));

    int rr = 0; int rc = 0;
    int br = 0; int bc = 0;
    
    for(int r=0; r<N; r++) {
        string s; cin >> s;
        for(int c=0; c<M; c++) {
            maps[r][c] = s[c];
            if(maps[r][c] == 'R'){
                rr = r; rc =c;
                maps[r][c] = '.';
            }
            if(maps[r][c] == 'B') {
                br = r; bc =c;
                maps[r][c] = '.';
            }
        }
    }
    
    q.push({rr,rc,0,br,bc});
    vis[rr][rc][br][bc] = true;
    cout << bfs();
    
    return 0;
}