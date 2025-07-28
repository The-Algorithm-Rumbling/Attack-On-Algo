#include <iostream>
#include <vector>

using namespace std;

int n;
vector<vector<pair<int,int>>> adj;
vector<bool> vis;
int maxNode; int maxSum;

void dfs(int node, int sum) {
    if(maxSum < sum) {
        maxSum = sum;
        maxNode = node;
    }

    for(auto child : adj[node]) {
        if(!vis[child.first]) {
            vis[child.first] = true;
            dfs(child.first, sum+child.second); 
        }
    }

    return;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);cout.tie(0);
    
    cin >> n;
    adj.resize(n+1, vector<pair<int,int>>(0));
    vis.resize(n+1, false);

    for(int i=0; i<n-1; i++) {
        int a, b, w; cin >> a >> b >> w;
        adj[a].push_back({b,w});
        adj[b].push_back({a,w});
    }

    vis[1] = true; 
    dfs(1,0);
    
    vis.assign(n+1, false);
    
    vis[maxNode] = true;
    dfs(maxNode,0);

    cout << maxSum;
    return 0;
}