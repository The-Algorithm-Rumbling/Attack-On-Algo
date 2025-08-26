#include <iostream>
#include <vector>
#include <queue>
#include <deque>

using namespace std;

int N, K;
vector<bool> vis;
vector<int> pre;
queue<pair<int,int>> q;

void bfs() {
    vis[N] = true;
    q.push({N,0});

    while(!q.empty()) {
        auto[pos, time] = q.front(); q.pop();

        if(pos == K) {
            cout << time << "\n";
            deque<int> route;
            for(int cur = K; cur != -1; cur = pre[cur]) {
                route.push_front(cur);
            }
            for(int r : route) {
                cout << r << " ";
            }
            return;
        }

        int npos = pos * 2;
        if(npos <= 100000 && !vis[npos]) {
            vis[npos] = true;
            pre[npos] = pos;
            q.push({npos, time+1});
        }

        npos = pos +1;
        if(npos <= 100000 && !vis[npos]) {
            vis[npos] = true;
            pre[npos] = pos;
            q.push({npos, time+1});
        }
        
        npos = pos -1;
        if(npos >= 0 && !vis[npos]) {
            vis[npos] = true;
            pre[npos] = pos;
            q.push({npos, time+1});
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> K;
    vis.resize(100001, false);
    pre.assign(100001, -1);
    
    bfs();
    return 0;
}