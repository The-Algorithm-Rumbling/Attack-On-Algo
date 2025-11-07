#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;
int N = 5; int INF = 98765432;
int answer = INF;
vector<vector<vector<int>>> maps;
vector<vector<vector<int>>> cmaps;
vector<vector<vector<int>>> tmaps;
vector<vector<vector<bool>>> vis;
vector<int> turns(5, 0);
vector<int> picks;
vector<bool> used;

int dx[] = {1,-1,0,0,0,0};
int dy[] = {0,0,1,-1,0,0};
int dh[] = {0,0,0,0, 1,-1};

bool inRange(int h, int r, int c) {
    return h>=0 && h<N && r>=0 && r<N && c>=0 && c<N;
}

int bfs() {
    if(cmaps[0][0][0] == 0 || cmaps[4][4][4] == 0) return INF;
        
    queue<tuple<int,int,int,int>> q;
    vis.assign(5, vector<vector<bool>>(N, vector<bool>(N,false)));
    vis[0][0][0] = true;
    q.push({0,0,0,0});

    while(!q.empty()) {
        auto[h,x,y,cnt] = q.front(); q.pop();

        if(h==4 && x==4 && y==4) return cnt;
        
        for(int d=0; d<6; d++) {
            int nh = h+dh[d]; int nx = x+dx[d]; int ny = y+dy[d];
            if(inRange(nh, nx, ny) && !vis[nh][nx][ny] && cmaps[nh][nx][ny] == 1) {
                vis[nh][nx][ny] = true;
                q.push({nh, nx, ny, cnt+1});
            }
        }
    }
    return INF;
}

void rotate(int mazeNum, int rCnt) {
    if(rCnt == 0) {
        cmaps[mazeNum]= tmaps[mazeNum];
        return;
    }
    else if(rCnt == 1) { // 90도
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                cmaps[mazeNum][c][N-1-r] = tmaps[mazeNum][r][c];
            }
        }
    }
    else if(rCnt == 2) { // 180도
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                cmaps[mazeNum][N-1-r][N-1-c] = tmaps[mazeNum][r][c];
            }
        } 
    }
    else if(rCnt == 3) { // 270도 
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                cmaps[mazeNum][N-1-c][r] = tmaps[mazeNum][r][c];
            }
        }        
    }
}

void dfs(int idx) {
    if(idx == 5) {
        cmaps.assign(5, vector<vector<int>>(N, vector<int>(N,0)));
        for(int t=0; t<5; t++) {
            rotate(t, turns[t]);
        }
        answer = min(answer, bfs());
        return;
    }
    
    for(int d=0; d<4; d++) {
        turns[idx] = d;
        dfs(idx+1);
    }
}

void dfsOrder(int cnt) {
    if(cnt == 5) {
        tmaps.assign(5, vector<vector<int>>(N, vector<int>(N,0)));
        for(int i=0; i<5; i++) {
            int num = picks[i];
            tmaps[num] = maps[i];
        }
        dfs(0);
        return;
    }

    for(int i=0; i<5; i++) {
        if(!used[i]) {
            used[i] = true; picks.push_back(i);
            dfsOrder(cnt+1);
            used[i] = false; picks.pop_back();
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    maps.resize(5, vector<vector<int>>(N, vector<int>(N,0)));

    for(int t=0; t<5; t++) {
        for(int r=0; r<5; r++) {
            for(int c=0; c<5; c++) {
                cin >> maps[t][r][c];
            }
        }
    }

    // for(int t=0; t<5; t++) {
    //     for(int r=0; r<5; r++) {
    //         for(int c=0; c<5; c++) {
    //             cout << maps[t][r][c] << " ";
    //         }
    //         cout << "\n";
    //     }
    //     cout << "\n";
    // }

    used.resize(N, false);
    dfsOrder(0);
    
    if(answer == INF) cout << -1;
    else cout << answer;
    
    return 0;
}