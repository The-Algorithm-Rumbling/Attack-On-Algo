#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
vector<vector<int>> arr;
vector<bool> vis;

int maxP = -1;

void dfs(int ath, int sum) {
    if(ath == 11) {
        maxP = max(maxP, sum);
        return;
    }

    for(int p=0; p<11; p++) {
        if(!vis[p] && arr[ath][p] > 0) {
            vis[p] = true;
            dfs(ath+1, sum + arr[ath][p]);
            vis[p] = false;
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    int T; cin >> T;
    while(T--) {
        maxP = -1;
        arr.assign(11, vector<int>(11,0));
        vis.assign(11, false);
        for(int r=0; r<11; r++) {
            for(int c=0; c<11; c++) {
                cin >> arr[r][c];
            }
        }
        dfs(0,0);
        cout << maxP << "\n";
    }
    return 0;
}