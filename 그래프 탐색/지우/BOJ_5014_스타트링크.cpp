#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <unordered_set>

using namespace std;

int F, S, G, U, D;
int ans = -1;
queue<pair<int,int>> q;
unordered_set<int> vis;

void bfs() {
    vis.insert(S);
    q.push({S,0});

    while(!q.empty()) {
        auto [curr, cnt] = q.front(); q.pop();
        if(curr == G) {
            ans = cnt;
            return;
        }

        int nxt = curr + U;
        if(nxt > curr && nxt<= F && !vis.count(nxt)) {
            vis.insert(nxt);
            q.push({nxt, cnt+1});
        }
    
        nxt = curr - D;
        if(nxt < curr && nxt >= 1 && !vis.count(nxt)) {
            vis.insert(nxt);
            q.push({nxt, cnt+1});
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    
    cin >> F >> S >> G >> U >> D;
    bfs();
    
    if(ans == -1) {
        cout << "use the stairs";
    } else {
        cout << ans;
    }
    
    return 0;
}