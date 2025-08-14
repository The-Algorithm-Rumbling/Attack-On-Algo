#include <iostream>
#include <vector>
#include <unordered_set>

using namespace std;

int N; int ans = 0;
vector<int> nums = {1, 5, 10, 50};
unordered_set<int> sets;

void dfs(int s, int sIdx, int sum) {
    if(sIdx == N) {
        sets.insert(sum);
        return;
    }

    for(int i=s; i<4; i++) {
        dfs(i, sIdx+1, sum+nums[i]);
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    dfs(0,0,0);

    cout << sets.size();
    return 0;
}