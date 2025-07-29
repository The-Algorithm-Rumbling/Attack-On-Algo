#include <iostream>
#include <vector>

using namespace std;

int N, M;
vector<bool> vis;

void dfs(int idx, vector<int>& ans) {
    if(ans.size() == M) {
        for(auto c : ans) {
            cout << c << " ";
        }
        cout << "\n";
        return;
    }

    for(int i=idx; i<=N; i++) {
        ans.push_back(i);
        dfs(i+1, ans);
        ans.pop_back();
    }

    return;
}

int main() {
    cin >> N >> M; 
    vector<int> ans(0);
    
    dfs(1,ans);
    return 0;
}