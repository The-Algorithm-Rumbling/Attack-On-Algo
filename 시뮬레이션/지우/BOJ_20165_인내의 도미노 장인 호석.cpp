#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int N, M, R; int cnt;
vector<vector<int>> maps;
vector<vector<bool>> fallen;
queue<pair<int,int>> q;

int dr[] = {1,0,-1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

int EWSN(char dir) {
    if(dir == 'S') return 0;
    else if(dir == 'E') return 1;
    else if(dir == 'N') return 2;
    return 3;
}

void bfs(int x, int y, int d) {
    if(!fallen[x][y]) {
        fallen[x][y] = true;
        cnt++;
        q.push({x,y});
    }

    while(!q.empty()) {
        auto[r,c] = q.front(); q.pop();

        int len = maps[r][c];
        for(int i=0; i<len; i++) {
            int nr = r + dr[d]*i; int nc = c + dc[d]*i;
            
            if(!inRange(nr,nc)) break;
            if(!fallen[nr][nc]) {
                fallen[nr][nc] = true;
                cnt++;
                q.push({nr,nc});
            }
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M >> R;
    maps.resize(N, vector<int>(M, 0));
    fallen.resize(N, vector<bool>(M, false));

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
        }
    }

    for(int i=0; i<R; i++) {
        int r, c; cin >> r >> c; r--; c--;
        char dir; cin >> dir;
        int d = EWSN(dir);
        bfs(r,c,d);

        int x, y; cin >> x >> y; x--; y--;
        fallen[x][y] = false;
    }

    cout << cnt << "\n";
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            if(fallen[r][c]) cout << "F ";
            else cout << "S ";
        }
        cout << "\n";
    }
    
    return 0;
}