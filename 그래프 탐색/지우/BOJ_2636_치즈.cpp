#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, M;
int lastCheese; int year;
vector<vector<int>> maps; vector<vector<bool>> wasCheese;
vector<vector<bool>> vis;
queue<pair<int,int>> q; 

int dr[] = {-1,0,1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

void hole() {
    queue<pair<int,int>> q2;
    q2.push({0,0});
    vis[0][0] = true;

    while(!q2.empty()) {
        pair<int,int> curr = q2.front(); q2.pop();
        int r = curr.first; int c = curr.second;

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(inRange(nr,nc) && !vis[nr][nc] && maps[nr][nc] == 0) {
                vis[nr][nc] = true;
                q2.push({nr,nc});
            }
        }
        
    }
}

void bfs() {
    wasCheese.assign(N,vector<bool>(M,false));
    vis.assign(N, vector<bool>(M,0));
    hole();
    int melting = lastCheese;
    
    while(melting > 0) {
        pair<int,int> curr = q.front(); q.pop();
        int r = curr.first; int c = curr.second;
        melting--;
        if(wasCheese[r][c]) return;

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(inRange(nr,nc) && vis[nr][nc]) {
                wasCheese[r][c] = true;
                maps[r][c] = 0;
                break;
            }
        }

        if(!wasCheese[r][c]) q.push({r,c});
    }

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            if(wasCheese[r][c]) maps[r][c] = 0;
        }
    }
    
    return;
}

int main() {
    cin >> N >> M;
    maps.resize(N, vector<int>(M,0));
    
    
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            int a; cin >> a;
            maps[r][c] = a;
            if(a == 1) q.push({r,c});
        }
    }

    while(q.size()>0) {
        lastCheese = q.size();
        bfs();
        year++;
    }

    cout << year << "\n" << lastCheese;
    
    return 0;
}