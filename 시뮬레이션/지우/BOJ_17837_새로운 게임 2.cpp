#include <iostream>
#include <vector>
#include <deque>
#include <tuple>

using namespace std;
int N, K;
vector<vector<int>> maps;
vector<int> dir;
vector<pair<int,int>> pos;
vector<vector<vector<int>>> horses;
deque<int> tmp;

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

int dr[] = {0,0,-1,1};
int dc[] = {1,-1,0,0};

int reverseDir(int d) {
    if(d==0 || d==1) return 1-d;
    if(d==2 || d==3) return 5-d;
    return d;
}

void doRed(int curr, int r, int c, int d) {
    for(int i=tmp.size()-1; i>=0; i--) {
        horses[r][c].push_back(tmp[i]);
        pos[tmp[i]] = {r,c}; 
    }
}

void doWhite(int curr, int r, int c, int d) {
    for(int i=0; i<tmp.size(); i++) {
        horses[r][c].push_back(tmp[i]);
        pos[tmp[i]] = {r,c}; 
    }
}

void doBlue(int curr, int r, int c, int d) {
    // 방향 바꿔본다.
    d = reverseDir(d); dir[curr] = d;

    int nr = r + dr[d]; int nc = c + dc[d];
    if(!inRange(nr,nc) || maps[nr][nc] == 2) {
        for(int i=0; i<tmp.size(); i++) {
            horses[r][c].push_back(tmp[i]);
            pos[tmp[i]] = {r,c}; 
        }
    }
    else if(maps[nr][nc] == 0) {
        doWhite(curr, nr, nc, d);
    }
    else if(maps[nr][nc] == 1) {
        doRed(curr, nr, nc, d);
    }
    
}

int Play() {
    for(int turn=1; turn <= 1000 ; turn++) {
        for(int curr=1; curr<=K; curr++) {
            auto[r, c] = pos[curr];
            int d = dir[curr];
    
            tmp.clear(); vector<int> deletes;
            bool isS = false;
            for(int i=0; i<horses[r][c].size(); i++) {
                if(horses[r][c][i] == curr) isS = true;
                if(isS) {
                    tmp.push_back(horses[r][c][i]);
                    deletes.push_back(i);
                } 
            }
            for(int i=deletes.size()-1; i>=0; i--) {
                horses[r][c].erase(horses[r][c].begin() + deletes[i]);
            }
            
    
            int nr = r + dr[d]; int nc = c + dc[d];
            if(!inRange(nr,nc) || maps[nr][nc] == 2) {
                doBlue(curr, r, c, d);
            } 
            else if(maps[nr][nc] == 0) {
                doWhite(curr, nr, nc, d);
            }
            else if(maps[nr][nc] == 1) {
                doRed(curr, nr, nc, d);
            }

            auto [finalR, finalC] = pos[curr];
            if(horses[finalR][finalC].size() >= 4) {
                return turn;
            }
            
        }
    }

    return -1;
}

int main() {
    cin >> N >> K;
    maps.resize(N, vector<int>(N, 0));
    pos.resize(K+1, {0,0});
    dir.resize(K+1, 0);
    horses.resize(N, vector<vector<int>>(N, vector<int>(0)));

    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            cin >> maps[r][c];
        }
    }

    for(int num=1; num<=K; num++) {
        int r, c, d;
        cin >> r >> c >> d;
        r--; c--; d--;
        dir[num] = d;
        pos[num] = {r,c};
        horses[r][c].push_back(num);
    }

    cout << Play();
    
    return 0;
}