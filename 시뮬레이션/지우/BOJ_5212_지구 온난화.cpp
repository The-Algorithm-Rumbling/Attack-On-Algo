#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int R, C;
vector<vector<char>> maps;
queue<pair<int,int>> q;
vector<pair<int,int>> melts;

int dr[] = {-1,0,1,0};
int dc[] = {0,1,0,-1};

bool inRange(int r, int c) {
    return r>=0 && r<R && c>=0 && c<C;
}

void Melting() {
    while(!q.empty()) {
        auto[r,c] = q.front(); q.pop();

        int cnt = 0;
        for(int d=0; d<4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];
            if(inRange(nr,nc)) {
                if(maps[nr][nc] == '.') cnt++;
            } else {
                cnt ++;
            }
        }

        if(cnt >= 3) melts.push_back({r,c});
    }

    for(auto pos : melts) {
        auto[r,c] = pos;
        maps[r][c] = '.';
    }
}

int main() {
    cin >> R >> C;
    maps.resize(R, vector<char>(C,'\0'));
    for(int r=0; r<R; r++) {
        string s; cin >> s;
        for(int c=0; c<C; c++) {
            maps[r][c] = s[c];
            if(maps[r][c] == 'X') {
                q.push({r,c});
            }
        }
    }


    Melting();

    int isS = false;
    int sr = R; int sc = C;
    int er = -1; int ec = -1;
    
    for(int r=0; r<R; r++) {
        for(int c=0; c<C; c++) {
            if(maps[r][c] == 'X') {
                sr = min(sr,r);
                sc = min(sc,c);
                er = max(er,r);
                ec = max(ec,c);
            }
        }
    }

    for(int r=sr; r<=er; r++) {
        for(int c=sc; c<=ec; c++) {
            cout << maps[r][c];
        }
        cout << "\n";
    }
    
    return 0;
}