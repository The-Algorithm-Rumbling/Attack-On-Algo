#include <iostream>
#include <vector>
#include <map>
#include <queue>
#include <tuple>

using namespace std;
int N, K, L;
vector<vector<int>> maps;
map<int, char> m;
queue<tuple<int,int,int,int>> q;

int dr[] = {0, -1, 0, 1};
int dc[] = {1, 0, -1, 0};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

int change(int dir, char C) {
    if(C == 'L') return (dir+1)%4;
    return (dir+3)%4;
}

int bfs() {
    q.push({0,0,0,0});
    maps[0][0] = 1;
    vector<pair<int,int>> tracks;

    while(!q.empty()) {
        auto [r,c,dir,cnt] = q.front(); q.pop();
        tracks.push_back({r,c});

        // 이제 cnt+1초 진행 
        if(m.find(cnt) != m.end()) dir = change(dir, m[cnt]);
        int nr = r+dr[dir]; int nc = c+dc[dir];
        
        if(inRange(nr,nc) && maps[nr][nc] != 1) {
            if(maps[nr][nc] == 2) {
                maps[nr][nc] = 1;
            }
            else {
                maps[nr][nc] = 1;
                auto[sr,sc] = tracks[0];
                maps[sr][sc] = 0;
                tracks.erase(tracks.begin()+0);
            }
            q.push({nr,nc,dir,cnt+1});
            
        } else {
            return cnt+1; // (cnt+1)초에 이동을 시도하기 때문
        }
    }
    return -1;
}

int main() {
    cin >> N; maps.resize(N, vector<int>(N,0));
    cin >> K;
    
    for(int i=0; i<K; i++) {
        int r, c; cin >> r >> c;
        r--; c--;

        maps[r][c] = 2; // 사과!
    }

    cin >> L;
    for(int i=0; i<L; i++) {
        int X; char C; 
        cin >> X >> C;

        m.insert({X,C});
    }

    cout << bfs();
    return 0;
}