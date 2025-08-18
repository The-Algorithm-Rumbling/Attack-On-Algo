#include <iostream>
#include <vector>
#include <unordered_set>
#include <cmath>
#include <climits>

using namespace std;

int N;
int ans = INT_MAX;
vector<vector<int>> power;
vector<bool> vis;

void dfs(int s, int pIdx) {
    if(pIdx == N/2) {
        vector<bool> vis2(N+1, false);
        
        for(int i=1; i<=N; i++) {
            if(!vis[i]) vis2[i] = true;
        }

        int team1 = 0;
        int team2 = 0;

        for(int r=1; r<=N-1; r++) {
            for(int c=r+1; c<=N; c++) {
                if(vis[r] && vis[c]) {
                    team1 += power[r][c] + power[c][r];
                }
                if(vis2[r] && vis2[c]) {
                    team2 += power[r][c] + power[c][r];
                }
            }
        }

        ans = min(ans, abs(team1-team2));
        if(ans == 0) {
            cout << ans; 
            exit(0);
        } 
        
    }

    for(int i=s; i<=N; i++) {
        if(!vis[i]) {
            vis[i] = true;
            dfs(i+1, pIdx+1);
            vis[i] = false;
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    power.resize(N+1, vector<int>(N+1,0));
    vis.resize(N+1, false);
    for(int r=1; r<=N; r++) {
        for(int c=1; c<=N; c++) {
            cin >> power[r][c];
        }
    }

    dfs(1,0);
    cout << ans;
    return 0;
}