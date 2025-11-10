#include <iostream>
#include <vector>
#include <deque>

using namespace std;
int N, M;
vector<vector<int>> maps;
vector<vector<int>> tmp;

// 우하좌상
int dr[] = {0, 1, 0, -1};
int dc[] = {1, 0, -1, 0};

void printt() {
    for(int r=0; r<maps.size(); r++) {
        for(int c=0; c<maps[0].size(); c++) {
            cout << maps[r][c] << " ";
        }
        cout << "\n";
    }
}

void move(int sr, int er, int sc, int ec, int d) {
    for(int r=sr; r<er; r++) {
        for(int c=sc; c<ec; c++) {
            int nr = r + dr[d]*N/2; 
            int nc = c + dc[d]*M/2;
            tmp[nr][nc] = maps[r][c];
        }
    }
}

void Six() {
    tmp.assign(N, vector<int>(M,0));
    move(0, N/2, 0, M/2, 1);
    move(0, N/2, M/2, M, 2);
    move(N/2, N, M/2, M, 3);
    move(N/2, N, 0, M/2, 0);
    maps = tmp;
}

void Five() {
    tmp.assign(N, vector<int>(M,0));
    move(0, N/2, 0, M/2, 0);
    move(0, N/2, M/2, M, 1);
    move(N/2, N, M/2, M, 2);
    move(N/2, N, 0, M/2, 3);
    maps = tmp;
}

void Four() {
    tmp.assign(M, vector<int>(N,0));
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            tmp[M-1-c][r] = maps[r][c];
        }
    }
    maps = tmp;
}

void Three() {
    tmp.assign(M, vector<int>(N,0));
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            tmp[c][N-1-r] = maps[r][c];
        }
    }
    maps = tmp;
}

void Two() {
    tmp.assign(N, vector<int>(M,0));
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            tmp[r][M-1-c] = maps[r][c];
        }
    }
    maps = tmp;
}

void One() {
    tmp.assign(N, vector<int>(M,0));
    for(int c=0; c<M; c++) {
        for(int r=0; r<N; r++) {
            tmp[N-1-r][c] = maps[r][c];
        }
    }
    maps = tmp;
}
 
int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    int R; cin >> R;

    maps.resize(N, vector<int>(M, 0));
    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> maps[r][c];
        }
    }

    while(R--) {
        int a; cin >> a;

        N = maps.size(); M = maps[0].size();
        if(a == 1) One();
        else if(a == 2) Two();
        else if(a == 3) Three();
        else if(a == 4) Four();
        else if(a == 5) Five();
        else if(a == 6) Six();
    }

    printt();
    return 0;
}