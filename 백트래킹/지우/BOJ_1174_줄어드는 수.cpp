#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

int N;
int ans;
bool found = false;
vector<int> nums = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
vector<long long> arrPicks;
vector<int> picks;

void dfs(int sIdx) {
    if(!picks.empty()) {
        long long num = 0;
        for(int p : picks) {
            num = num * 10 + p; // 정처기의 추억..
        }
        arrPicks.push_back(num);
    }

    for(int i=sIdx; i<nums.size(); i++) {
        picks.push_back(nums[i]);
        dfs(i+1);
        picks.pop_back();
    }
}

int main() {  
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;

    dfs(0);
    
    sort(arrPicks.begin(), arrPicks.end());
    
    // cout << arrPicks.size() << " " << arrPicks[arrPicks.size()-1] << "\n";
    
    if(arrPicks.size() < N) {
        cout << -1;
    } else {
        cout << arrPicks[N-1];
    }
    return 0;
}