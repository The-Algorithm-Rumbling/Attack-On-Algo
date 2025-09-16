#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;
int N, M; int cnt;
vector<vector<int>> maps;
bool vis[2001][2001][4];
queue<tuple<int,int,int>> q;
bool cooled[2001][2001];

int dr[] = {1,0,-1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

int Air(int subject, int d) {
    if(subject == 1) {
        if(d==3) return 1;
        if(d==1) return 3;
        return d;
    }

    if(subject == 2) {
        if(d==0) return 2;
        if(d==2) return 0;
        return d;
    }

    if(subject == 3) {
        if(d==0) return 3;
        if(d==3) return 0;
        if(d==2) return 1;
        if(d==1) return 2;
    }

    if(subject == 4) {
        if(d==0) return 1;
        if(d==1) return 0;
        if(d==2) return 3;
        if(d==3) return 2;
    }

    return d;
}

void bfs() {
    while(!q.empty()) {
        auto[r,c,d] = q.front(); q.pop();
        if(!cooled[r][c]) {
            cooled[r][c] = true;
            cnt++;
        }
        
        d = Air(maps[r][c], d);
        int nr = r + dr[d]; int nc = c + dc[d];
        if(!inRange(nr,nc) || vis[nr][nc][d]) continue;
        vis[nr][nc][d] = true;
        q.push({nr, nc, d});
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    maps.resize(N, vector<int>(M,0));

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
            if(maps[r][c] == 9) {
                cooled[r][c] = true;
                cnt++;
                for(int d=0; d<4; d++) {
                    vis[r][c][d] = true;
                    q.push({r,c,d});
                }
            }
        }
    }

    bfs();
    cout << cnt;
    return 0;
}