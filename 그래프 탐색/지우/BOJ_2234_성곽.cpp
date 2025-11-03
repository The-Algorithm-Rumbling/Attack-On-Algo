#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int N, M; int R, C;
int largest = -1;
vector<vector<int>> maps;
vector<vector<int>> dp;
vector<int> sizes;

int walls[] = {8,4,2,1};
int dr[] = {1, 0, -1, 0};
int dc[] = {0, 1, 0, -1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

void bfs(int r, int c, int roomNum) {
    queue<pair<int,int>> q;
    dp[r][c] = roomNum;
    q.push({r,c});

    int cnt = 0;
    while(!q.empty()) {
        auto[r,c] = q.front(); q.pop();
        cnt++;
        
        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];
            if(!inRange(nr,nc)) continue;
             // 벽 없는 거 확인!
            if((maps[r][c] & walls[d]) != walls[d] && dp[nr][nc] == 0) {
                dp[nr][nc] = roomNum;
                q.push({nr,nc});
            }
        }
    }

    largest = max(largest, cnt);
    sizes[roomNum] = cnt;
}

int main() {
    cin >> M >> N;
    maps.resize(N, vector<int>(M, 0));
    dp.resize(N, vector<int>(M, 0));
    sizes.resize(N*M+1, 0);
    
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
        }
    }

    int roomNum = 1;
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            if(dp[r][c] == 0) {
                bfs(r,c, roomNum);
                roomNum++;
            }
        }
    }
    roomNum--;
    cout << roomNum << "\n";
    cout << largest << "\n";

    int maxSize = -1;
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            int num1 = dp[r][c];
            
            for(int d=0; d<4; d++) {
                int nr = r + dr[d]; int nc = c + dc[d];
                if(!inRange(nr,nc)) continue;
                if((maps[r][c] & walls[d]) == walls[d] && dp[nr][nc] != num1) {
                    maxSize = max(maxSize, sizes[dp[r][c]] + sizes[dp[nr][nc]]);
                }
            }
        }
    }

    cout << maxSize;
    return 0;
}