#include <iostream>
#include <vector>
#include <set>
#include <tuple>
using namespace std;

int N, M, K;
vector<vector<vector<int>>> maps;
vector<tuple<int,int,int,int,int>> fireballs;
int dr[] = {-1,-1,0,1,1,1,0,-1};
int dc[] = {0,1,1,1,0,-1,-1,-1};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

int main() {
    cin >> N >> M >> K;
    fireballs.resize(M, {0,0,0,0,0});

    for(int i=0; i<M; i++) {
        int r, c, m, s, d; 
        cin >> r >> c >> m >> s >> d;
        r--; c--;
        fireballs[i] = {r,c,m,s,d};
    }
    
    while(K--) {
        maps.assign(N, vector<vector<int>>(N));
        set<pair<int,int>> pos;
        vector<tuple<int,int,int,int,int>> tmp;                
        
        for(int i=0; i<fireballs.size(); i++) {
            auto [r,c,m,s,d] = fireballs[i];
            int nr = r + dr[d]*s;
            int nc = c + dc[d]*s;
            
            // 0과 N은 연결되어 있다.
            nr = (nr%N + N) % N;
            nc = (nc%N + N) % N;
            
            pos.insert({nr,nc});
            maps[nr][nc].push_back(i);
        }
        
        // 이동이 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
        for(auto p : pos) {
            auto [pr, pc] = p;
            
            if(maps[pr][pc].size() >= 2) {
                int M = 0; 
                int S = 0;
                bool isOdd = false; 
                bool isEven = false;
                
                for(int f : maps[pr][pc]) {
                    auto [r,c,m,s,d] = fireballs[f];
                    M += m;
                    S += s;
                    if(d % 2 == 0) isEven = true;
                    else isOdd = true;
                }

                M = M/5; 
                S = S/maps[pr][pc].size();
                
                if(M > 0) {
                    int start = 1;
                    if((isOdd && !isEven) || (!isOdd && isEven)) start = 0;

                    for(int nd = start; nd <= 7; nd +=2) {
                        tmp.push_back({pr,pc,M,S,nd});
                    }
                } 
            }
            else if(maps[pr][pc].size() == 1) {
                int fIdx = maps[pr][pc][0];
                auto [r,c,m,s,d] = fireballs[fIdx];
                tmp.push_back({pr,pc,m,s,d});
            }
        }
        fireballs = tmp;
    }
    
    int sum = 0;
    for(auto f : fireballs) {
        auto [r,c,m,s,d] = f;
        sum += m;
    }
    cout << sum;
    
    return 0;
}