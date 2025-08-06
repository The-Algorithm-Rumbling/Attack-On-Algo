#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;
vector<int> nums;
vector<int> picks;

void dfs(int s, int sIdx) {
    if(sIdx == M) {
        for(auto num : picks) {
            cout << num << " ";
        }
        cout << "\n";
        return;
    }

    for(int i=s; i<N; i++) {
        picks.push_back(nums[i]);
        dfs(i+1, sIdx+1);
        picks.pop_back();
    }
    return;
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