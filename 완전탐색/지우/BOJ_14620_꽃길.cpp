#include <iostream>
#include <vector>

using namespace std;
int N; 
int minCost = 98765432;
vector<vector<int>> maps;
int dr[] = {0,-1,0,1,0};
int dc[] = {0,0,1,0,-1};
vector<vector<bool>> vis;

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

int Sum(int r, int c) {
    int sum = 0;
    for(int d=0; d<5; d++) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        sum += maps[nr][nc];
    }
    return sum;
}

void Vis(int r, int c, bool isS) {
    for(int d=0; d<5; d++) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        vis[nr][nc] = isS;
    }
}

bool check(int r, int c) {
    for(int d=0; d<5; d++) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        if(!inRange(nr,nc) || vis[nr][nc]) return false;
    }
    return true;
}

void dfs(int idx, int cnt, int cost) {
    if(cnt == 3) {
        minCost = min(minCost, cost);
        return;
    }
    if(idx == N*N) return;
    
    int r = idx/N;
    int c = idx%N;

    if(check(r,c)) {
        Vis(r,c,true);
        dfs(idx+1, cnt+1, cost + Sum(r,c));
        Vis(r,c,false);
    }
    dfs(idx+1, cnt, cost);
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    maps.resize(N, vector<int>(N,0));
    vis.resize(N, vector<bool>(N,false));
    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            cin >> maps[r][c];
        }
    }

    dfs(0,0,0);
    cout << minCost;
    return 0;
}