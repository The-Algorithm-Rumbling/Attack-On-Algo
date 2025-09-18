#include <iostream>
#include <vector>

using namespace std;
vector<vector<int>> maps;
int ans;
 
void dfs(int country, int battle, int cnt) {

    if(cnt == 0) {
        ans = 1;
    }
    
    if(battle == 6) {
        if(country+2 < 6) {
            dfs(country+1, country+2, cnt);
        }
        return;
    }

    // 승
    if(maps[country][0] > 0 && maps[battle][2] > 0) {
        maps[country][0]--; maps[battle][2]--;
        dfs(country, battle+1, cnt-2);
        maps[country][0]++; maps[battle][2]++;
    }

    // 무
    if(maps[country][1] > 0 && maps[battle][1] > 0) {
        maps[country][1]--; maps[battle][1]--;
        dfs(country, battle+1, cnt-2);
        maps[country][1]++; maps[battle][1]++;
    }

    // 패
    if(maps[country][2] > 0 && maps[battle][0] > 0) {
        maps[country][2]--; maps[battle][0]--;
        dfs(country, battle+1, cnt-2);
        maps[country][2]++; maps[battle][0]++;
    }
}

int main() {
    int T = 4;
    while(T--) {
        ans = 0;
        maps.assign(6, vector<int>(3, 0));
        int cnt = 0;
        for(int r=0; r<6; r++) {
            for(int c=0; c<3; c++) {
                cin >> maps[r][c];
                cnt += maps[r][c];
            }
        }
        dfs(0,1, cnt);
        cout << ans << " ";
    }
    return 0;
}