#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int N, M, X;

int bfs(vector<vector<int>>& arr) {
    queue<int> q; vector<bool> vis(N+1, false);
    int cnt = -1;
    vis[X] = true;
    q.push(X);

    while(!q.empty()) {
        int curr = q.front(); q.pop();
        cnt++;
        for(auto nxt : arr[curr]) {
            if(!vis[nxt]) {
                vis[nxt] = true;
                q.push(nxt);
            }
        }
    }

    return cnt;
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M >> X;
    vector<vector<int>> win_lose(N+1, vector<int>(0));
    vector<vector<int>> lose_win(N+1, vector<int>(0));

    for(int i=0; i<M; i++) {
        int a, b; cin >> a >> b;
        win_lose[a].push_back(b);
        lose_win[b].push_back(a);
    }

    // 가장 높은 등수 : 나보다 앞선 사람의 수 그 다음 
    // 가장 낮은 등수 : 나에게 진 사람의 수 그 전
    cout << bfs(lose_win)+1 << " " << N-bfs(win_lose);
    
    return 0;
}