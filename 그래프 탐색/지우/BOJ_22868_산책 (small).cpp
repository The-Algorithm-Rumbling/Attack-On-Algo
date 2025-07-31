#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int N, M; int ans;
int startN; int endN;
bool goBack = false;
vector<vector<int>> adj;
vector<int> vis;
vector<int> p;
queue<int> q;

void countRoutes(int s, int e) {
    for(int i = s; i != e; i = p[i] ) {
        vis[i] = true;
        ans++;
    }
}

void bfs() {
    while(!q.empty()) {
        int curr = q.front(); q.pop();

        if(curr == endN && !goBack) {
            vis.assign(N+1,false);
            q = queue<int>();
            goBack = true;
            
            countRoutes(endN, startN);
            
            q.push(endN);
        }

        if(curr == startN && goBack) {
            countRoutes(startN, endN);
            break;
        }

        for(auto nxt : adj[curr]) {
            if(!vis[nxt]) {
                vis[nxt] = true;
                p[nxt] = curr;
                q.push(nxt);
            }
        }
    }
}

int main() {
    cin >> N >> M;
    adj.resize(N+1, vector<int>(0));
    vis.resize(N+1,false);
    p.resize(N+1,0);

    for(int m=1; m<=M; m++) {
        int a; int b; cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }
    for(int i=1; i<=N; i++) {
        sort(adj[i].begin(), adj[i].end());
    }
    cin >> startN >> endN;

    q.push(startN); vis[startN] = true;
    bfs();

    cout << ans;
       
    
    return 0;
}