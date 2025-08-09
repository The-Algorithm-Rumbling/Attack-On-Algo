#include <iostream>
#include <vector>
#include <queue>
#include <set>

using namespace std;

int N, M;
vector<vector<int>> maps; vector<vector<int>> vis;
vector<vector<int>> groupSizes; vector<vector<int>> groupNames;
queue<pair<int,int>> q; queue<pair<int,int>> q2;
int gName = 0; int maxSize = 0;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};

bool inRange(int r, int c){
    return r>=0 && r<N && c>=0 && c<M;
}

void bfs() {
    gName++;
    int cnt = 0;

    while(!q.empty()) {
        auto[r,c] = q.front(); q.pop();
        groupNames[r][c] = gName;
        cnt++;
        q2.push({r,c});

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(inRange(nr,nc) && !vis[nr][nc] && maps[nr][nc] == 1) {
                vis[nr][nc] = true;
                q.push({nr,nc});
            }
        }
    }

    while(!q2.empty()) {
        auto[r,c] = q2.front(); q2.pop();
        groupSizes[r][c] = cnt;
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    maps.resize(N, vector<int>(M,0));
    vis.resize(N, vector<int>(M,0));
    groupSizes.resize(N, vector<int>(M,0));
    groupNames.resize(N, vector<int>(M,0));

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
        }
    }
    
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            if(!vis[r][c] && maps[r][c] == 1) {
                vis[r][c] = true;
                q.push({r,c});
                bfs();
            }
        }
    }

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            
            if(maps[r][c] == 0) {
                int tmp = 0; set<int> s;
                for(int d=0; d<4; d++) {
                    int nr = r + dr[d]; int nc = c + dc[d];
                    if(inRange(nr,nc) && maps[nr][nc] == 1 && !s.count(groupNames[nr][nc])) {
                        tmp += groupSizes[nr][nc];
                        s.insert(groupNames[nr][nc]);
                    }
                }
                maxSize = max(maxSize, tmp);
            }
            
        }
    }
    
    cout << maxSize +1;
    return 0;
}