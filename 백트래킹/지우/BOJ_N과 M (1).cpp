#include <iostream>
#include <vector>

using namespace std;

int N, M;
vector<bool> vis;

void dfs(vector<int>& ans) {
    if(ans.size() == M) {
        for(auto c : ans) {
            cout << c << " ";
        }
        cout << "\n";
        return;
    }

    for(int i=1; i<=N; i++) {
        if(vis[i]) continue;
        
        vis[i] = true; ans.push_back(i);
        dfs(ans);
        
        vis[i] = false; ans.pop_back();
    }

    return;
}

int main() {
    cin >> N >> M; 
    vis.resize(N+1, false); vector<int> ans(0);
    
    dfs(ans);
    return 0;
}