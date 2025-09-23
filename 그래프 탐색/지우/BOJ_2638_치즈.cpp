#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int ans;
int N, M;
vector<vector<int>> maps;
queue<pair<int,int>> cq;

int dr[] = {1, 0, -1, 0};
int dc[] = {0, 1, 0, -1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

void checkOutside() {
    queue<pair<int,int>> q;
    vector<vector<bool>> vis(N, vector<bool>(M,false));
    vis[0][0] = true;
    maps[0][0] = 2;
    q.push({0,0});

    while(!q.empty()) {
        auto[r,c] = q.front(); q.pop();

        for(int d=0; d<4; d++) {
            int nr = r+dr[d]; int nc = c + dc[d];
            if(inRange(nr,nc) && maps[nr][nc] != 1 && !vis[nr][nc]) {
                vis[nr][nc] = true;
                maps[nr][nc] = 2;
                q.push({nr,nc});
            }
        }
    }
}

void Solve() {
    while(!cq.empty()) {

        ans++;
        checkOutside();

        int size = cq.size();
        vector<pair<int,int>> tmps;
        while(size--) {
            auto[r,c] = cq.front(); cq.pop();

            int cnt = 0;
            for(int d=0; d<4; d++) {
                int nr = r+dr[d]; int nc = c + dc[d];
                if(inRange(nr,nc) && maps[nr][nc] == 2) cnt++;
            }

            if(cnt >=2) {
                tmps.push_back({r,c});
            } else {
                cq.push({r,c});
            }
        }

        for(auto t : tmps) {
            auto[r,c] = t;
            maps[r][c] = 0;
        }
    }
}

int main() {
    cin >> N >> M;
    maps.resize(N, vector<int>(M,0));
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
            if(maps[r][c] == 1) {
                cq.push({r,c});
            }
        }
    }
    Solve();
    cout << ans;
    return 0;
}