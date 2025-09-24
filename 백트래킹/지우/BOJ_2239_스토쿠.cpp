#include <iostream>
#include <vector>

using namespace std;
vector<vector<int>> maps;

bool check9(int x, int y, int num) {
    int sr = (x/3)*3;
    int sc = (y/3)*3;

    for(int r=sr; r<sr+3; r++) {
        for(int c=sc; c<sc+3; c++) {
            if(maps[r][c] == num) return false;
        }
    }
    return true;
}

bool checkCol(int x, int y, int num) {
    for(int r=0; r<9; r++) {
        if(maps[r][y] == num) return false;
    }
    return true;
}

bool checkRow(int x, int y, int num) {
    for(int c=0; c<9; c++) {
        if(maps[x][c] == num) return false;
    }
    return true;
}

void dfs(int idx) {
    
    if(idx == 81) {
        for(int r=0; r<9; r++) {
            for(int c=0; c<9; c++) {
                cout << maps[r][c];
            }
            cout << "\n";
        }
        exit(0);
        return;
    }

    int r = idx/9;
    int c = idx%9;

    if(maps[r][c] != 0) {
        dfs(idx+1);
        return;
    }
    
    for(int i=1; i<=9; i++) {
        if(checkRow(r,c,i) && checkCol(r,c,i) && check9(r,c,i)) {
            maps[r][c] = i;
            dfs(idx+1);
            maps[r][c] = 0;
        }
    }
}

int main() {
    maps.resize(9, vector<int>(9,0));
    for(int r=0; r<9; r++) {
        string s; cin >> s;
        for(int c=0; c<9; c++) {
            maps[r][c] = s[c] - '0';
        }
    }

    dfs(0);
    return 0;
}