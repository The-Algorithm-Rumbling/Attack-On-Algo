#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;
vector<int> nums;
vector<int> picks;
vector<bool> vis;

void dfs(int pIdx) {
    if(pIdx == M) {
        for(int i=0; i<M; i++) {
            cout << picks[i] << " ";
        }
        cout << "\n";
        return;
    }

    int pre = -1; // 같은 레벨에서 같은 숫자를 중복으로 사용하지 않도록 이전 값 저장!
    
    for(int i=0; i<N; i++) {
        if(vis[i] || pre == nums[i]) continue;
        vis[i] = true;
        picks.push_back(nums[i]);
        dfs(pIdx+1);
        vis[i] = false;
        picks.pop_back();
        pre = nums[i]; // 현재 사용된 값 저장 
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