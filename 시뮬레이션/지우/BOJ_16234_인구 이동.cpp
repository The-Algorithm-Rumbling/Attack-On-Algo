#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int N, L, R;
vector<vector<int>> maps;
vector<vector<bool>> vis;

int dr[] = {1,0,-1,0};
int dc[] = {0, 1, 0, -1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

int bfs(int sr, int sc) {
    queue<pair<int,int>> q;
    vector<pair<int,int>> tmps;
    int cnt = 0; int sum = 0;
    
    vis[sr][sc] = true;
    q.push({sr,sc});

    while(!q.empty()) {
        auto[r,c] = q.front(); q.pop();
        cnt++;
        sum += maps[r][c];
        tmps.push_back({r,c});

        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];
            if(inRange(nr,nc) && !vis[nr][nc]) {
                int diff = abs(maps[r][c] - maps[nr][nc]);
                if(L <= diff && R >= diff) {
                    vis[nr][nc] = true;
                    q.push({nr,nc});
                }
            }
        }
    }

    for(auto t : tmps) {
        auto[r,c] = t;
        maps[r][c] = sum / cnt;
    }

    if(cnt > 1) return 1;
    return 0;
}

int main() {
    cin >> N >> L >> R;
    maps.resize(N, vector<int>(N,0));

    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            cin >> maps[r][c];
        }
    }

    int answer = 0;
    while(1) {
        int goStop = 0;
        vis.assign(N, vector<bool>(N,false));
        
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(!vis[r][c]) goStop += bfs(r,c);
            }
        }

        if(goStop == 0) break;
        answer++;
    }
    
    cout << answer;
    return 0;
}