#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int N, M;
int answer;
vector<vector<int>> maps;
vector<vector<bool>> vis;
vector<pair<int,int>> candidates;
vector<pair<int,int>> picks;

int dr[] = {1, 0, -1, 0};
int dc[] = {0, 1, 0, -1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

int bfs(int sr, int sc) {
    queue<pair<int,int>> q;
    vis[sr][sc] = true;
    q.push({sr,sc});
    
    int cnt = 0;
    bool isS = true;
    
    while(!q.empty()) {
        auto[r,c] = q.front(); q.pop();
        cnt++;
        
        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];
            if(inRange(nr,nc) && !vis[nr][nc]) {
                if(maps[nr][nc] == 0) isS = false;
                if(maps[nr][nc] == 2) {
                    vis[nr][nc] = true;
                    q.push({nr,nc});
                }
            }
        }
        
    }

    if(!isS) return 0;
    return cnt;
}

void dfs(int start, int cnt) {
    if(cnt == 2) {
        vis.assign(N, vector<bool>(M, false));
        for(auto p : picks) {
            auto[r,c] = p;
            maps[r][c] = 1;
        }

        int tmp = 0;
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(maps[r][c] ==2 && !vis[r][c]) {
                    tmp += bfs(r,c);
                }
            }
        }
        answer = max(answer, tmp);
        
        for(auto p : picks) {
            auto[r,c] = p;
            maps[r][c] = 0;
        }
        return;
    }
    
    for(int i = start; i<candidates.size(); i++) {
        picks.push_back(candidates[i]);
        dfs(i+1, cnt+1);
        picks.pop_back();
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    maps.resize(N, vector<int>(M, 0));

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
            if(maps[r][c] == 0) {
                candidates.push_back({r,c});
            }
        }
    }
    
    dfs(0,0);
    cout << answer;
    
    return 0;
}