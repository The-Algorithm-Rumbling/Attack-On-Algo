#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

int N; int ans = INT_MAX; // 제일 크게 만들어주기 백만*N보다 더 크게!!
vector<vector<int>> adj;
vector<bool> vis;

int dfs(int start, int depth, int curr, int sum) {
    if(depth == N-1) {
        if(adj[curr][start] > 0) {
            return sum + adj[curr][start];
        } else {
            return INT_MAX;
        } 
    }

    int tmpSum = INT_MAX;

    for(int nxt=0; nxt<N; nxt++) {
        if(!vis[nxt] && adj[curr][nxt] > 0) {
            vis[nxt] = true;
            // 해당 경로를 따라 끝까지 갔다가 돌아오는 비용 중 최소 저장
            tmpSum = min(tmpSum, dfs(start, depth+1, nxt, sum+adj[curr][nxt]));
            vis[nxt] = false;
        }
    }

    return tmpSum;
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    adj.resize(N, vector<int>(N,0));

    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            cin >> adj[r][c];
        }
    }
 
    for(int i=0; i<N; i++) {
        vis.assign(N,false);
        vis[i] = true;
        ans = min(ans, dfs(i,0,i,0));
    }

    cout << ans;

}