#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int N, M; int ans = 98765432;
vector<vector<int>> maps;
vector<pair<int,int>> cctv;
vector<vector<bool>> vis;

int dr[] = {1,0,-1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

int cntBlind() {
    int cnt = 0;
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            if(maps[r][c] == 0) cnt++;
        }
    }
    return cnt;
}

void check(int r, int c, int d, int num, int out) {
    int nr = r+dr[d]; int nc = c+dc[d];
    while(inRange(nr,nc)) {
        if(maps[nr][nc] == 6) break;
        else if(maps[nr][nc] == out) maps[nr][nc] = num;

        nr += dr[d]; nc += dc[d];
    }
}

void dfs(int idx, int num) {
    if(idx == cctv.size()) {
        ans = min(cntBlind(), ans);
        return;
    }
    
    auto[r,c] = cctv[idx];
    int t = maps[r][c];

    if(t == 1){
        for(int d=0; d<4; d++) {
            check(r,c,d,num,0);
            dfs(idx+1, num+1);
            check(r,c,d,0, num);
        }
    }
    else if(t==2) {
        for(int d=0; d<2; d++) {
            check(r,c,d,num,0);
            check(r,c,d+2,num,0);
            dfs(idx+1, num+1);
            check(r,c,d,0,num);
            check(r,c,d+2,0,num);
        }
    }
    else if(t==3) {
        for(int d=0; d<4; d++) {
            int nd = (d+1)%4;
            check(r,c,d,num,0);
            check(r,c,nd,num,0);
            dfs(idx+1, num+1);
            check(r,c,d,0,num);
            check(r,c,nd,0,num);
        }
    }
    else if(t==4) {
        for(int d=0; d<4; d++) {
            int nd = (d+2)%4; int nd2 = (d+3)%4;
            check(r,c,d,num,0);
            check(r,c,nd,num,0); check(r,c,nd2,num,0);
            dfs(idx+1, num+1);
            check(r,c,d,0,num);
            check(r,c,nd,0,num); check(r,c,nd2,0,num);
        }
    } 
    else if(t==5) {
        for(int d=0; d<4; d++) {
            check(r,c,d,num,0);
        }
        dfs(idx+1, num+1);
        for(int d=0; d<4; d++) {
            check(r,c,d,0,num);
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    maps.resize(N, vector<int>(M, 0));
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
            if(maps[r][c] != 0 && maps[r][c] != 6) {
                cctv.push_back({r,c});
            }
        }
    }

    dfs(0, 7);
    cout << ans;
    return 0;
}