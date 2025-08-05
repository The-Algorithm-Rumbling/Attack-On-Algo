#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;
vector<int> picks;

void dfs(int sIdx) {
    if(sIdx == M) {
        for(auto num : picks) {
            cout << num << " ";
        }
        cout << "\n";
        return;
    }

    for(int i=1; i<=N; i++) {
        picks.push_back(i);
        dfs(sIdx+1);
        picks.pop_back();
    }

    return;
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    dfs(0);
    return 0;
}