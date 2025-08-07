#include <iostream>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

int N, M;
vector<int> nums;
vector<int> picks;
vector<bool> vis;
set<string> sets;

void dfs(int pIdx) {
    if(pIdx == M) {
        string s = "";
        for(int i=0; i<M; i++) {
            s += to_string(picks[i]) + " ";
        }
        if(!sets.count(s)) {
            sets.insert(s);
            cout << s << "\n";
        }
        return;
    }
    
    for(int i=0; i<N; i++) {
        if(vis[i]) continue;
        vis[i] = true;
        picks.push_back(nums[i]);
        dfs(pIdx+1);
        vis[i] = false;
        picks.pop_back();
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    nums.resize(N,0);
    vis.resize(N,false);
    for(int i=0; i<N; i++) {
        cin >> nums[i];
    }
    sort(nums.begin(), nums.end());
    dfs(0);
    
    return 0;
}