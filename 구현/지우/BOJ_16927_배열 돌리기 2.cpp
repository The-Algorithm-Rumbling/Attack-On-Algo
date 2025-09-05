#include <iostream>
#include <vector>

using namespace std;

int N, M, R;
vector<vector<int>> maps;

// 이전걸 끌어오기 
// 우, 하, 상, 좌
int dr[] = {0,1,0,-1};
int dc[] = {1,0,-1,0};

bool inRange(int r , int c, int start) {
    return r >= start && r< N-start && c>=start && c < M-start;
}

void move(int start, int total) {
    int realR = R % total; // 칸 수만큼 이동의 최종 모양은 반복됨!
    
    while(realR--) {
        int r = start; int c = start; // 무조건 이 위치부터 돌릴거임!!
        int curr = maps[r][c];
        int d = 0;

        while(d < 4) {
            int nr = r + dr[d]; int nc = c + dc[d];

            if(nr == r && nc == c) break;
            if(inRange(nr,nc, start)) {
                maps[r][c] = maps[nr][nc];
                r = nr; c = nc;
            } else {
                d++;
            }
        }
        maps[start+1][start] = curr;
    }
}

int main() {
    cin >> N >> M >> R;
    maps.resize(N, vector<int>(M,0));

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
        }
    }

    int maxDepth = min(M,N)/2; //전체 깊어지는 수
    int sizeM = M;
    int sizeN = N;

    for(int d=0; d<maxDepth; d++) {
        int total = sizeN*2 + sizeM*2 - 4;
        move(d, total);

        sizeN -= 2;
        sizeM -= 2;
    }

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cout << maps[r][c] << " ";
        }
        cout << "\n";
    }
        
    return 0;
}