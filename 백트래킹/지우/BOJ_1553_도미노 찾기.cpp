#include <iostream>
#include <vector>
#include <set>

using namespace std;

int ans = 0;
int R = 8; int C = 7;
vector<vector<int>> maps; 
vector<vector<bool>> vis;
vector<int> picks;
set<pair<int,int>> sets;

int dr[] = {0,1};
int dc[] = {1,0};

bool inRange(int r, int c) {
    return r>=0 && r<R && c>=0 && c<C;
}

void dfs(int depth, int start) {
    if(depth == 2) {
        sets.insert({picks[0], picks[1]});
        sets.insert({picks[1], picks[0]});
        return;
    }
    
    for(int i=start; i<=6; i++) {
        picks.push_back(i);
        dfs(depth+1, i);
        picks.pop_back();
    }
}

void dfs2(int idx, int cnt) {

    if(cnt == 28) {
        ans++;
        return;
    }
    if(idx >= 8*7) return;

    int r = idx/C; 
    int c = idx%C;
    if(vis[r][c]) {
        dfs2(idx+1, cnt);
        return; // 밑으로 내려가면 안됨!
    }
    for(int d=0; d<2; d++) {
        int nr = r + dr[d]; int nc = c + dc[d];
        if(inRange(nr,nc) && !vis[nr][nc]) {
            int a = maps[r][c]; int b = maps[nr][nc];

            if(sets.count({a,b})) {
                sets.erase({a,b}); sets.erase({b,a});
                vis[r][c] = vis[nr][nc] = true;
                dfs2(idx+1, cnt+1);
                
                sets.insert({a, b}); sets.insert({b, a});
                vis[r][c] = vis[nr][nc] = false;
            }
        }
    }
}

int main() {
    maps.resize(R, vector<int>(C,0));
    vis.resize(R, vector<bool>(C,false));
    for(int r=0; r<R; r++) {
        string s; cin >> s;
        for(int c=0; c<C; c++) {
            maps[r][c] = s[c]-'0';
        }
    }

    dfs(0,0); // 도미노 sets 만들어주기
    dfs2(0, 0);
    cout << ans;
    return 0;
}