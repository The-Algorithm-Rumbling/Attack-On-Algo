#include <iostream>
#include <vector>
#include <queue>
#include <set>

using namespace std;
int N, M;
int answer = 0;
vector<vector<int>> maps;
queue<pair<int,int>> q;

int dr[] = {-1,-1,0,1,1,1,0,-1};
int dc[] = {0,1,1,1,0,-1,-1,-1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<M;
}
 
int bfs() {
    vector<pair<int,int>> tmps;
    set<pair<int,int>> sets;
    
    while(!q.empty()) {
        auto[r,c] = q.front(); q.pop();

        // 무너질까?
        int cnt = 0;
        for(int d=0; d<8; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];
            if(inRange(nr,nc) && maps[nr][nc] == 0) cnt++;
        }

        if(maps[r][c] <= cnt) {
            tmps.push_back({r,c}); // 예비 빈칸
        }
    }

    for(auto t : tmps) {
        auto[r,c] = t;
        maps[r][c] = 0; // 빈칸이 됨
    }

    // 새로운 빈칸에 대해 인접한 모래성들 q에 넣어주기
    for(auto t : tmps) {
        auto[r,c] = t;
        for(int d=0; d<8; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];
            if(inRange(nr,nc) && maps[nr][nc] > 0) sets.insert({nr,nc});
        }
    }

    for(auto s : sets) {
        q.push(s);
    }

    return tmps.size();
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    maps.resize(N, vector<int>(M, 0));
    
    for(int r=0; r<N; r++) {
        string s; cin >> s;
        for(int c=0; c<M; c++) {
            char i = s[c];
            if(i == '.') maps[r][c] = 0;
            else {
                maps[r][c] = i-'0';
            } 
        }
    }

    // 초기 빈칸에 대해 인접한 모래성들 q에 넣어주기 
    set<pair<int,int>> sets;
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            if(maps[r][c] > 0) {
                for(int d=0; d<8; d++) {
                    int nr = r + dr[d]; int nc = c + dc[d];
                    if(inRange(nr,nc) && maps[nr][nc] == 0) {
                        sets.insert({r,c});
                        break;
                    }
                }
            }
        }
    }
    
    for(auto s : sets) {
        q.push(s);
    }

    while(!q.empty()) {
        int cnt = bfs();
        if(cnt == 0) break;
        answer++;
    }
    
    cout << answer;
    return 0;
}