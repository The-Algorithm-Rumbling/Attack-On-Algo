#include <iostream>
#include <vector>
#include <queue>
#include <map>

using namespace std;

int N, M;
int ans = 101;
map<int,int> m;
vector<bool> vis;
queue<pair<int,int>> q;

void bfs () {
    vis[1] = true;
    q.push({1, 0});

    while(!q.empty()) {
        auto [pos, cnt] = q.front(); q.pop();

        if(pos == 100) {
            ans = cnt;
            return;
        }
        
        if(pos > 100) continue;

        for(int i=1; i<=6; i++) {
            int nxt = pos + i;

            if(vis[nxt]) continue;
            
            if(m.find(nxt) != m.end()) {
                q.push({m[nxt], cnt+1});
            } else {
                vis[nxt] = true;
                q.push({nxt, cnt+1});
            }
        }
    }
}

int main() {
    cin >> N >> M;
    vis.resize(101,false);
    for(int i=0; i<N; i++) {
        int x, y;
        cin >> x >> y;

        m.insert({x, y});
    }

    for(int i=0; i<M; i++) {
        int u, v;
        cin >> u >> v;
        
        m.insert({u, v});
    }

    bfs();
    cout << ans;
    return 0;
}