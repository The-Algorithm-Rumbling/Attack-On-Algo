#include <iostream>
#include <vector>

using namespace std;

int N, M, H;
int hr, hc; int ans;
vector<pair<int,int>> milks;
vector<bool> vis;

int dist(int r1, int c1, int r2, int c2) {
    return abs(r1-r2) + abs(c1-c2);
}

void dfs(int r, int c, int mCnt, int power) {
    if(power >= dist(r,c,hr,hc)) ans = max(ans, mCnt);
    
    for(int i=0; i<milks.size(); i++) {
        if(!vis[i]) {
            auto[mr,mc] = milks[i];
            int dis = dist(r,c,mr,mc);
            if(power >= dis) {
                vis[i] = true;
                dfs(mr, mc, mCnt+1, power-dis+H);
                vis[i] = false;
            }
        }
    }
}

int main() {
    cin >> N >> M >> H;
    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            int a; cin >> a;
            
            if(a == 1) {
                hr = r; hc = c;
            } 
            else if (a== 2) {
                milks.push_back({r,c});
            }
        }
    }
    vis.resize(milks.size(), false);
    
    dfs(hr, hc, 0, M);
    cout << ans;
    return 0;
}