#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int N, M, T;
int AR1, AR2;
vector<vector<int>> maps;
queue<pair<int,int>> q;

//우하좌상
int dr[] = {0,1,0,-1};
int dc[] = {1,0,-1,0};
//하우상좌
int dr1[] = {1,0,-1,0};
int dc1[] = {0,1,0,-1};

bool inRange(int r, int c, int limit1, int limit2) {
    return r>=limit1 && r<=limit2 && c >= 0 && c < M;
}

void Move2() {
    int num = maps[AR2][0];
    int d = 0;
    int r = AR2; int c = 0;
    
    while(d < 4) {
        int nr = r + dr1[d]; int nc = c + dc1[d];
        if(nr == AR2 && nc == 0) break;

        if(inRange(nr,nc,AR2,N-1)) {
            maps[r][c] = maps[nr][nc];           
            r = nr; c = nc;
        }
        else {
            d++;
        }
    }
    maps[AR2][0] = num;
    maps[AR2][1] = 0;
}

void Move1() {
    int num = maps[0][0];
    int d = 0;
    int r = 0; int c = 0;
    
    while(d < 4) {
        int nr = r + dr[d]; int nc = c + dc[d];
        if(nr == 0 && nc == 0) break;

        if(inRange(nr,nc,0,AR1)) {
            if(maps[nr][nc] != -1) maps[r][c] = maps[nr][nc];
            else maps[r][c] = 0;
            
            r = nr; c = nc;
        }
        else {
            d++;
        }
    }
    maps[1][0] = num;
    maps[AR1][0] = -1;
}

void Spread() {
    int size = q.size();
    vector<vector<int>> tmp(N, vector<int>(M,0));
    
    while(size--) {
        auto [r,c] = q.front(); q.pop();
        int num = maps[r][c];
        int a = num/5;
        int cnt = 0;

        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];
            if(inRange(nr,nc,0,N-1) && maps[nr][nc] != -1) {
                tmp[nr][nc] += a;
                cnt++;
            }
        }

        maps[r][c] = num - a*cnt;
    }

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            maps[r][c] += tmp[r][c];
        }
    }
}

int main() {
    cin >> N >> M >> T;
    maps.resize(N, vector<int>(M, 0));

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
            if(maps[r][c]==-1) {
                if(AR1 == 0) AR1 = r;
                else AR2 = r;
            }
            else if(maps[r][c] > 0) {
                q.push({r,c});
            }
        }
    }

    while(T--) {
        Spread();
        Move1();
        Move2();

        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(maps[r][c] > 0) q.push({r,c});
            }
        }
    }

    int sum = 0;
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            if(maps[r][c] > 0) sum += maps[r][c];
        }
    }
    cout << sum;
    return 0;
}