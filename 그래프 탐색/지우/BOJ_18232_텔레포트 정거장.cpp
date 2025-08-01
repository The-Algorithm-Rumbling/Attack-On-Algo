#include <iostream>
#include <vector>
#include <queue>
#include <unordered_set>

using namespace std;

long long N, M, S, E;
vector<vector<long long>> adj;
queue<pair<long long,long long>> q; // 정점, 시간
unordered_set<long long> vis;

long long bfs() {
    vis.insert(S);
    q.push({S,0});

    while(!q.empty()) {
        auto[curr, time] = q.front(); q.pop();

        if(curr == E) {
            return time;
        }

        for(auto nxt : adj[curr]) {
            if(!vis.count(nxt)) {
                vis.insert(nxt);
                q.push({nxt, time+1});
            }
        }

        if(curr-1 > 0 && !vis.count(curr-1)) {
            vis.insert(curr-1);
            q.push({curr-1, time+1});
        }

        if(curr+1 <= N &&!vis.count(curr+1)) {
            vis.insert(curr+1);
            q.push({curr+1, time+1});
        }
    }

    return -1;
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M >> S >> E;
    adj.resize(N+1, vector<long long>(0));

    for(int m=0; m<M; m++) {
        long long a, b;
        cin >> a >> b;

        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    cout << bfs();
    
    return 0;
}