#include <iostream>
#include <vector>
#include <set>

using namespace std;

vector<vector<int>> maps(9,vector<int>(9,0));
vector<pair<int,int>> blanks(0);
vector<vector<int>> candidates; 
set<int> s; set<int> s2;

void setCandi(int idx) {

    int r = blanks[idx].first;
    int c = blanks[idx].second;
    
    for(int nc=0; nc<9; nc++) {
        s2.erase(maps[r][nc]);
    }

    for(int nr=0; nr<9; nr++) {
        s2.erase(maps[nr][c]);
    }

    int sr = (r/3) * 3;
    int sc = (c/3) * 3; // 3x3 블록 좌표 계산
    for(int x=sr; x<sr+3; x++) {
        for(int y=sc; y<sc+3; y++) {
            s2.erase(maps[x][y]);
        }
    }
}

bool checkRowColBox(int r, int c, int num) {
    int result = true;
    for(int nc=0; nc<9; nc++) {
        if(maps[r][nc] == num) {
            result = false;
            break;
        }
    }

    for(int nr=0; nr<9; nr++) {
        if(maps[nr][c] == num) {
            result = false;
            break;
        }
    }

    int sr = (r/3) * 3;
    int sc = (c/3) * 3; // 3x3 블록 좌표 계산
    for(int x=sr; x<sr+3; x++) {
        for(int y=sc; y<sc+3; y++) {
            if(maps[x][y] == num) {
                result = false;
                break;
            }
        }
    }

    return result;
}

void dfs(int idx) {

    if(idx == blanks.size()) {
        for(int r=0; r<9; r++) {
            for(int c=0; c<9; c++) {
                cout << maps[r][c] << " ";
            }
            cout << "\n";
        }
        exit(0); // dfs 타고 다시 안 올라가게 그냥 종료
    }

    int r = blanks[idx].first;
    int c = blanks[idx].second;

    for(int i=0; i<candidates[idx].size(); i++) {
        int tmpNum = candidates[idx][i];
        if(checkRowColBox(r,c,tmpNum)) {
            maps[r][c] = tmpNum;
            dfs(idx+1);
            maps[r][c] = 0; 
        }
    }

}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    for(int r=0; r<9; r++) {
        for(int c=0; c<9; c++) {
            cin >> maps[r][c];
            if(maps[r][c] == 0) {
                blanks.push_back({r,c});
            }
        }
    }

    for(int i=1; i<=9; i++) {
        s.insert(i);
    }

    candidates.resize(blanks.size());
    for(int i=0; i<blanks.size(); i++) {
        s2 = s;
        setCandi(i);
        for(auto candi : s2) {
            candidates[i].push_back(candi);
        }
    }

    dfs(0);
    
    return 0;
}