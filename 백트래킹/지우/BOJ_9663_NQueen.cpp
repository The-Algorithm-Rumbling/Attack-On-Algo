#include <iostream>
#include <vector>

using namespace std;

int N; int ans = 0;
vector<vector<bool>> maps;
vector<bool> visCol;
vector<bool> visRow;

int dr[] = {-1,-1};
int dc[] = {-1,1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

bool checkDiag(int r, int c) {
    for(int d=0; d<2; d++) { // dfs이니까 위에만 보면 된다.
        int nr = r;
        int nc = c;
        while(1) {
            nr += dr[d];
            nc += dc[d];
            if(inRange(nr,nc)) {
                if(maps[nr][nc]) return false;
            } else {
                break;
            }
        }
    }

    return true;
}

void dfs(int r) { // 한 행에 한 퀸을 놓아야 N개를 놓을 수 있다.
    if(r == N) {
        ans++;
        return;
    }

    for(int c=0; c<N; c++) {
        if(!visCol[c] && checkDiag(r,c)) {
            visCol[c] = true;
            maps[r][c] = true;
            
            dfs(r+1);
    
            visCol[c] = false;
            maps[r][c] = false;
        }
    }

    
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    maps.resize(N, vector<bool>(N,false));
    visCol.resize(N,false);
    visRow.resize(N,false);

    dfs(0);
    cout << ans;
    return 0;
}