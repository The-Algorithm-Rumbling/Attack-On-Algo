#include <iostream>
#include <vector>

using namespace std;
int N, M, x, y, K;
vector<vector<int>> maps;
vector<int> dice(6,0); // 위, 북, 동, 서, 남, 바닥

int dr[] = {0,0,-1,1};
int dc[] = {1,-1,0,0};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}

void RollDice(int d) {
    vector<int> tmp = dice;

    if(d == 0) { // 우
        dice[5] = tmp[2]; 
        dice[0] = tmp[3]; 
        dice[2] = tmp[0]; 
        dice[3] = tmp[5];
    }
    else if(d == 1) { // 좌  
        dice[0] = tmp[2];
        dice[2] = tmp[5];
        dice[3] = tmp[0];
        dice[5] = tmp[3];
    }
    else if(d == 2) { // 상
        dice[0] = tmp[4];
        dice[1] = tmp[0];
        dice[4] = tmp[5];
        dice[5] = tmp[1];
    }
    else { // 하 
        dice[0] = tmp[1];
        dice[1] = tmp[5];
        dice[4] = tmp[0];
        dice[5] = tmp[4];
    }
}

void Move(int d) {
    int nr = x + dr[d]; int nc = y + dc[d];
    if(!inRange(nr,nc)) return;
    x = nr; y = nc;

    RollDice(d);

    if(maps[x][y] == 0) {
        maps[x][y] = dice[5];
    } else {
        dice[5] = maps[x][y];
        maps[x][y] = 0;
    }

    cout << dice[0] << "\n";
    return;
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M >> x >> y >> K;
    
    maps.resize(N, vector<int>(M,0));
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
        }
    }

    while(K--) {
        int d; cin >> d;
        Move(d-1);
    }
    
    return 0;
}