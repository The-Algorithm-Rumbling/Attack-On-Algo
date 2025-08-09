#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;
vector<int> nums;
vector<int> picks;

void dfs(int s, int pIdx) {
    if(pIdx == M) {
        for(int i=0; i<M; i++) {
            cout << picks[i] <<" ";
        }
        cout << "\n";
        return;
    } 

    int pre = -1;
    
    for(int i=s; i<N; i++) {
        if(pre == nums[i]) continue;
        picks.push_back(nums[i]);
        dfs(i, pIdx+1);
        picks.pop_back();
        pre = nums[i];
    }
        
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    nums.resize(N,0);
    for(int i=0; i<N; i++) {
        cin >> nums[i];
    }
    sort(nums.begin(), nums.end());
    dfs(0, 0);
    
    return 0;
}