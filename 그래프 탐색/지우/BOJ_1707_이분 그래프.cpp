#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int V, E;
vector<vector<int>> adj;
vector<int> dp;

bool bfs(int start) {
    queue<pair<int,int>> q;
    dp[start] = 1;
    q.push({start, 1});

    while(!q.empty()) {
        auto[curr, color] = q.front(); q.pop();
        // cout << curr << " " << color << "\n";

        for(auto nxt : adj[curr]) {
            int nxtColor = 0-color;

            if(dp[nxt] == 0) {
                dp[nxt] = nxtColor;
                q.push({nxt, nxtColor});
            } else {
                if(dp[nxt] == color) { // 다음은 지금이랑 색이 같으면 안됨
                    return false;
                }
            }
        }
    }
    return true;
}

int main() {
    int T; cin >> T;
    while(T--) {
        cin >> V >> E;
        adj.assign(V+1, vector<int>(0));
        dp.assign(V+1,0);

        for(int i=0; i<E; i++) {
            int a, b;
            cin >> a >> b;
    
            adj[a].push_back(b);
            adj[b].push_back(a);
        }

        bool isGraph = true;
        
        for(int i=1; i<=V; i++) {
            if(dp[i] == 0) {
                isGraph = bfs(i);
                if(!isGraph) break;
            }
            
        }

        if(isGraph == 0) cout << "NO \n";
        else cout << "YES \n";   
    }
    return 0;
}