#include <iostream>
#include <vector>
#include <deque>
#include <queue>
#include <algorithm>

using namespace std;

int N, M, K;
vector<vector<int>> maps;
vector<vector<int>> nuts;
vector<vector<deque<int>>> trees;
queue<pair<int,int>> forFalls;

int dr[] = {-1,-1,-1,0,0,1,1,1};
int dc[] = {-1,0,1,-1,1,-1,0,1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

void Winter() {
    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            maps[r][c] += nuts[r][c];
        }
    }
}

void Fall() {
    while(!forFalls.empty()) {
        auto[r,c] = forFalls.front(); forFalls.pop();
        for(int d=0; d<8; d++) {
            int nr = r+dr[d]; int nc = c+dc[d];
            if(inRange(nr,nc)) {
                trees[nr][nc].push_back(1);
            }
        }
    }
}

void SpringSummer() {
    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            if(trees[r][c].size() > 0) {
                int nut = maps[r][c];
                deque<int> tmp = trees[r][c];

                sort(tmp.begin(), tmp.end());
                
                int erase_start = tmp.size();
                for(int i=0; i<tmp.size(); i++) {
                    if(nut - tmp[i] >= 0) {
                        nut -= tmp[i];
                        tmp[i]++;
                        if(tmp[i]%5 == 0) {
                            forFalls.push({r,c});
                        }
                    }
                    else {
                        erase_start = i;
                        break;
                    }
                }

                queue<int> q;
                for(int i=tmp.size()-1; i>=erase_start; i--) {
                    q.push(tmp[i]);
                    tmp.pop_back();
                }

                int sum = 0;
                while(!q.empty()) {
                    int age = q.front(); q.pop();
                    sum += age/2;
                }
                maps[r][c] = nut + sum;
                trees[r][c] = tmp;
            }
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M >> K;

    maps.resize(N, vector<int>(N, 5));
    nuts.resize(N, vector<int>(N,0));
    trees.resize(N, vector<deque<int>>(N,deque<int>(0)));
    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            cin >> nuts[r][c];
        }
    }

    for(int i=0; i<M; i++) {
        int x, y, z;
        cin >> x >> y >> z;
        x--; y--;
        trees[x][y].push_back(z);
    }

    while(K--) {
        SpringSummer();
        Fall();
        Winter();
    }

    int answer = 0;
    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            answer += trees[r][c].size();
        }
    }
    cout << answer;
    
    return 0;
}