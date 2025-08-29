#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;

int R = 12; int C = 6;
bool isS; int cnt;
queue<tuple<char,int,int>> q; queue<pair<int,int>> q2;
vector<vector<char>> maps; vector<vector<bool>> vis;

int dr[] = {1, 0, -1, 0};
int dc[] = {0, -1, 0, 1};

bool inRange(int r, int c) {
    return r>=0 && r<R && c>=0 && c<C;
}

void Down() {
    vector<char> list;
    for(int c=0; c<C; c++) {
        for(int r=0; r<R; r++) {
            if(maps[r][c] != '.') {
                list.push_back(maps[r][c]);
                maps[r][c] = '.';
            }
        }

        int rIdx = 11;
        while(list.size() > 0) {
            char color = list[list.size()-1];
            maps[rIdx][c] = color;
            list.pop_back();
            rIdx--;
        }
    }
}

void bfs() {
    while(!q.empty()) {
        auto [color, r, c] = q.front(); q.pop();
        q2.push({r,c});
        
        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];

            if(inRange(nr,nc) && !vis[nr][nc] && maps[nr][nc] == color) {
                vis[nr][nc] = true;
                q.push({color, nr, nc});
            }
        }
    }
    
    if(q2.size() >= 4) {
        isS = true;
        while(!q2.empty()) {
            auto [r, c] = q2.front(); q2.pop();
            maps[r][c] = '.';
        }
    } else {
        q2 = queue<pair<int,int>>();
    }
}

int main() {
    maps.resize(R, vector<char>(C,0));
    
    for(int r=0; r<R; r++) {
        for(int c=0; c<C; c++) {
            cin >> maps[r][c];
        }
    }
    
    while(1) {
        isS = false;
        vis.assign(R, vector<bool>(C,false));
        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(maps[r][c] != '.' && !vis[r][c]) {
                    vis[r][c] = true;
                    q.push({maps[r][c], r, c});
                    bfs();
                }
            }
        }

        Down();        
        if(!isS) break;
        cnt++;
    }

    cout << cnt;
    return 0;
}