#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;
vector<int> picks;

void dfs(int s, int sIdx) {
    if(sIdx == M) {
        for(auto num : picks) {
            cout << num << " ";
        }
        cout << "\n";
        return;
    }

    for(int i=s; i<=N; i++) {
        picks.push_back(i);
        dfs(i, sIdx+1);
        picks.pop_back();
    }
    return;
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    
    dfs(1, 0);
    return 0;
}