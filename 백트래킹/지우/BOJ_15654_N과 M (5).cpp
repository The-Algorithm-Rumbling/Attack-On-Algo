#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;
vector<int> nums;
vector<int> picks;
vector<bool> vis;

void dfs(int sIdx) {

    if(sIdx == M) {
        for(auto num : picks) {
            cout << num << " ";
        }
        cout << "\n";
        return;
    }

    for(int i=0; i<N; i++) {
        if(!vis[i]) {
            vis[i] = true;
            picks.push_back(nums[i]);
            
            dfs(sIdx+1);
            
            vis[i] = false;
            picks.pop_back();
        }
    }

    return;
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