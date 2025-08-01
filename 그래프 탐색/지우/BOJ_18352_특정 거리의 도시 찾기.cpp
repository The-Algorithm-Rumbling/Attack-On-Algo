#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <set>

using namespace std;

int N, M, K, X;
vector<vector<int>> adj;
vector<int> dp;
queue<int> q;
set<int> s;

void bfs() {
    dp[X] = 1; K++;
    q.push(X);

    while(!q.empty()) {
        int curr = q.front(); q.pop();

        if(dp[curr] == K) s.insert(curr);
        else if(dp[curr] > K) continue;

        for(auto nxt : adj[curr]) {
            if(dp[nxt] == 0) {
                dp[nxt] = dp[curr] + 1;
                q.push(nxt);
            }
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); 
    ios::sync_with_stdio(0);
    cin >> N >> M >> K >> X;
    adj.resize(N+1,vector<int>(0));
    dp.resize(N+1, 0);

    for(int m=0; m<M; m++) {
        int a, b;
        cin >> a >> b;

        adj[a].push_back(b); // 단방향
    }

    bfs();

    if(s.size() == 0) {
        cout << -1;
    } else {
        for(auto i : s) {
            cout << i << "\n";
        }
    }
    
    return 0;
}