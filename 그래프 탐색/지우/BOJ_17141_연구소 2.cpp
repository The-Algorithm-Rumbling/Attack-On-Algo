#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;

int INF = 98765432;
int ans = INF;
int N, M; 
int bCnt;
vector<vector<int>> maps;
vector<pair<int,int>> twos;
vector<int> picks;
queue<tuple<int,int,int>> q;

int dr[] = {1,0,-1,0};
int dc[] = {0,-1,0,1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

int bfs() {
    vector<vector<int>> mps = maps;
    int maxTime = 0;
    int blankCnt = bCnt;
    int twoCnt = twos.size() - M;

    for(int idx : picks) {
        auto[r,c] = twos[idx];
        mps[r][c] = 1;
        q.push({r,c,0});
    }
    
    while(!q.empty()) {
        auto[r,c, time] = q.front(); q.pop();

        maxTime = time;
        
        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];
            if(inRange(nr,nc)) {
                if(mps[nr][nc] == 0) {
                    blankCnt--;
                    mps[nr][nc] = 1;
                    q.push({nr,nc, time+1});
                }
                else if(mps[nr][nc] == 2) {
                    twoCnt--;
                     mps[nr][nc] = 1;
                    q.push({nr,nc, time+1});
                }
            }
        }
    }
    
    if(blankCnt == 0 && twoCnt == 0) {
        return maxTime;
    }

    return INF;
}

void dfs(int start, int cnt) {
    if(cnt == M) {
        ans = min(bfs(), ans);
        return;
    }

    for(int i=start; i<twos.size(); i++) {
        picks.push_back(i);
        dfs(i+1, cnt+1);
        picks.pop_back();
    }
}


int main() {
    cin >> N >> M;
    maps.resize(N, vector<int>(N,0));

    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            cin >> maps[r][c];
            if(maps[r][c] == 2) {
                twos.push_back({r,c});
            } else if(maps[r][c] == 0){
                bCnt++;
            }
        }
    }
    
    dfs(0,0);
    
    if(ans == INF) cout << -1;
    else cout << ans;
    
    return 0;
}