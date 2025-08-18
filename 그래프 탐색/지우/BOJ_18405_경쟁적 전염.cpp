#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;

int N, K;
int S, X, Y;
vector<vector<int>> maps;
priority_queue<tuple<int,int,int>, vector<tuple<int,int,int>>, greater<tuple<int,int,int>>> pq;

int dr[] = {1,0,-1,0};
int dc[] = {0,-1,0,1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

void bfs() {
    while(S--) {
        priority_queue<tuple<int,int,int>, vector<tuple<int,int,int>>, greater<tuple<int,int,int>>> pq2;
        while(!pq.empty()) {
            auto[num, r, c] = pq.top(); pq.pop();

            for(int d=0; d<4; d++) {
                int nr = r + dr[d]; int nc = c + dc[d];
                if(inRange(nr,nc) && maps[nr][nc] == 0) {
                    maps[nr][nc] = num;
                    pq2.push({num, nr, nc});
                }
            }
        }
        pq = pq2;
    }
}

int main() {
    cin >> N >> K;
    maps.resize(N, vector<int>(N,0));

    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            cin >> maps[r][c];
            if(maps[r][c] > 0) pq.push({maps[r][c], r,c});
        }
    }
    cin >> S >> X >> Y;
    
    bfs();
    cout << maps[X-1][Y-1];
    return 0;
}