#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>

using namespace std;

int N, M;
map<int, int> ladders;
map<int, int> snakes;
queue<pair<int,int>> q;
vector<bool> vis(101, false);

int bfs() {
    vis[1] = true;
    q.push({1, 0});

    while(!q.empty()) {
        auto [curr, cnt] = q.front(); q.pop();
        if(curr == 100) return cnt;
        if(curr > 100) continue;

        for(int i=6; i>=1; i--) {
            int nxt = curr + i;
            if(ladders.find(nxt) != ladders.end()) {
                nxt = ladders[nxt];
            }
            if(snakes.find(nxt) != snakes.end()) {
                nxt = snakes[nxt];
            }

            if(!vis[nxt]) {
                vis[nxt] = true;
                q.push({nxt, cnt+1});
            }
        }
    }
    return 0;
}


int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;

    for(int i=0; i<N; i++) {
        int x, y; cin >> x >> y;
        ladders[x] = y;
    }
    for(int i=0; i<M; i++) {
        int x, y; cin >> x >> y;
        snakes[x] = y;
    }

    cout << bfs();
    
    return 0;
}