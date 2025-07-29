#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, M;
int wallCnt;
vector<vector<int>> maps;
vector<vector<bool>> outside;

int oddR[] = {-1,0,1,1,0,-1};
int oddC[] = {0,-1,0,1,1,1};

int evenR[] = {-1,0,1,-1,0,1};
int evenC[] = {0,-1,0,-1,1,-1};

void checkOutside();
void countWalls();

bool inRange(int r, int c) {
    return r>=0 && r<N+2 && c>=0 && c<M+2;
}

int main() {
    cin >> M >> N;
    maps.resize(N+2, vector<int>(M+2, 0));

    for(int r=1; r<=N; r++) {
        for(int c=1; c<=M; c++) {
            cin >> maps[r][c];
        }
    }

    checkOutside();
    countWalls();
    cout << wallCnt;

    return 0;
}

void checkOutside() {
    queue<pair<int,int>> q;
    outside.resize(N+2, vector<bool>(M+2, 0));
    
    q.push({0,0}); 
    outside[0][0] = true;
    while(!q.empty()) {
        auto [r,c] = q.front(); q.pop();

        if(r%2 != 0) {
            for(int d=0; d<6; d++) {
                int nr = r + oddR[d];
                int nc = c + oddC[d];

                if(inRange(nr,nc) && maps[nr][nc] == 0 && !outside[nr][nc]) {
                    outside[nr][nc] = true;
                    q.push({nr,nc});
                }
            }
        } else {
            for(int d=0; d<6; d++) {
                int nr = r + evenR[d];
                int nc = c + evenC[d];

                if(inRange(nr,nc) && maps[nr][nc] == 0 && !outside[nr][nc]) {
                    outside[nr][nc] = true;
                    q.push({nr,nc});
                }
            }
        }
    }
}

void countWalls() {
    for(int r=1; r<=N; r++) {
        for(int c=1; c<=M; c++) {
            if(maps[r][c] == 1) {
                if(r%2 != 0) {
                    for(int d=0; d<6; d++) {
                        int nr = r + oddR[d];
                        int nc = c + oddC[d];
        
                        if(inRange(nr,nc) && outside[nr][nc]) {
                            wallCnt++;
                        }
                    }
                } else {
                    for(int d=0; d<6; d++) {
                        int nr = r + evenR[d];
                        int nc = c + evenC[d];
        
                        if(inRange(nr,nc) && outside[nr][nc]) {
                            wallCnt++;
                        }
                    }
                }
            }
        }
    }
}