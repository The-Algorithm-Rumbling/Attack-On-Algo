#include <iostream>
#include <vector>

using namespace std;

int N;
int ans = 0;
vector<int> nums;
vector<bool> vis;

void dfs(int sum, int sIdx) {
    
    if(sIdx == nums.size() - 2) {
        ans = max(ans, sum);
        return;
    }

    for(int i=1; i<nums.size()-1; i++) {
        if(vis[i]) continue;
        
        int left = 1; int right = 1;
        while(vis[i-left]) {
            left++;
        }
        while(vis[i+right]) {
            right++;
        }


        int tmpSum = sum + nums[i-left]*nums[i+right];
        vis[i] = true;
        dfs(tmpSum, sIdx+1);
        vis[i] = false;

        
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    nums.resize(N,0);
    vis.resize(N,false);

    for(int i=0; i<N; i++) {
        cin >> nums[i];
    }

    dfs(0, 0);
    cout << ans;
    
    return 0;
}