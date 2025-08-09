#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

vector<vector<int>> powers;
vector<bool> vis;
int maxAns;

void dfs(int sIdx, int sum) {
    if(sIdx == 11) {
        maxAns = max(maxAns, sum);
        return;
    }

    for(int i=0; i<11; i++) {
        if(!vis[i] && powers[i][sIdx] > 0) {
            vis[i] = true;
            dfs(sIdx+1, sum + powers[i][sIdx]);
            vis[i] = false;
        }
    }
}

int main() {
    int T; cin >> T;
    while(T--) {
        powers.assign(11,vector<int>(11,0));
        vis.assign(11,false);
        maxAns = -1;

        for(int r=0; r<11; r++) {
            for(int c=0; c<11; c++) {
                cin >> powers[r][c];
            }
        }

        dfs(0,0);
        cout << maxAns << "\n";
    }
    return 0;
}