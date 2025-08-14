#include <iostream>
#include <vector>

using namespace std;

int N; int ans = 0;
vector<vector<int>> maps;
vector<bool> vis1; // \ 방향 대각선
vector<bool> vis2; // / 방향 대각선

int dfs(int s, int cnt, vector<pair<int,int>>& ones) {
    if(s == ones.size()) { 
        return cnt;
    }

    int maxCnt = cnt;
    
    for(int i=s; i<ones.size(); i++) {
        auto[r, c] = ones[i];
        if(!vis1[r-c+N-1] && !vis2[r+c]) {
            vis1[r-c+N-1] = vis2[r+c] = true;
            maxCnt = max(maxCnt, dfs(i+1, cnt+1, ones));
            vis1[r-c+N-1] = vis2[r+c] = false;
        }
    }

    return maxCnt;
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    maps.resize(N, vector<int>(N,0));
    vector<pair<int,int>> whites(0);
    vector<pair<int,int>> blacks(0);

    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            cin >> maps[r][c];
            if(maps[r][c] == 1) {
                if((r+c)%2 == 0) {
                    whites.push_back({r,c});
                } else {
                    blacks.push_back({r,c});
                }
            }
        }
    }
    vis1.resize(2*N-1, false);
    vis2.resize(2*N-1, false);

    ans += dfs(0, 0, whites);
    ans += dfs(0, 0, blacks);
    cout << ans;
    
    return 0;
}