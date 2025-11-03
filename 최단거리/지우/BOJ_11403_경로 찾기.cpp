#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int N;
vector<vector<int>> adj;
vector<bool> vis;

int bfs(int start, int end) {
    queue<int> q;
    q.push(start);
    
    while(!q.empty()) {
        int curr = q.front(); q.pop();
        if(vis[curr] && curr ==  end) return 1;

        for(int c=0; c<N; c++) {
            if(adj[curr][c] == 1 && !vis[c]) {
                vis[c] = true;
                q.push(c);
            }
        }
    }

    return 0;
}

int main() {
    cin >> N;
    adj.resize(N, vector<int>(N,0));

    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            cin >> adj[r][c];
        }
    }

    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            vis.assign(N, false);
            cout << bfs(r,c) << " ";
        }
        cout << "\n";
    }
    
    return 0;
}