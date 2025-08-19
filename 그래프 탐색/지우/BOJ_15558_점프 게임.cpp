#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;

int N, k;
int removeIdx = 0;
vector<vector<int>> maps;
vector<vector<bool>> vis;
queue<tuple<int,int,int>> q;

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

int bfs () {
    while(!q.empty()) {

        auto[r, c, t] = q.front(); q.pop();

        if(c < t) continue; // 현재 위치 c가 시간보다 뒤에 있으면? -> 불가능
        
        int nr = 1-r;
        int nc = c+k;
        if(nc >= N) {
            return 1;
        } else if(inRange(nr,nc) && !vis[nr][nc] && maps[nr][nc] == 1) {
            vis[nr][nc] = true;
            q.push({nr,nc, t+1});
        }

        for(int i=-1; i<=1; i+=2) {
            nr = r;
            nc = c + i;
            
            if(nc >= N) {
                return 1;
            } else if(inRange(nr,nc) && !vis[nr][nc] && maps[nr][nc] == 1) {
                vis[nr][nc] = true;
                q.push({nr,nc,t+1});
            }
        }
    }

    return 0;
}

int main() {
    cin >> N >> k;
    maps.resize(2, vector<int>(N,0));
    vis.resize(2, vector<bool>(N,false)); 
    
    for(int r=0; r<2; r++) {
        string s; cin >> s;
        for(int c=0; c<N; c++) {
            maps[r][c] = s[c] - '0';
        }
    }

    vis[0][0] = true;
    q.push({0,0,0});
    cout << bfs();
    
    return 0;
}