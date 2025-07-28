#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, M; int maxSum; int totalSafe;
vector<vector<int>> maps; vector<vector<bool>> vis; vector<vector<bool>> vis2;
vector<pair<int,int>> viruses; queue<vector<int>> q; 

int dr[] = {-1,0,1,0};
int dc[] = {0, 1, 0, -1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

int check() {
    int infected = 0;
    vis2 = vis;
    
    for(auto virus : viruses) {
        q.push({virus.first, virus.second});
    }
    
    while(!q.empty()) {
        vector<int> curr = q.front(); q.pop();
        int r = curr[0];
        int c = curr[1];
        infected++;

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(inRange(nr,nc) && !vis2[nr][nc] && maps[nr][nc] == 0) {
                vis2[nr][nc] = true;
                q.push({nr,nc});
            }
        }
    }
    return totalSafe - 3 + viruses.size() - infected;
}

void dfs(int wallCnt, int idx) {
    if(wallCnt == 3) {
        maxSum = max(maxSum, check());
        return;
    }

    for(int i=idx; i<N*M; i++) {
        int r = i/M;
        int c = i%M;
        
        if(maps[r][c] == 0) {
            maps[r][c] = 1;
            dfs(wallCnt+1, i+1); // 다음 칸부터 탐색 
            maps[r][c] = 0;
        }
    }
    return;
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    maps.resize(N, vector<int>(M,0)); vis.resize(N, vector<bool>(M,false)); viruses.clear();

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
            
            if(maps[r][c] == 2) {
                vis[r][c] = true; 
                viruses.push_back({r,c});
            } 
            else if(maps[r][c] == 0) {
                totalSafe++;
            }
            
        }
    }

    dfs(0, 0);
    cout << maxSum;
    return 0;
}