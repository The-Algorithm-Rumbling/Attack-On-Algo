#include <iostream>
#include <vector>

using namespace std;

int N,M; int maxAns = 0;
vector<vector<int>> maps;
vector<vector<bool>> vis;

int dr[] = {-1,0, -1,0, 0,1, 0,1};
int dc[] = {0,-1,  0,1, 1,0, -1,0};
void dfs(int idx, int sum);

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

void makeSum(int idx, int sum, int r,int c, int sd) {
    bool possible = true;
    int tmpSum = maps[r][c]*2;

    for(int d=sd; d<sd+2; d++) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        if(!inRange(nr, nc) || vis[nr][nc]) {
            possible = false;
        } else {
            tmpSum += maps[nr][nc];
        }
    }

    if(possible) {
        vis[r][c] = vis[r+dr[sd]][c+dc[sd]] = vis[r+dr[sd+1]][c+dc[sd+1]] = true;
        dfs(idx+1, sum+tmpSum);
        vis[r][c] = vis[r+dr[sd]][c+dc[sd]] = vis[r+dr[sd+1]][c+dc[sd+1]] = false;
    }
    
}

void dfs(int idx, int sum) {
    
    if(idx == N*M) {
        maxAns = max(maxAns, sum);
        return;
    }

    int r = idx/M;
    int c = idx%M;

    if(!vis[r][c]) {
        makeSum(idx,sum,r,c,0);
        makeSum(idx,sum,r,c,2);
        makeSum(idx,sum,r,c,4);
        makeSum(idx,sum,r,c,6);
    } 
    
    dfs(idx+1, sum); // 그냥 지나갈 경우
    
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M; // 세로 가로
    maps.resize(N, vector<int>(M, 0));
    vis.resize(N, vector<bool>(M, false));

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
        }
    }

    dfs(0,0);
    cout << maxAns;
    
    return 0;
}