#include <iostream>
#include <vector>
#include <cmath>

using namespace std;
vector<vector<int>> maps(4, vector<int>(8,0));

void Turn(int a, int dir) {
    if(dir == 1) {
        int tmp = maps[a][7];
        for(int i=6; i>=0; i--) {
            maps[a][i+1] = maps[a][i];
        }
        maps[a][0] = tmp;
    }
    else {
        int tmp = maps[a][0];
        for(int i=1; i<8; i++) {
            maps[a][i-1] = maps[a][i];
        }
        maps[a][7] = tmp;
    }
}

void Solve(int a, int dir) {
    // -1, 0, 1 (0: 회전 x)
    vector<int> rotates(4,0);
    rotates[a] = dir;

    for(int i=a; i<3; i++) {
        if(maps[i][2] != maps[i+1][6]) {
            rotates[i+1] = -1 * rotates[i];
        } else {
            break;
        }
    }

    for(int i=a; i>0; i--) {
        if(maps[i][6] != maps[i-1][2]) {
            rotates[i-1] = -1 * rotates[i];
        } else {
            break;
        }
    }

    for(int i=0; i<4; i++) {
        if(rotates[i] != 0) Turn(i, rotates[i]);
    }
}

int main() {
    for(int r=0; r<4; r++) {
        string s; cin >> s;
        for(int c=0; c<8; c++) {
            maps[r][c] = s[c] - '0';
        }
    }

    int K; cin >> K;
    for(int i=0; i<K; i++) {
        int a; int dir;
        cin >> a >> dir;
        a--;
        Solve(a,dir);
    }

    int ans = 0;
    for(int i=0; i<4; i++) {
        ans += maps[i][0] * pow(2,i);
    }
    cout << ans;
    
    return 0;
}