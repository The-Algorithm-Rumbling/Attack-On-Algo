#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, K;
int Nlen;
int ans;
vector<int> nums;

void dfs(int depth, int num) {
    if(num > N) return;
    if(num <= N) {
        if(ans < num) {
            ans = num;
        }
    }
    
    for(int i=nums.size()-1; i>=0; i--) {
        dfs(depth+1, num*10 + nums[i]);
    }
}

int main() {
    cin >> N >> K;
    nums.resize(K,0);
    for(int i=0; i<K; i++) {
        cin >> nums[i];
    }
    sort(nums.begin(), nums.end());

    dfs(0,0);
    cout << ans;
    return 0;
}