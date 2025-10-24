#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;
int N, M;
int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};
vector<vector<int>> maps;
queue<tuple<int,int,int, int>> q;

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

bool checkFourDir(int r, int c) {
    for(int d=0; d<4; d++) {
        int nr = r+dr[d]; int nc = c + dc[d];
        if(inRange(nr,nc) && maps[nr][nc] == 0) {
            return true;
        } 
    }
    return false;
}

int bfs() {
    int result = 0;
    while(!q.empty()) {
        auto[r,c,d,cnt] = q.front(); q.pop();
        result = cnt;

        if(maps[r][c] == 0) {
            maps[r][c] = 2;
            q.push({r,c,d,cnt+1});
        }
        else if(!checkFourDir(r,c)) {
            int dir = (d+2)%4;
            int nr = r + dr[dir]; int nc = c + dc[dir];
            if(!inRange(nr,nc) || maps[nr][nc] == 1) return cnt;
            q.push({nr,nc,d, cnt});
        }
        else {
            while(1) {
                d = d-1;
                if(d==-1) d=3;
                int nr = r + dr[d]; int nc = c + dc[d];
                if(inRange(nr,nc) && maps[nr][nc]==0) {
                    q.push({nr, nc, d, cnt});
                    break;
                }
            }
        }
    }
    return result;
}

int main() {
    cin >> N >> M;
    maps.resize(N, vector<int>(M,0));

    int currR, currC, currDir;
    cin >> currR >> currC >> currDir;
    
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
        }
    }
    q.push({currR, currC, currDir,0});
    cout << bfs();
    
    return 0;
}