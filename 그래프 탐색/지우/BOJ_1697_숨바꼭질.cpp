#include <iostream>
#include <queue>
#include <unordered_set>

using namespace std;

int N, K;
queue<pair<int,int>> q;
unordered_set<int> vis;

void bfs() {
    vis.insert(N);
    q.push({N,0});

    while(!q.empty()) {
        auto [X, time] = q.front(); q.pop();

        if(X == K) {
            cout << time;
            exit(0);
        }

        int nxt = 2*X;
        if(!vis.count(nxt) && nxt <= 2*K) {
            vis.insert(nxt);
            q.push({nxt, time+1});
        }

        nxt = X+1;
        if(!vis.count(nxt)) {
            vis.insert(nxt);
            q.push({nxt, time+1});
        }

        nxt = X-1;
        if(!vis.count(nxt) && nxt >= 0) {
            vis.insert(nxt);
            q.push({nxt, time+1});
        }
    }
}

int main() {
    cin >> N >> K;
    bfs();
    return 0;
}