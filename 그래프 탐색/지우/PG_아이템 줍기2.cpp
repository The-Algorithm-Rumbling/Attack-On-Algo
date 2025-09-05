#include <string>
#include <vector>
#include <queue>

using namespace std;
int cX, cY, iX, iY;
vector<vector<int>> maps(101, vector<int>(101,0));
vector<vector<int>> dp(101, vector<int>(101,-1));
queue<pair<int,int>> q;

int dr[] = {-1,0,1,0};
int dc[] = {0, 1, 0, -1};

bool inRange(int r, int c) {
    return r>=0 && r<101 && c>=0 && c<101;
}

int bfs() {
    dp[cX][cY] = 0;
    q.push({cX, cY});
    
    while(!q.empty()) {
        auto [r,c] = q.front(); q.pop();
        if(r==iX && c==iY) {
            return dp[r][c];
        }
        
        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(inRange(nr,nc) && maps[nr][nc] == 1 && dp[nr][nc] == -1) {
                dp[nr][nc] = dp[r][c] + 1;
                q.push({nr,nc});
            }
        }
    }
}

int solution(vector<vector<int>> rectangle, int characterX, int characterY, int itemX, int itemY) {
    int answer = 0;
    cX = 2 * characterX; cY = 2 * characterY; iX = 2 * itemX; iY = 2 * itemY;
    
    // 모두 1로 채워주기
    for(int i=0; i<rectangle.size(); i++) {
        int r1 = rectangle[i][0] * 2; int c1 = rectangle[i][1] * 2; 
        int r2 = rectangle[i][2] * 2; int c2 = rectangle[i][3] * 2;
        
        for(int r=r1; r<=r2; r++) {
            for(int c=c1; c<=c2; c++) {
                maps[r][c] = 1; 
            }
        }
    }
    
    // 테두리 안은 0으로 비워주기
    for(int i=0; i<rectangle.size(); i++) {
        int r1 = rectangle[i][0] * 2; int c1 = rectangle[i][1] * 2; 
        int r2 = rectangle[i][2] * 2; int c2 = rectangle[i][3] * 2;
        
        for(int r=r1+1; r<=r2-1; r++) {
            for(int c=c1+1; c<=c2-1; c++) {
                maps[r][c] = 0; 
            }
        }
    }
    
    return bfs()/2;
}