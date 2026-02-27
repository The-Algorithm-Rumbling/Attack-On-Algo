#include <iostream>
#include <vector>

using namespace std;

int N; 
vector<vector<char>> maps;

// 하우상좌
int dr[] = {1,0,-1,0};
int dc[] = {0,1,0,-1}; 

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 &&c <N;
}

int measure(int r, int c, int d) {
    int cnt = 0;

    int nr = r+dr[d]; int nc = c+dc[d];
    while(inRange(nr,nc) && maps[nr][nc] == '*') {
        cnt++;
        nr = nr+dr[d]; nc = nc+dc[d];
    }

    return cnt;
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    maps.resize(N, vector<char>(N, '\0'));

    int sr = -1; int sc = -1;
    for(int r=0; r<N; r++) {
        string s; cin >> s;
        for(int c=0; c<N; c++) {
            maps[r][c] = s[c];
            if(sr == -1 && sc == -1 && maps[r][c] == '*') {
                sr = r; sc = c;
            }
        }
    }

    // 심장 위치
    int hr = sr+1; int hc = sc;
    cout << hr+1 << " " << hc+1 << "\n";

    // 왼팔
    cout << measure(hr, hc, 3) << " ";
    // 오른팔
    cout << measure(hr, hc, 1) << " ";

    // 허리
    int waist = measure(hr, hc, 0);
    cout << waist << " ";

    int wr = hr + dr[0]*waist;
    int wc = hc;
    // 왼다리
    cout << measure(wr, wc-1, 0) << " "; 
    // 오른다리
    cout << measure(wr, wc+1, 0) << " "; 

    return 0;
}